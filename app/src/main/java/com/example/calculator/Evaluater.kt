package com.example.calculator
import net.objecthunter.exp4j.ExpressionBuilder

fun evaluateExpression(expression1: String): String {
  val expression = expression1.replace("√", "sqrt")
    return try {
        val result = ExpressionBuilder(expression).build().evaluate()
        if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
    } catch (e: Exception) {
        "Error"
    }
}