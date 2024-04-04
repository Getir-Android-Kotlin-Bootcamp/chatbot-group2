package com.example.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val apiKey = "AIzaSyClVhDkfI5KjDAejX4ir0Al6S_sXcOT7Sg"

class GeminiViewModel : ViewModel() {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey
    )

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    fun fetchData(prompt: String) {
        MainScope().launch {
            val response = generativeModel.generateContent(prompt)
            _response.value = response.text
        }
    }
}