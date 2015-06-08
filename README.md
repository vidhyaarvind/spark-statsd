# spark-statsd

StatsD is common wrapper for graphite metrics. This project enables the use of statsd metrics collection via spark metrics.properties file

Scala version 2.10.4
Build version 0.13.6
Spark version 1.3.0

###Steps to create assembly jar

```
git clone git@github.com:vidhyaarvind/spark-statsd.git

cd spark-statsd

sbt assembly
```

creates target/scala-2.10/spark-statsd-1.0.0.jar assembly jar file


### Add settings to conf/metrics.properties 

```
*.sink.statsd.class=org.apache.spark.metrics.sink.StatsDSink
*.sink.statsd.host=localhost
*.sink.statsd.port=8125
```

### Run spark job

eg. 
```
./bin/run-example SparkPi 10 --driver-class-path target/scala-2.10/spark-statsd-1.0.0.jar
```
