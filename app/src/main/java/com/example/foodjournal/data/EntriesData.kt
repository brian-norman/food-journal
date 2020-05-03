package com.example.foodjournal.data

import com.example.foodjournal.model.Dish
import com.example.foodjournal.model.Entry
import java.time.LocalDate

val entry1 = Entry(
    id = 1,
    restaurant = "Marufuku Ramen",
    date = LocalDate.parse("2019-02-12"),
    location = "San Francisco",
    rating = 5,
    review = null,
    photos = emptyList(),
    dishes = listOf(Dish("Hakata Tonkotsu DX"))
)

val entry2 = Entry(
    id = 2,
    restaurant = "Deli Board",
    date = LocalDate.parse("2019-03-15"),
    location = "San Francisco",
    rating = 5,
    review = "Best sandwich I've ever had!",
    photos = emptyList(),
    dishes = listOf(Dish("$$$"))
)

val entry3 = Entry(
    id = 3,
    restaurant = "Flour + Water",
    date = LocalDate.parse("2019-04-08"),
    location = "San Francisco",
    rating = 5,
    review = null,
    photos = emptyList(),
    dishes = listOf(
        Dish("Spaghetti"),
        Dish("Ravioli"),
        Dish("Meaty Pizza")
    )
)

val entry4 = Entry(
    id = 4,
    restaurant = "Home Cooked",
    date = LocalDate.parse("2020-04-08"),
    location = "Calgary",
    rating = 4,
    review = null,
    photos = emptyList(),
    dishes = listOf(Dish("Kraft Dinner with Doritos"))
)

val newEntry = Entry(
    id = 5,
    restaurant = "New Entry",
    date = LocalDate.parse("2021-04-08"),
    location = "Calgary",
    rating = 3,
    review = null,
    photos = emptyList(),
    dishes = emptyList()
)

val fakeEntries: List<Entry> = listOf(
    entry1,
    entry2,
    entry3,
    entry4
)
