package com.vk.directop.maydiceroller

//https://developer.android.com/codelabs/basic-android-kotlin-training-lists?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-lists#1

class ListLearning {

    fun basicAboutLists(){
        val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        println("List: $numbers")
        println("Size: ${numbers.size}")
        println("First element: ${numbers[0]}")
        println("Second element: ${numbers[1]}")
        println("Last index: ${numbers.size - 1}")
        println("Last element: ${numbers[numbers.size - 1]}")
        println("First: ${numbers.first()}")
        println("Last: ${numbers.last()}")
        println("Contains 4? ${numbers.contains(4)}")
        println("Contains 7? ${numbers.contains(7)}")
        println("Reversed list: ${numbers.reversed()}")
        println("Sorted list: ${numbers.sorted()}")
    }
    fun mutableLists() {
        val entrees : MutableList<String> = mutableListOf<String>()
        println("Add noodles: ${entrees.add("noodles")}")
        println("Add spaghetti: ${entrees.add("spaghetti")}")
        println("Add noodles: ${entrees.add("noodles")}")
        val moreItems = listOf("ravioli", "lasagna", "fettuccine")
        println("Add list: ${entrees.addAll(moreItems)}")
        println("Entrees: $entrees")
        println("Remove noodles: ${entrees.remove("noodles")}")
        println("Remove noodles: ${entrees.remove("noodles")}")
        println("Remove noodles: ${entrees.remove("noodles")}")
        println("Entrees: $entrees")
        println("Remove first element: ${entrees.removeAt(0)}")
        println("Entrees: $entrees")
        entrees.clear()
        println("Entrees: $entrees")
        println("Empty? ${entrees.isEmpty()}")
    }
    fun loopThroughAList() {
        val guestsPerFamily = listOf(2, 4, 1, 3)
        var totalGuests = 0
        var index = 0
        while (index < guestsPerFamily.size){
            totalGuests += guestsPerFamily[index]
            index++
        }
        println("Total Guest Count: $totalGuests")
        val names = listOf("Jessica", "Henry", "Alicia", "Jose")
        for (name in names) {
            println("$name - Number of characters: ${name.length}")
        }
    }
}