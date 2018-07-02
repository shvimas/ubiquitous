package ubiquitous.frontend

import ubiquitous.backend.{Backend, Translation, Word}

import scala.swing._
import scala.swing.event._

class UI(backend: Backend) extends MainFrame {
  visible = true
  title = "Ubiquitous"
  preferredSize = new Dimension(1024, 768)

  val addWordButton = new Button("+")
  val closeButton = new Button("close")

  private val controls = new FlowPanel {
    contents += Swing.Glue
    contents += addWordButton
    contents += Swing.Glue
    //    contents += Swing.HStrut(10)
    //    contents += closeButton
    border = Swing.TitledBorder(Swing.LineBorder(java.awt.Color.RED, 3), "Controls")
  }

  private def wordsGrid = new GridPanel(backend.countWords, 2) {
    for ((word, translation) <- backend.getWords) {
      contents += new Label(word)
      contents += new Label(translation)
    }
  }

  def rootComponent: Component =
    new BorderPanel {
      add(controls, BorderPanel.Position.North)
      add(wordsGrid, BorderPanel.Position.Center)
    }

  def updateRootComponent(): Unit = {
    contents = rootComponent
  }

  updateRootComponent()

  listenTo(addWordButton, closeButton)

  reactions += {
    case ButtonClicked(`addWordButton`) => addWord()
    case ButtonClicked(`closeButton`) => closeMe()
  }


  def addWord(): Unit = {

    def translationTuple2text(tuple: (String, Translation, String)): String =
      s"${tuple._1}: ${tuple._2}. ${tuple._3}"

    val word = Dialog.showInput[Word](
      parent = contents.head,
      message = "Type in word you want to add",
      initial = "").getOrElse(return)
    val translations = backend.translate(word)
    val radioButtons = translations.map(translationTuple2text).map(new RadioButton(_))
    val userTranslation = new RadioButton("user translation")

    new Frame {
      visible = true
      title = "choose translation"
      preferredSize = new Dimension(512, 128)
      //      location = new Point()

      contents = new BoxPanel(Orientation.Vertical) {
        contents ++= radioButtons
        contents += userTranslation
      }

      listenTo(radioButtons: _*)
      listenTo(userTranslation)

      reactions += {
        case ButtonClicked(`userTranslation`) =>
          val translation =
            Dialog.showInput(
              message = "Type in translation",
              initial = "")
          if (translation.nonEmpty) {
            backend.addWord(word, translation.get)
            updateRootComponent()
          }
          close()
        case ButtonClicked(button) =>
          val translation = translations.find(tuple => translationTuple2text(tuple) == button.text).get._2
          backend.addWord(word, translation)
          updateRootComponent()
          close()
      }
    }
  }

  def closeMe(): Unit = {
    val res = Dialog.showConfirmation(contents.head,
      "Do you really want to quit?",
      optionType = Dialog.Options.YesNo,
      title = title)
    if (res == Dialog.Result.Ok)
      sys.exit(0)
  }
}
