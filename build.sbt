import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

enablePlugins(JavaAppPackaging)

name := "scala-jsonapi-example"

version := "0.3.4"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val akkaVersion           = "2.4.4"

  Seq(
    "com.typesafe.akka"    %% "akka-actor"                           % akkaVersion,
    "com.typesafe.akka"    %% "akka-slf4j"                           % akkaVersion,
    "com.typesafe.akka"    %% "akka-stream"                          % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-core"                       % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-experimental"               % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-spray-json-experimental"    % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-testkit"                    % akkaVersion % "test",
    "org.zalando"          %% "scala-jsonapi"                        % "0.3.4"
  )
}

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(SpacesAroundMultiImports, false)
  .setPreference(CompactControlReadability, false)

Revolver.settings
