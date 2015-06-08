
name := "spark-statsd"

version := "1.0.0"

organization := "spark.metrics"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.0" % "provided"

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


//lazy val commonSettings = Seq(
//  version := "1.0.0",
//  organization := "spark.metrics",
//  name := "spark-statsd",
//  scalaVersion := "2.10.4",
//  libraryDependencies ++= Seq(
//    "org.apache.spark" %% "spark-core" % "1.3.0",
//    "com.readytalk" % "metrics3-statsd" % "4.1.0"
//  ),
//  resolvers ++= Seq(
//    "Bintray" at "https://dl.bintray.com/readytalk/maven"
//  )
//)
//
//
//lazy val statsd = project
//  .in(file("."))
//  .settings(commonSettings: _*)
//  .settings(
//    publishLocal := {},
//    publish := {},
//    assemblyMergeStrategy in assembly := {
//      case "BUILD" => MergeStrategy.discard
//      case other => MergeStrategy.defaultMergeStrategy(other)
//    }
//  )


//publishMavenStyle := true

//publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (version.value.endsWith("SNAPSHOT"))
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
//}

//pomExtra := (
//  <url>https://github.com/vidhyaarvind/spark-statsd</url>
//  <licenses>
//    <license>
//      <name>Apache License, Verision 2.0</name>
//      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
//      <distribution>repo</distribution>
//    </license>
//  </licenses>
//  <scm>
//    <url>git@github.com:vidhyaarvind/spark-statsd.git</url>
//    <connection>scm:git:git@github.com:vidhyaarvind/spark-statsd.git</connection>
//  </scm>
//  <developers>
//    <developer>
//      <id>vidhyaarvind</id>
//      <name>Vidhya Arvind</name>
//      <url>https://github.com/vidhyaarvind</url>
//    </developer>
//  </developers>)


