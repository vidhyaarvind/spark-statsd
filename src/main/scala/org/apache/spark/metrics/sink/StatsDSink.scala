package org.apache.spark.metrics.sink

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.codahale.metrics.{MetricRegistry, MetricFilter}
import com.readytalk.metrics.StatsDReporter
import org.apache.spark.SecurityManager
import org.apache.spark.metrics.MetricsSystem
import org.apache.spark.metrics.sink.Sink

private[spark] class StatsDSink(val property: Properties,
                 val registry: MetricRegistry,
                 securityMgr: SecurityManager) extends Sink {

  val STATSD_DEFAULT_PERIOD = 10
  val STATSD_DEFAULT_UNIT = "SECONDS"
  val STATSD_DEFAULT_PREFIX = ""

  val STATSD_KEY_HOST = "host"
  val STATSD_KEY_PORT = "port"
  val STATSD_KEY_PERIOD = "period"
  val STATSD_KEY_UNIT = "unit"
  val STATSD_KEY_PREFIX = "prefix"

  def propertyToOption(prop: String): Option[String] = Option(property.getProperty(prop))

  if (!propertyToOption(STATSD_KEY_HOST).isDefined) {
    throw new Exception("StatsD sink requires 'host' property.")
  }

  if (!propertyToOption(STATSD_KEY_PORT).isDefined) {
    throw new Exception("StatsD sink requires 'port' property.")
  }

  val host = propertyToOption(STATSD_KEY_HOST).get
  val port = propertyToOption(STATSD_KEY_PORT).get.toInt

  val pollPeriod = propertyToOption(STATSD_KEY_PERIOD) match {
    case Some(s) => s.toInt
    case None => STATSD_DEFAULT_PERIOD
  }

  val pollUnit: TimeUnit = propertyToOption(STATSD_KEY_UNIT) match {
    case Some(s) => TimeUnit.valueOf(s.toUpperCase())
    case None => TimeUnit.valueOf(STATSD_DEFAULT_UNIT)
  }

  val prefix = propertyToOption(STATSD_KEY_PREFIX).getOrElse(STATSD_DEFAULT_PREFIX)

  MetricsSystem.checkMinimalPollingPeriod(pollUnit, pollPeriod)


  val reporter = StatsDReporter.forRegistry(registry)
    .prefixedWith(prefix)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .convertRatesTo(TimeUnit.SECONDS)
    .filter(MetricFilter.ALL)
    .build(host, port)

  override def start() {
    reporter.start(pollPeriod, pollUnit)
  }

  override def stop() {
    reporter.stop()
  }

  override def report() {
    reporter.report()
  }
}