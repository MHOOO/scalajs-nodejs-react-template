import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt.Project.projectToRef
import sbt._

val appVersion = "0.1.0"
val transcendentVersion = "0.2.3.2"
val _scalaVersion = "2.11.8"

val paradisePluginVersion = "3.0.0-M1"
val scalaJsDomVersion = "0.9.0"
val scalaJsJQueryVersion = "0.9.0"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.8", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.8", "-target", "1.8", "-g:vars")

val jsCommonSettings = Seq(
  scalaVersion := _scalaVersion,
  scalacOptions ++= Seq("-feature", "-deprecation"),
  scalacOptions in(Compile, doc) ++= Seq(
    "-no-link-warnings" // Suppresses problems with Scaladoc @throws links
  ),
  relativeSourceMaps := true,
  //persistLauncher := true,
  persistLauncher in Test := false,
  homepage := Some(url("https://github.com/ldaniels528/transcendent-js-todomvc")),
  addCompilerPlugin("org.scalamacros" % "paradise" % paradisePluginVersion cross CrossVersion.full),
  ivyScala := ivyScala.value map (_.copy(overrideScalaVersion = true)),
  resolvers += "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2",
  libraryDependencies ++= Seq(
    "be.doeraene" %%% "scalajs-jquery" % scalaJsJQueryVersion,
    "org.scala-js" %%% "scalajs-dom" % scalaJsDomVersion,
    "org.scala-lang" % "scala-reflect" % _scalaVersion
  )
)

lazy val shared = (project in file("app-shared"))
  .enablePlugins(ScalaJSPlugin)
  .settings(jsCommonSettings: _*)
  .settings(
    name := "shared",
    organization := "com.github.ldaniels528",
    version := appVersion,
    libraryDependencies ++= Seq(
      "com.github.ldaniels528" %%% "scalajs-common" % transcendentVersion
    )
  )

// The frontend runs in the browser
lazy val frontend = (project in file("app-frontend"))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .aggregate(shared)
  .dependsOn(shared)
  .settings(jsCommonSettings: _*)
  .settings(
    name := "frontend",
    organization := "com.github.ldaniels528",
    version := appVersion,
    //mainClass in Compile := Some("app.TodoMvcMain"),
    // dependencies necessary for compilation: these include facades for e.g. react
    libraryDependencies ++= Seq(
      "com.github.ldaniels528" %%% "scalajs-common" % transcendentVersion,
      // contains the scalajs-react library, which includes facades for react
      "com.github.japgolly.scalajs-react" %%% "core" % "0.11.3"
    ),
    npmDependencies in Compile ++= Seq("rc-tabs" -> "0.7.1",
                                       "react" -> "15.3.2",
                                       "react-dom" -> "15.3.2")

    // the following are javascript dependencies, available as webjars
    // npm deps go into app-angularjs/package.json
    // jsDependencies ++= Seq(
    //   "org.webjars.bower" % "react" % "15.3.2"
    //     /        "react-with-addons.js"
    //     minified "react-with-addons.min.js"
    //     commonJSName "React",
    //   "org.webjars.bower" % "react" % "15.3.2"
    //     /         "react-dom.js"
    //     minified  "react-dom.min.js"
    //     dependsOn "react-with-addons.js"
    //     commonJSName "ReactDOM",
    //   "org.webjars.bower" % "react" % "15.3.2"
    //     /         "react-dom-server.js"
    //     minified  "react-dom-server.min.js"
    //     dependsOn "react-dom.js"
    //     commonJSName "ReactDOMServer")
)

// lazy val nodejs = (project in file("app-nodejs"))
//   .aggregate(shared)
//   .dependsOn(shared
//                // depending here, causes the client code to appear inside the server!
//                // , angularjs
// )
//   .enablePlugins(ScalaJSPlugin)
//   .settings(jsCommonSettings: _*)
//   .settings(
//     name := "todomvc-nodejs",
//     persistLauncher := true,
//     organization := "com.github.ldaniels528",
//     version := appVersion,
//     Seq(packageScalaJSLauncher, fastOptJS, fullOptJS) map { packageJSKey =>
//       crossTarget in(angularjs, Compile, packageJSKey) := baseDirectory.value / "public" / "javascripts"
//     },
//     compile in Compile <<=
//       (compile in Compile) dependsOn (fastOptJS in(angularjs, Compile)),
//     libraryDependencies ++= Seq(
//       "com.github.ldaniels528" %%% "scalajs-npm-mean-bundle-minimal" % transcendentVersion
//     )
//   )

// // loads the jvm project at sbt startup
// onLoad in Global := (Command.process("project nodejs", _: State)) compose (onLoad in Global).value
