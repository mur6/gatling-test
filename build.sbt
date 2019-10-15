scalaVersion := "2.12.8"

enablePlugins(GatlingPlugin)

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.0.0" % "test",
  "io.gatling" % "gatling-test-framework" % "3.0.0" % "test",
//  "org.msgpack" % "jackson-dataformat-msgpack" % "0.8.13" % "test",
//  "org.mindrot" % "jbcrypt" % "0.4" % "test"
)
