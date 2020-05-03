package com.example.foodjournal.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.example.foodjournal.R
import com.example.foodjournal.Screen
import com.example.foodjournal.model.Dish
import com.example.foodjournal.model.Entry
import com.example.foodjournal.navigateTo

private val defaultSpacerSize = 16.dp

@Composable
fun EntryScreen(
    entry: Entry,
    scaffoldState: ScaffoldState = remember { ScaffoldState() },
    modifier: Modifier = Modifier
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(
                title = { Text(text = entry.restaurant ?: "Home Cooked") },
                navigationIcon = {
                    IconButton(onClick = { navigateTo(Screen.Feed) }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        bottomAppBar = { BottomBar(entry) }
    ) {
        EntryContent(modifier, entry)
    }
}

@Composable
fun BottomBar(entry: Entry) {
    Surface(elevation = 2.dp) {
        Box(modifier = Modifier.preferredHeight(56.dp).fillMaxSize()) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                BottomBarAction(resId = R.drawable.ic_add_photo) { TODO() }
                BottomBarAction(resId = R.drawable.ic_dishes_add) { entry.dishes += Dish("Testing") }
            }
        }
    }
}

@Composable
private fun BottomBarAction(
    @DrawableRes resId: Int,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = Modifier.padding(12.dp).preferredSize(24.dp, 24.dp)) {
        Icon(vectorResource(resId))
    }
}

@Composable
fun EntryContent(modifier: Modifier, entry: Entry) {
    Stack(modifier = modifier.fillMaxSize()) {
        VerticalScroller {
            Column {
                HeaderImage()
                Column(
                    modifier = modifier.padding(start = defaultSpacerSize, end = defaultSpacerSize)
                ) {
                    Spacer(Modifier.preferredHeight(defaultSpacerSize))
                    entry.location?.let { Text(text = it, style = MaterialTheme.typography.h3) }
                    Text(text = entry.date.toString(), style = MaterialTheme.typography.h4)
                    Rating(entry.rating)
                    Spacer(Modifier.preferredHeight(defaultSpacerSize))
                    Dishes(entry.dishes)
                }
            }
        }
    }
}

@Composable
private fun Dishes(dishes: List<Dish>) {
    for (dish in dishes) {
        Row(
            verticalGravity = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                asset = vectorResource(id = R.drawable.ic_fastfood),
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(text = dish.name, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
private fun Rating(rating: Int) {
    Row {
        for (i in 1 .. rating) {
            IconButton(onClick = { TODO() }) {
                Icon(asset = vectorResource(id = R.drawable.ic_star))
            }
        }
        for (i in rating + 1 .. 5) {
            IconButton(onClick = { TODO() }) {
                Icon(asset = vectorResource(id = R.drawable.ic_star_outline))
            }
        }
    }
}

@Composable
fun HeaderImage() {
    val image = imageResource(id = R.drawable.computer)
    val imageModifier = Modifier
        .preferredHeightIn(maxHeight = 180.dp)
        .fillMaxWidth()
    Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
}
