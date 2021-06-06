package com.vk.directop.maydiceroller
//4. Learn about lambdas and higher-order functions
//https://developer.android.com/codelabs/basic-android-kotlin-training-collections?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-collections#3
//https://kotlinlang.org/docs/collections-overview.html
//https://kotlinlang.org/docs/lambdas.html
class MapLambdas {

    fun dictionaryAndMap() {
        val peopleAges = mutableMapOf<String, Int>(
            "Fred" to 30,
            "Ann" to 23
        )
        peopleAges.put("Barbara", 42)
        peopleAges["Joe"] = 51
        peopleAges["Joe"] = 56
        println(peopleAges)
        peopleAges.forEach{ print("${it.key} is ${it.value}, ")}
        println()
        println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", "))
        peopleAges.filter { it.key.length < 4 }

        // lambda looks like variable with type
        val number: Int = 4
        val triple: (Int) -> Int = { a: Int -> a * 3 }
        val tripleShorter: (Int) -> Int = { it * 3 }
        println(triple(5))
        println(tripleShorter(3))

        val peopleNames = listOf("Fred", "Ann", "Barbara", "Abi", "Joe")
        println(peopleNames.sorted())
        println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length})

        val words = listOf("about", "acute", "awesome", "balloon", "best", "craft", "brief", "class", "bug", "coffee", "creative")
        val filteredWords = words.filter {
            it.startsWith("b", ignoreCase = true)
        }.shuffled()
            .take(2)
            .sorted()
        val endWords = words.filter {
            it.endsWith("t", ignoreCase = true)
        }.shuffled()
        println(filteredWords)
        println(endWords)
        // one random word, starts with the letter c
        val filteredWord = words.filter{ it.startsWith("c", ignoreCase = true)}
            .shuffled()
            .take(1)
            .sorted()
        println(filteredWord)
    }
}