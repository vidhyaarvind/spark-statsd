
name := "spark-statsd"

version := "1.0.0"

organization := "spark.metrics"

scalaVersion := "2.10.4"

sparkVersion := "1.5.2"

//sparkComponents += "core"

spName := "vidhyaarvind/spark-statsd"

spShortDescription := "Statsd metrics reporter for spark"

spDescription := """Statsd metrics reporter for spark.
                   |Helps spark to push metrics to statsd. See ReadMe for more details""".stripMargin

licenses += "Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")

spAppendScalaVersion := true

spIncludeMaven := true

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

resolvers += "Bintray" at "https://dl.bintray.com/readytalk/maven"

libraryDependencies += "com.readytalk" % "metrics3-statsd" % "4.1.0"

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

assemblyMergeStrategy in assembly := {
      case "BUILD" => MergeStrategy.discard
      case other => MergeStrategy.defaultMergeStrategy(other)
      case PathList("META-INF", xs @ _*) =>
        (xs map {_.toLowerCase}) match {
          case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) =>
            MergeStrategy.discard
          case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
            MergeStrategy.discard
          case "plexus" :: xs =>
            MergeStrategy.discard
          case "services" :: xs =>
            MergeStrategy.filterDistinctLines
          case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
            MergeStrategy.filterDistinctLines
          case _ => MergeStrategy.deduplicate
        }
    }

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/vidhyaarvind/spark-statsd</url>
  <licenses>
    <license>
      <name>Apache License, Verision 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:vidhyaarvind/spark-statsd.git</url>
    <connection>scm:git:git@github.com:vidhyaarvind/spark-statsd.git</connection>
  </scm>
  <developers>
    <developer>
      <id>vidhyaarvind</id>
      <name>Vidhya Arvind</name>
      <url>https://github.com/vidhyaarvind</url>
    </developer>
  </developers>)
