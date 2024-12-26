package com.syed.quizzapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.syed.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private // Array of programming questions
    val questions = arrayOf(
        "What does JVM stand for?",
        "What is the main purpose of a constructor in programming?",
        "Which language is known as the 'mother of all programming languages'?",
        "Which keyword is used to declare a variable in Kotlin?",
        "What is the full form of HTML?",
        "Which of the following is a type of loop in programming?",
        "What does OOP stand for?",
        "Which of the following is a valid Kotlin data type?",
        "What is an array in programming?",
        "What does 'return' do in a function?"
    )
    private   val option = arrayOf(
        arrayOf("Java Virtual Machine", "Java View Model", "Java Version Manager"), // Q1
        arrayOf("To initialize object properties", "To define a function", "To manage memory"), // Q2
        arrayOf("C", "Assembly", "Fortran"), // Q3
        arrayOf("var", "let", "int"), // Q4
        arrayOf("Hyper Transfer Markup Language", "Hyper Text Markup Language", "High Tech Machine Language"), // Q5
        arrayOf("For loop", "While loop", "Both of the above"), // Q6
        arrayOf("Object Oriented Programming", "Open Operations Protocol", "Optimized Operational Process"), // Q7
        arrayOf("String", "text", "Int32"), // Q8
        arrayOf("A list of items", "A function that returns a value", "A data structure for key-value pairs"), // Q9
        arrayOf("Exits the function", "Prints the value", "Starts the function") // Q10
    )
    private  val correctAnswer = arrayOf(
        1, // Q1 - "Java Virtual Machine" is correct
        1, // Q2 - "To initialize object properties" is correct
        2, // Q3 - "Assembly" is correct
        1, // Q4 - "var" is correct
        2, // Q5 - "Hyper Text Markup Language" is correct
        3, // Q6 - "Both of the above" is correct
        1, // Q7 - "Object Oriented Programming" is correct
        1, // Q8 - "String" is correct
        1, // Q9 - "A list of items" is correct
        1  // Q10 - "Exits the function" is correct
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        displayQuestion()
        binding.button.setOnClickListener{checkAns(0)}
        binding.button2.setOnClickListener{checkAns(1)}
        binding.button3.setOnClickListener{checkAns(2)}

        binding.restartBtn.setOnClickListener{
            restartQuiz()
        }
    }
    private fun correctButtoncolor(buttonIndex: Int){
        when(buttonIndex){
            0->binding.button.setBackgroundColor(Color.GREEN)
            1->binding.button2.setBackgroundColor(Color.GREEN)
            2->binding.button3.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtoncolor(buttonIndex: Int){
        when(buttonIndex){
            0->binding.button.setBackgroundColor(Color.RED)
            1->binding.button2.setBackgroundColor(Color.RED)
            2->binding.button3.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtoncolor(){
        binding.button.setBackgroundColor(Color.rgb(95,158,160))
        binding.button2.setBackgroundColor(Color.rgb(95,158,160))
        binding.button3.setBackgroundColor(Color.rgb(95,158,160))
    }

    private fun showResult(){
        Toast.makeText(this,"You Score: $score out of ${questions.size}", Toast.LENGTH_SHORT).show()
        binding.restartBtn.isEnabled=true
    }
    private fun displayQuestion(){
        binding.textView2.text = questions[currentQuestionIndex]
        binding.button.text = option[currentQuestionIndex][0]
        binding.button2.text = option[currentQuestionIndex][1]
        binding.button3.text = option[currentQuestionIndex][2]
        resetButtoncolor()
    }
    private fun checkAns(selectedAnsIndex:Int){
        val  correctAnsIndex = correctAnswer[currentQuestionIndex]

        if (selectedAnsIndex == correctAnsIndex){
            score++
            correctButtoncolor(selectedAnsIndex)
        }else{
            wrongButtoncolor(selectedAnsIndex)
            correctButtoncolor(correctAnsIndex)
        }
        if (currentQuestionIndex< questions.size-1){
            currentQuestionIndex++
            binding.textView2.postDelayed({displayQuestion()},1000)
        }else{
            showResult()
        }
    }
    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        binding.restartBtn.isEnabled = true
    }

}