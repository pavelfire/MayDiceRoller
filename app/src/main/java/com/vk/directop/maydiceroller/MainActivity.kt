package com.vk.directop.maydiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.bt_roll)
        rollButton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        //Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceRoll2 = dice.roll()

        //Update the screen with the dice roll
        /*val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView2.text = diceRoll2.toString()*/

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        changePicture(diceRoll, diceImage)
        changePicture(diceRoll2, diceImage2)

        //short write
        //resultTextView2.text = Dice(12).roll().toString()
        //Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
    }

    private fun changePicture( roll: Int, img: ImageView){
        // Determine which drawable resource ID to use based on the dice roll
        val drawableRes: Int = when(roll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView with the correct drawable resource ID
        img.setImageResource(drawableRes)

        // Update the content description
        img.contentDescription = roll.toString()

    }
    /*when(diceRoll){
        1 -> diceImage.setImageResource(R.drawable.dice_1)
        2 -> diceImage.setImageResource(R.drawable.dice_2)
        3 -> diceImage.setImageResource(R.drawable.dice_3)
        4 -> diceImage.setImageResource(R.drawable.dice_4)
        5 -> diceImage.setImageResource(R.drawable.dice_5)
        6 -> diceImage.setImageResource(R.drawable.dice_6)
    }*/

    class Dice(val numSides: Int) {

        fun roll(): Int {
            return (1..numSides).random()
        }
    }

}