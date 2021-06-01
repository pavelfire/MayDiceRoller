package com.vk.directop.maydiceroller

class OrdersExample {
    //https://developer.android.com/codelabs/basic-android-kotlin-training-lists?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-lists#5

    open class Item(val name: String, val price: Int)

    class Noodles : Item("Noodles", 10){
        override fun toString(): String{
            return name // + ": $" + price
        }
    }
    //only one parameter can be marked as vararg and is usually
    //the last parameter in the list.
    class Vegetables(vararg val toppings: String) : Item("Vegetables", 5){
        override fun toString(): String{
            if (toppings.isEmpty()){
                return "$name Chef's Choice"
            }else{
                return name + ": " + toppings.joinToString(", ")
            }
        }
    }
    class Order(val orderNumber: Int){
        private val itemList = mutableListOf<Item>()

        fun addItem(newItem: Item) : Order{
            itemList.add(newItem)
            return this
        }
        fun addAll (newItems: List<Item>) : Order{
            itemList.addAll(newItems)
            return this
        }
        fun print(){
            println("Order #${orderNumber}")
            var total = 0
            for (item in itemList){
                println("${item}: $${item.price}")
                total += item.price
            }
            println("Total: $${total}")
        }
    }

    fun main() {
        val noodles = Noodles()
        val vegetables = Vegetables("Cabbage", "Sprouts", "Onion")
        val chiefVegetables = Vegetables()
        println(noodles)
        println(vegetables)
        println(chiefVegetables)
        println()

        val orderList = mutableListOf<Order>()

        val order1 = Order(1)
        order1.addItem(Noodles())
        orderList.add(order1)

        val order2 = Order(2)
        order2.addItem(Noodles())
        order2.addItem(Vegetables())
        orderList.add(order2)

        val order3 = Order(3)
        val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
        order3.addAll(items)
        orderList.add(order3)

        val order4 = Order(4).addItem(Noodles())
            .addItem(Vegetables("Cabbage", "Onion"))
            .addItem(Noodles())
        orderList.add(order4)

        orderList.add(
            Order(5)
                .addItem(Noodles())
                .addItem(Noodles())
                .addItem(Vegetables("Spinach")))

        for (order in orderList){
            order.print()
            println()
        }

    }
}