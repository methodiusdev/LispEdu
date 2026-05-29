package com.methodiusdev.lispedu.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.methodiusdev.lispedu.data.LispRepository

class LessonViewModel : ViewModel() {
    var lessonState by mutableIntStateOf(0)
        private set

    var currentQuestionIndex by mutableIntStateOf(0)
        private set

    var selectedAnswerIndex by mutableIntStateOf(-1)
        private set

    var score by mutableIntStateOf(0)
        private set

    fun takeQuiz() {
        this.lessonState = 1
    }

    fun selectAnswer(index: Int) {
        this.selectedAnswerIndex = index
    }

    fun submitAnswer() {
        when (this.selectedAnswerIndex) {
            LispRepository.sampleLesson.quizQuestions[currentQuestionIndex].correctAnswerIndex -> {
                this.score += 1
            }
        }
    }

    fun nextQuestion() {
        if (this.currentQuestionIndex < LispRepository.sampleLesson.quizQuestions.count() - 1) {
            this.currentQuestionIndex += 1
            this.selectedAnswerIndex = -1
        } else {
            this.lessonState = 2
        }
    }
}