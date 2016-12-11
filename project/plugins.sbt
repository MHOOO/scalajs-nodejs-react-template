// Comment to get more information during initialization
logLevel := Level.Debug

/// Plugins
// sbt base plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")

// compiling scalajs projects via the ScalaJSPlugin
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.13")

// compiling web based projects via the sbt-web plugin
//addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.2.0")

// bundle npm dependencies together for the browser
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.3.1")

// Resolvers
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("scala-js-snapshots", url("http://repo.scala-js.org/repo/snapshots/"))(Resolver.ivyStylePatterns)
