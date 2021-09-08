package com.vk.directop.maydiceroller

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class WherBallActivity : AppCompatActivity() {

    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var btShowResult: Button? = null
    private var btNewGame: Button? = null
    private var etX: EditText? = null
    private var etY: EditText? = null

    //implements SoundPool.OnLoadCompleteListener {
    //https://devcolibri.com/getting-started-with-retrofit-in-android/
    private var mTTS: TextToSpeech? = null

    val size = 7
    var x = 3
    var y = 3
    var moves = 0
    val startMoves = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where_ball)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        btShowResult = findViewById(R.id.btShowResult)
        btNewGame = findViewById(R.id.btNewGame)
        etX = findViewById(R.id.etX)
        etY = findViewById(R.id.etY)

        var textToShow = "" //"!!!!!!!!!\n${x} ${y}\n"


        btShowResult!!.setOnClickListener {

            val userX: Int? = etX!!.text?.toString()?.toIntOrNull()
            val userY: Int? = etY!!.text?.toString()?.toIntOrNull()
            if (userX == null || userY == null) {
                return@setOnClickListener
            }
            var result = ""
            if (userX == x && userY == y && moves == 0){
                result = "You win!"
            }else{
                result = "You lose."
            }
            tv2!!.text = "${result}\nRight answer is ${x} ${y}" }


        btNewGame!!.setOnClickListener {
            textToShow = ""
            x = 3
            y = 3
            moves = startMoves
            for (i in 1..moves){
                textToShow = textToShow + makeMove()
            }
            tv1!!.text = textToShow //+ "\n${x} ${y}"
            tv2!!.text = ""
        }

        mTTS = speechInit()


    }

    private fun speechInit(): TextToSpeech? {
        return TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result: Int
                result = if (mTTS!!.isLanguageAvailable(Locale(Locale.getDefault().language))
                    == TextToSpeech.LANG_AVAILABLE
                ) {
                    mTTS!!.setLanguage(Locale(Locale.getDefault().language))
                    //return new Locale(Locale.getDefault().getLanguage());
                } else {
                    mTTS!!.setLanguage(Locale.US)
                    //return Locale.US;
                }
                //int result = mTTS.setLanguage(Locale.GERMAN);
                Log.e("TTS", "PV Language is setted")
                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language not supported")
                } else {
                    //mButtonSpeak.setEnabled(true);
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
    }
    private fun speak(text: String) {
        var pitch = 1f
        if (pitch < 0.1) pitch = 0.1f
        var speed = 1.toFloat()
        if (speed < 0.1) speed = 0.1f
        mTTS!!.setPitch(pitch)
        mTTS!!.setSpeechRate(speed)
        //mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(text)
            Log.e("TTS", "PV Greather21")
        } else {
            ttsUnder20(text)
            Log.e("TTS", "PV Under20")
        }
    }
    private fun ttsUnder20(text: String) {
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "MessageId"
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, map)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun ttsGreater21(text: String) {
        val utteranceId = this.hashCode().toString() + ""
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }
    override fun onDestroy() {
        if (mTTS != null) {
            mTTS!!.stop()
            mTTS!!.shutdown()
        }
        Log.d("TAG", "onDestroy() called")
        super.onDestroy()
    }

    private fun makeMove(): String{
        val move = Move(3)
        val dir = move.roll()

        when (dir){
            0 -> {//move up
                if (y > 0) {
                    y--
                    moves--
                    speak("up")
                    return "-up-"
                }
            }
            1 -> {
                if (y < size - 1) {
                    y++
                    moves--
                    return "-down-"
                }
            }
            2 -> {
                if (x > 0) {
                    x--
                    moves--
                    return "-left-"
                }
            }
            3 -> {
                if (x < size - 1) {
                    x++
                    moves--
                    return "-right-"
                }
            }
        }
        return "\n"
    }

    class Move(val numOfDir: Int){
        fun roll(): Int{
            return (0..numOfDir).random()
        }
    }


}