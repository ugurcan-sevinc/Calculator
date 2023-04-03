package com.ugrcaan.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ugrcaan.calculator.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set click listeners for buttons
        binding.clearAllBtn.setOnClickListener(this)
        binding.paranthesesBtn.setOnClickListener(this)
        binding.percentBtn.setOnClickListener(this)
        binding.divideBtn.setOnClickListener(this)
        binding.multiplyBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
        binding.plusBtn.setOnClickListener(this)
        binding.equalBtn.setOnClickListener(this)
        binding.dotBtn.setOnClickListener(this)
        binding.backspaceBtn.setOnClickListener(this)
        binding.zeroBtn.setOnClickListener(this)
        binding.oneBtn.setOnClickListener(this)
        binding.twoBtn.setOnClickListener(this)
        binding.threeBtn.setOnClickListener(this)
        binding.fourBtn.setOnClickListener(this)
        binding.fiveBtn.setOnClickListener(this)
        binding.sixBtn.setOnClickListener(this)
        binding.sevenBtn.setOnClickListener(this)
        binding.eightBtn.setOnClickListener(this)
        binding.nineBtn.setOnClickListener(this)
    }


    override fun onClick(view: View) {
        val inputTextView = binding.operationsTV
        val outputTextView = binding.resultTV
        val calculator = Calculator()

        when (view.id) {
            R.id.clearAllBtn -> {
                inputTextView.text = ""
                outputTextView.text = ""
            }
            R.id.paranthesesBtn -> {
                val input = inputTextView.text.toString()
                val lastChar = if (input.isNotEmpty()) input.last().toString() else ""

                // check if the last character is a closing parenthesis or a number
                if (lastChar != ")" && !lastChar.matches(Regex("\\d"))) {
                    // add an opening parenthesis
                    inputTextView.append("(")
                } else {
                    // add a closing parenthesis
                    val openParanthesisCount = input.count { it == '(' }
                    val closeParanthesisCount = input.count { it == ')' }
                    if (openParanthesisCount > closeParanthesisCount) {
                        inputTextView.append(")")
                    }
                }
            }
            R.id.percentBtn -> {
                inputTextView.append("%")
            }
            R.id.divideBtn -> {
                inputTextView.append("/")
            }
            R.id.multiplyBtn -> {
                inputTextView.append("*")
            }
            R.id.minusBtn -> {
                inputTextView.append("-")
            }
            R.id.plusBtn -> {
                inputTextView.append("+")
            }
            R.id.equalBtn -> {
                if(inputTextView.text == "") outputTextView.text = "0"
                else {
                    val expression = inputTextView.text.toString()
                    try {
                        val result = calculator.evaluateExpression(expression)
                        outputTextView.text = result.toString()
                    } catch (e: IllegalArgumentException) {
                        outputTextView.text = "Error"
                    }
                }

            }
            R.id.dotBtn -> {
                inputTextView.append(".")
            }
            R.id.backspaceBtn -> {
                val input = inputTextView.text.toString()
                if (input.isNotEmpty()) {
                    inputTextView.text = input.substring(0, input.length - 1)
                }
            }
            R.id.zeroBtn -> {
                inputTextView.append("0")
            }
            R.id.oneBtn -> {
                inputTextView.append("1")
            }
            R.id.twoBtn -> {
                inputTextView.append("2")
            }
            R.id.threeBtn -> {
                inputTextView.append("3")
            }
            R.id.fourBtn -> {
                inputTextView.append("4")
            }
            R.id.fiveBtn -> {
                inputTextView.append("5")
            }
            R.id.sixBtn -> {
                inputTextView.append("6")
            }
            R.id.sevenBtn -> {
                inputTextView.append("7")
            }
            R.id.eightBtn -> {
                inputTextView.append("8")
            }
            R.id.nineBtn -> {
                inputTextView.append("9")
            }
        }
    }



}