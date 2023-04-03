package com.ugrcaan.calculator

import java.util.*

class Calculator {
    private val operandStack = Stack<Double>()
    private val operatorStack = Stack<Char>()

    private fun tokenizeExpression(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        var numberBuffer = StringBuilder()

        for (i in expression.indices) {
            val c = expression[i]

            if (c.isDigit() || c == '.') {
                numberBuffer.append(c)
            } else {
                if (numberBuffer.isNotEmpty()) {
                    tokens.add(numberBuffer.toString())
                    numberBuffer = StringBuilder()
                }
                if (c == '%') {
                    tokens.add("%")
                }
                tokens.add(c.toString())
            }
        }

        if (numberBuffer.isNotEmpty()) {
            tokens.add(numberBuffer.toString())
        }

        return tokens
    }

    private fun hasHigherPrecedence(op1: Char, op2: Char): Boolean {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')
                || (op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-')
                || (op1 == '*' || op1 == '/') && (op2 == '*' || op2 == '/')
                || (op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')
    }

    private fun performOperation(operands: Stack<Double>, operator: Char) {
        val operand2 = operands.pop()
        val operand1 = operands.pop()

        val result = when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            '*' -> operand1 * operand2
            '/' -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }

        operands.push(result)
    }

    private fun String.isNumeric(): Boolean = this in "0".."9" || this == "."
    private fun String.isOperator(): Boolean = this.length == 1 && this[0] in "+-*/"
    private fun String.isPercent(): Boolean = this == "%"

    private fun applyPercent(operands: Stack<Double>) {
        val operand = operands.pop()
        operands.push(operand / 100.0)
    }

    fun evaluateExpression(expression: String): Double {
        val tokens = tokenizeExpression(expression)

        tokens.forEach {
            when {
                it.isNumeric() -> operandStack.push(it.toDouble())
                it.isOperator() -> {
                    while (operatorStack.isNotEmpty() && hasHigherPrecedence(operatorStack.peek(), it[0])) {
                        performOperation(operandStack, operatorStack.pop())
                    }
                    operatorStack.push(it[0])
                }
                it.isPercent() -> {
                    applyPercent(operandStack)
                }
                it == "(" -> operatorStack.push(it[0])
                it == ")" -> {
                    while (operatorStack.isNotEmpty() && operatorStack.peek() != '(') {
                        performOperation(operandStack, operatorStack.pop())
                    }
                    if (operatorStack.isNotEmpty() && operatorStack.peek() == '(') {
                        operatorStack.pop()
                    } else {
                        throw IllegalArgumentException("Invalid expression: mismatched parentheses")
                    }
                }
                else -> throw IllegalArgumentException("Invalid expression: unknown token $it")
            }
        }

        while (operatorStack.isNotEmpty()) {
            if (operatorStack.peek() == '(') {
                throw IllegalArgumentException("Invalid expression: mismatched parentheses")
            }
            performOperation(operandStack, operatorStack.pop())
        }

        if (operandStack.size != 1) {
            throw IllegalArgumentException("Invalid expression")
        }

        return operandStack.pop()
    }
}
