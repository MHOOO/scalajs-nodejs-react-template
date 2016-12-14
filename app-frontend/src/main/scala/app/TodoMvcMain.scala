package app

// import org.scalajs.dom.browser.console
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react._
import org.scalajs.dom
import scala.scalajs.js.annotation.{ JSImport,JSExportAll }
import scala.scalajs.js

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

// facade for rc-tabs library. Ensures that
// uses of e.g. `RcTabs.Tabs` get translated into native js code
@JSImport("rc-tabs", JSImport.Default)
@js.native
object Tabs extends js.Object
{
  def apply(a : js.Any, b : js.Any) = js.native
  val TabPane : js.Dynamic = js.native
}

@JSImport("rc-tabs/lib/ScrollableInkTabBar", JSImport.Default)
  @js.native
object ScrollableInkTabBar extends js.Object
{
}

@JSImport("rc-tabs/lib/TabContent", JSImport.Default)
  @js.native
object TabContent extends js.Object
{
}


import js.{Any, UndefOr, undefined }
case class RcTabs_Tabs(children : UndefOr[Any] = undefined,
                 renderTabContent : js.Function,
                 style : UndefOr[js.Object] = undefined,
                 onChange : UndefOr[js.Function] = undefined ,
                 renderTabBar : js.Function,
                 prefixCls : UndefOr[String] = undefined,
                 tabBarPosition : UndefOr[String] = undefined,
                 destroyInactiveTabPane : UndefOr[Boolean]=undefined,
                 className : UndefOr[String] = undefined) {
  def toJS = {
    val p = js.Dynamic.literal()
    children.foreach(v => p.updateDynamic("children")(v))
    p.updateDynamic("renderTabContent")(renderTabContent)
    style.foreach(v => p.updateDynamic("style")(v))
    onChange.foreach(v => p.updateDynamic("onChange")(v))
    p.updateDynamic("renderTabBar")(renderTabBar)
    prefixCls.foreach(v => p.updateDynamic("prefixCls")(v))
    tabBarPosition.foreach(v => p.updateDynamic("tabBarPosition")(v))
    destroyInactiveTabPane.foreach(v => p.updateDynamic("destroyInactiveTabPane")(v))
    className.foreach(v => p.updateDynamic("className")(v))
    p
  }

  def apply(children : ReactNode*) = {
    println("apply")
    val t = Tabs
    val factory = React.asInstanceOf[js.Dynamic].createFactory(t)
    val props = toJS
    val reactElement = factory(props, children.toJsArray).asInstanceOf[ReactComponentU_]
    reactElement
  }
}

case class TabPane(tab : UndefOr[String] = undefined,
                   style : UndefOr[Any] = undefined,
                   forceRender : UndefOr[Boolean]=undefined,
                   placeholder :  UndefOr[ReactElement] = undefined,
                   key : UndefOr[String] = undefined,
                   destroyInactiveTabPane : UndefOr[Boolean]=undefined,
                   className : UndefOr[String] = undefined,
                   active : UndefOr[Boolean]=undefined) {
  def toJS = {
    val p = js.Dynamic.literal()
    tab.foreach(v => p.updateDynamic("tab")(v))
    style.foreach(v => p.updateDynamic("style")(v))
    forceRender.foreach(v => p.updateDynamic("forceRender")(v))
    placeholder.foreach(v => p.updateDynamic("placeholder")(v))
    key.foreach(v => p.updateDynamic("key")(v))
    destroyInactiveTabPane.foreach(v => p.updateDynamic("destroyInactiveTabPane")(v))
    className.foreach(v => p.updateDynamic("className")(v))
    active.foreach(v => p.updateDynamic("active")(v))
    p
  }

  def apply(children : ReactNode*) = {
    val f = Tabs.TabPane
    val factory = React.asInstanceOf[js.Dynamic].createFactory(f)
    factory(toJS,children.toJsArray).asInstanceOf[ReactComponentU_]
  }
}

// @JSImport("react", JSImport.Default)
//   @js.native
// object MyReact extends js.Object {}
// @JSImport("react-dom", JSImport.Default)
// @js.native
// object MyReactDom extends js.Object {}


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
    // hack around the fact, that the scalajs-react library requires react
    // to be available as a global named "React", but we use npm dependencies, wich have to be required first
    // js.Dynamic.global.React = MyReact
    // js.Dynamic.global.ReactDOM = MyReactDom

    // val React = MyReact.asInstanceOf[React]
    // val ReactDOM = MyReactDom.asInstanceOf[ReactDOM]
    // create the application
    ReactDOM.render(Ipv4Input(), dom.document.getElementById("root"))
    val tabsCaseClass = RcTabs_Tabs(
      renderTabContent = () ⇒ {
        val ele = React.asInstanceOf[js.Dynamic].createElement(TabContent)
        ele
      },
      renderTabBar = () ⇒ React.asInstanceOf[js.Dynamic].createElement(ScrollableInkTabBar)
    )
    val t = Tabs
    //println(t)
    val tabsElement = tabsCaseClass(
      TabPane(tab="tab 1",
              key="1")("This is tab 1"),
      TabPane(tab="tab 2",
              key="2")()
    )
    ReactDOM.asInstanceOf[js.Dynamic].render(tabsElement.asInstanceOf[js.Any], dom.document.getElementById("tabRoot"))
  }

}
