package app

import org.scalajs.nodejs.bodyparser.{BodyParser, UrlEncodedBodyOptions}
import org.scalajs.nodejs.express.{Express, Request, Response}
import org.scalajs.nodejs.expressws._
import org.scalajs.nodejs.globals._
import org.scalajs.nodejs.{Bootstrap, console}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
object main extends js.JSApp
{
  // main is empty, but necessary for compilation
  override def main(): Unit = {}

  // will be invoked from the bootstrap js file (server.js)
  def startServer(implicit bootstrap: Bootstrap): Unit = {
    implicit val require = bootstrap.require
    console.log("starting server")

    // determine the port to listen on
    val port = process.env.get("port").map(_.toInt) getOrElse 1337

    // setup Express
    console.log("Loading Express modules...")
    implicit val express = require[Express]("express")
    implicit val app = express().withWsRouting
    implicit val wss = require[ExpressWS]("express-ws")(app)

    // setup the body parsers
    console.log("Setting up body parsers...")
    val bodyParser = require[BodyParser]("body-parser")
    app.use(bodyParser.json())
    app.use(bodyParser.urlencoded(new UrlEncodedBodyOptions(extended = true)))

    // setup the routes for serving static files
    console.log("Setting up the routes for serving static files...")
    app.use(express.static("public"))
    app.use("/assets", express.static("public"))
    app.use("/bower_components", express.static("bower_components"))

    // disable caching
    app.disable("etag")

    // start the listener
    app.listen(port, () => console.log("Server now listening on port %d", port))
    ()
  }
}
