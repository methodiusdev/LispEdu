package com.methodiusdev.lispedu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.methodiusdev.lispedu.data.LispRepository
import com.methodiusdev.lispedu.ui.viewmodel.LessonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    viewModel: LessonViewModel,
    lispRepository: LispRepository,
    onBackClick: () -> Unit,
    onBackToMenu: () -> Unit
) {
    when (viewModel.lessonState) {
        0 -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Lesson 1")
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Text(
                        text = lispRepository.sampleLesson.title,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = lispRepository.sampleLesson.content
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.takeQuiz() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text(text = "Take Quiz!")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onBackClick() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text(text = "Back")
                    }
                }
            }
        }
        1 -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Quiz. Question ${viewModel.currentQuestionIndex}")
                        }
                    )
                }
            ) { paddingValues ->
                val currentQuestion = lispRepository.sampleLesson
                    .quizQuestions[viewModel.currentQuestionIndex]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = currentQuestion.questionText
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    currentQuestion.options.forEachIndexed { index, optionText ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (viewModel.selectedAnswerIndex == index),
                                    onClick = { viewModel.selectAnswer(index) }
                                )
                                .padding(16.dp)
                        ) {
                            RadioButton(
                                selected = (viewModel.selectedAnswerIndex == index),
                                onClick = null
                            )
                            Text(text = optionText)
                        }
                    }

                    Button(
                        onClick = { viewModel.submitAnswer(); viewModel.nextQuestion() },
                        enabled = viewModel.selectedAnswerIndex != -1,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Submit Answer")
                    }
                }
            }
        }
        2 -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Lesson score!")
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Quiz Complete!",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your Score: ${viewModel.score} / " +
                                "${LispRepository.sampleLesson.quizQuestions.count()}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onBackToMenu() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Back to Main Menu")
                    }
                }
            }
        }
    }
}