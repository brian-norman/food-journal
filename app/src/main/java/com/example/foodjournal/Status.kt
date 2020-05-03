package com.example.foodjournal

import androidx.compose.Model
import com.example.foodjournal.model.Entry as EntryModel

/**
 * Class defining the screens we have in the app: feed and entry detail view
 */
sealed class Screen {
    object Feed : Screen()
    data class Entry(val entry: EntryModel) : Screen()
}

@Model
object FoodJournalStatus {
    var currentScreen: Screen = Screen.Feed
}

/**
 * Currently the best way we have to navigate with Compose, doesn't support a back stack yet
 * TODO: https://github.com/zsoltk/compose-router
 */
fun navigateTo(destination: Screen) {
    FoodJournalStatus.currentScreen = destination
}
