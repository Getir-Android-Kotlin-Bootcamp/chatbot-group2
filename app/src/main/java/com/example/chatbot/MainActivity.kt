package com.example.chatbot

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val apiKey = "AIzaSyClVhDkfI5KjDAejX4ir0Al6S_sXcOT7Sg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        geminiApi()
    }

    fun geminiApi() {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = apiKey,
        )

        val prompt = "Which LLM are you?"
        MainScope().launch {
            val response = generativeModel.generateContent(prompt)
            response.text?.let { Log.d("response", it) }
        }
    }


}