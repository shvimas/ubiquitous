package ubiquitous.backend

import ubiquitous.backend.translation.Lang

case class UserSettings(var from: Lang,
                        var to: Lang)
