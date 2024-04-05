package com.example.chatbot

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var editTextUserInput: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var chatOutputView: TextView
    private lateinit var scrollViewChat: ScrollView
    private lateinit var progressBarTyping: ProgressBar
    private lateinit var geminiViewModel: GeminiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUserInput = findViewById(R.id.editTextUserInput)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        chatOutputView = findViewById(R.id.chatOutputView)
        scrollViewChat = findViewById(R.id.scrollViewChat)
        progressBarTyping = findViewById(R.id.progressBarTyping)

        geminiViewModel = ViewModelProvider(this)[GeminiViewModel::class.java]

        buttonSubmit.setOnClickListener {
            val userInput = editTextUserInput.text.toString()
            if (userInput.isNotEmpty()) {
                showTypingIndicator()
                geminiViewModel.fetchData(userInput)
                editTextUserInput.setText("")
            }
        }

        geminiViewModel.response.observe(this, Observer { response ->
            chatOutputView.append("\nUser: ${editTextUserInput.text}\nBot: $response")
            hideTypingIndicator()
            scrollViewChat.post { scrollViewChat.fullScroll(ScrollView.FOCUS_DOWN) }
        })
    }

    private fun showTypingIndicator() {
        progressBarTyping.visibility = View.VISIBLE
    }

    private fun hideTypingIndicator() {
        progressBarTyping.visibility = View.GONE
    }
}
