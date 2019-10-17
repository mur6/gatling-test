val gatlingVersion = "3.2.1"

lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    scalaVersion := "2.12.8",
    PB.protoSources in Compile := Seq(file("src/main/protobuf"), file("target/protobuf_external/google/api")),
    PB.includePaths in Compile := Seq(file("src/main/protobuf"), file("target/protobuf_external")),
    PB.targets in Compile += scalapb.gen() -> (sourceManaged in Compile).value,
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "io.gatling" % "gatling-core" % gatlingVersion % "test",
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test",
      "io.gatling" % "gatling-test-framework" % gatlingVersion % "test",
      "com.github.phisgr" %% "gatling-grpc" % "0.5.0" % "test",
      "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf",
    ),
  )
