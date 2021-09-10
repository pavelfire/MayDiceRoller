package com.vk.directop.maydiceroller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrdersActivty : AppCompatActivity(){

    private var tv1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        tv1 = findViewById(R.id.tv1)



        var textToShow = ""

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

        val order4 = Order(4)
        order4.addItem(Noodles())
        order4.addItem(Vegetables("Cabbage", "Sprouts", "Onion"))
        order4.addItem(Vegetables())
        orderList.add(order4)

        textToShow = textToShow + order1.print() + order2.print() + order3.print() + order4.print()

        //textToShow = textToShow + "\n " + noodles + "\n " + vegetables + "\n " + vegetables1

        tv1!!.text = "Here is the orders list: \n\n$textToShow"
    }
}

open class Item(val name: String, val price: Int){
    override fun toString(): String{
        return name + " info from Item class"
    }
}

class Noodles : Item("Noodles", 10) {
    override fun toString(): String{
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5){
    override fun toString(): String{
        if (toppings.isEmpty()){
            return "$name Chief's Choice"
        }else{
            return name + " " + toppings.joinToString()
        }

    }
}

class Order(val orderNumber: Int){
    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item){
        itemList.add(newItem)
    }

    fun addAll(newItems: List<Item>){
        itemList.addAll(newItems)
    }

    fun print(): String{
        var result = ""
        var total = 0
        for (item in itemList) {
            result = result + "${item}: $${item.price}\n"
            total += item.price
        }
        return "Order #${orderNumber}\n" + result + "Total: $${total}" + "\n\n"

    }

}