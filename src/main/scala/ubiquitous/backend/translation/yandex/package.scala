package ubiquitous.backend.translation

package object yandex {
  type SupportedLang = Lang with YandexSupport
  type YandexLangId = String
}
