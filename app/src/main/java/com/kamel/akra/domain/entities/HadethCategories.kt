package com.kamel.akra.domain.entities

data class HadethCategories(
    val id : Int,
    val title: String,
    val count: Int
)

data class Hadeth(
    val id : Int,
    val content: String,
)

data class HadethListById(
    val hadethList:List<HadethCategories> ,
    val lastPage: Int,
    val currentPage: Int
)


