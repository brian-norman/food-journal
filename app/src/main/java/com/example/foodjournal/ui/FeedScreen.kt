package com.example.foodjournal.ui

import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.remember
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.example.foodjournal.R
import com.example.foodjournal.Screen
import com.example.foodjournal.model.Entry
import com.example.foodjournal.navigateTo

@Model
class SearchState(
    var textField : TextFieldValue = TextFieldValue()
)

@Composable
fun FeedScreen(
    entries: List<Entry>,
    scaffoldState: ScaffoldState = remember { ScaffoldState() },
    modifier: Modifier = Modifier,
    onAddEntry: () -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(title = {
                Text(
                    text = "Food Journal"
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddEntry() }
            ) {
                Icon(asset = vectorResource(R.drawable.ic_baseline_add_24))
            }
        }
    ) {
        FeedContent(
            modifier = modifier,
            entriesList = entries
        )
    }
}

@Composable
private fun FeedContent(
    modifier: Modifier,
    entriesList: List<Entry>,
    searchState: SearchState = remember { SearchState() }
) {
    Stack(modifier = modifier.fillMaxSize()) {
        // TODO: Replace this with AdapterList
        val filteredEntries = entriesList.filter {
            it.restaurant.toLowerCase().contains(searchState.textField.text.toLowerCase())
        }
        VerticalScroller {
            Column {
                SearchBar(searchState)
                for (entry in filteredEntries) {
                    Clickable(onClick = { navigateTo(Screen.Entry(entry)) }) {
                        FeedCard(entry = entry)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(searchState: SearchState) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        // TODO: Use something with placeholder text
        // TODO: Close keyboard
        TextField(
            value = searchState.textField,
            modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
            imeAction = ImeAction.Search,
            onValueChange = { searchState.textField = it }
        )
    }
}

@Composable
fun FeedCard(entry: Entry) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        FeedCardImage()
        Spacer(
            Modifier.preferredHeight(
                16.dp
            )
        )
        val emphasisLevels = EmphasisAmbient.current
        ProvideEmphasis(emphasisLevels.high) {
            Text(
                text = entry.restaurant ?: "Home Cooked",
                style = typography.h6
            )
            Text(
                text = entry.date.toString(),
                style = typography.body2
            )
        }
    }
}

@Composable
private fun FeedCardImage() {
    // TODO: Take in a URL as a parameter and load image
    val image = imageResource(id = R.drawable.computer)
    val imageModifier = Modifier
        .preferredHeightIn(minHeight = 120.dp, maxHeight = 180.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(4.dp))
    Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
}
