package app

// import org.scalajs.dom.browser.console
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll


object TabBar {
  case class State(selected : Int)


  val component = ReactComponentB[Unit]("TabBar")
    .initialState(State(selected = 0))
    //.renderBackend

}

object Ipv4Input {

  case class State(ipv4 : String)


  class Backend($: BackendScope[Unit, State]) {
    def onTextChange(e: ReactEventI) = {
      val v = e.target.value
      $.modState(_.copy(ipv4 = v))
    }
    def validateIpv4(e: ReactEventI) = {

      false
    }
    def render(s: State) =   // ← Accept props, state and/or propsChildren as argument
      <.div("IP:",
            <.input(
              ^.onChange ==> onTextChange,

              //^.validate ==> validateIpv4,
              ^.value := s.ipv4))
  }

  val component = ReactComponentB[Unit]("Ipv4Input")
    .initialState(State("192.168.22.1"))
    .renderBackend[Backend]
    .build


  def apply() = component("192.168.22.1")

}


/**
  * Todo MVC application (MEANS.js demo)
  * @author lawrence.daniels@gmail.com
  */
@JSExportAll
object TodoMvcMain extends js.JSApp {
  val Hello = ReactComponentB[String]("Hello")
    .render_P(name => <.div("Hello there ", name))
    .build

  override def main(): Unit = {
    // create the application
    ReactDOM.render(Ipv4Input(), dom.document.getElementById("root"))
  }

}
