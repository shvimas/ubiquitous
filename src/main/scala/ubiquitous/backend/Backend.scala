package ubiquitous.backend

import ubiquitous.backend.translation.allTranslators

class Backend {
  private val cfgDumpFile = dumpDir + "config.json"
  private val cfg = Config.load(cfgDumpFile)

  private val wordsDumpFile = dumpDir + cfg.fileWithWords
  private val words = Words.load(wordsDumpFile)

  private val translators = allTranslators

  def addWord(word: String, translation: Translation): Unit = {
    words += word -> translation
  }

  def removeWord(word: Word): Unit = {
    words -= word
  }

  def countWords: Int = {
    words.count(_ => true)
  }

  def getWords: TraversableOnce[(Word, Translation)] = {
    words.toSeq
  }

  def translate(word: Word): Seq[(String, Translation, String)] = {
    translators
      .par
      .map(t => (t.name, t.translate(word, cfg.userSettings.from, cfg.userSettings.to), t.postfix))
      .filter(_._2.nonEmpty)
      .map(tuple => (tuple._1, tuple._2.get.get, tuple._3))
      .seq
  }

  def dump(): Unit = {
    cfg.dumpTo(cfgDumpFile)
    words.dumpTo(wordsDumpFile)
  }
}
