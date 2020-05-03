package com.example.foodjournal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.example.foodjournal.data.fakeEntries
import com.example.foodjournal.data.newEntry
import com.example.foodjournal.model.Entry
import com.example.foodjournal.ui.EntryScreen
import com.example.foodjournal.ui.FeedScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodJournalApp()
        }
    }
}

@Model
class FeedModel (
    var entries: List<Entry> = fakeEntries
)

@Composable
private fun FoodJournalApp() {
    MaterialTheme {
        AppContent()
    }
}

@Composable
private fun AppContent(model: FeedModel = FeedModel()) {
    Crossfade(current = FoodJournalStatus.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Feed -> FeedScreen(
                    entries = model.entries,
                    onAddEntry = { model.entries += newEntry }
                )
                is Screen.Entry -> EntryScreen(
                    entry = screen.entry
                )
            }
        }
    }
}
