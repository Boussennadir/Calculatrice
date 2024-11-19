package com.example.calculator.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculatrice(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
Column {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        TextField(
            value = if (result.isNotEmpty()) result else input ,
            onValueChange = {} ,
            readOnly = true ,
            textStyle = TextStyle(fontSize = 48.sp , color = Color.Black) ,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) ,
            singleLine = true ,
            enabled = false ,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White ,
                disabledTextColor = Color.Black ,
                disabledLabelColor = Color.Transparent
            )
        )
    }
    Spacer(modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp) ,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Display the input or result


        // Calculator buttons
        Column(
            modifier = Modifier
                .fillMaxWidth() ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First row (AC, %, /, ×)
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "AC" ,
                    onClick = { input = ""; result = "" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "%" ,
                    onClick = { input += "%" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "/" ,
                    onClick = { input += "/" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "×" ,
                    onClick = { input += "*" } ,
                    modifier = Modifier.weight(1f)
                )
            }

            // Second row (7, 8, 9, -)
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "7" ,
                    onClick = { input += "7" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "8" ,
                    onClick = { input += "8" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "9" ,
                    onClick = { input += "9" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "-" ,
                    onClick = { input += "-" } ,
                    modifier = Modifier.weight(1f)
                )
            }

            // Third row (4, 5, 6, +)
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "4" ,
                    onClick = { input += "4" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "5" ,
                    onClick = { input += "5" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "6" ,
                    onClick = { input += "6" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "+" ,
                    onClick = { input += "+" } ,
                    modifier = Modifier.weight(1f)
                )
            }

            // Fourth row (1, 2, 3, =)
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "1" ,
                    onClick = { input += "1" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "2" ,
                    onClick = { input += "2" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(
                    text = "3" ,
                    onClick = { input += "3" } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(text = "=" , onClick = {
                    val expression = input.replace("×" , "*").replace("/" , "/")
                    try {
                        val calculatedResult = evaluateExpression(expression)
                        result = calculatedResult.toString()
                    } catch (e : Exception) {
                        result = "Error"
                    }
                } , modifier = Modifier.weight(1f))
            }

            // Fifth row (0, ., +/-, ±)
            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "0" ,
                    onClick = { input += "0" } ,
                    modifier = Modifier.weight(2f)
                )
                CalculatorButton(
                    text = "." ,
                    onClick = { input += "." } ,
                    modifier = Modifier.weight(1f)
                )
                CalculatorButton(text = "±" , onClick = {
                    if (input.isNotEmpty() && input[0] == '-') {
                        input = input.drop(1)
                    } else {
                        input = "-$input"
                    }
                } , modifier = Modifier.weight(1f))
            }
        }
    }
}
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(0.dp),
        shape = RoundedCornerShape(CornerSize(40.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFf0f0f5)),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 28.sp, color = Color.Black)
        )
    }
}

// Expression evaluation logic
private fun evaluateExpression(expression: String): Double {
    var result = 0.0
    var currentNumber = ""
    var currentOperator = '+'

    for (char in expression) {
        if (char.isDigit() || char == '.') {
            currentNumber += char
        } else {
            val number = currentNumber.toDouble()
            when (currentOperator) {
                '+' -> result += number
                '-' -> result -= number
                '*' -> result *= number
                '/' -> result /= number
            }
            currentNumber = ""
            currentOperator = char
        }
    }

    // Handle the last number and operator
    val lastNumber = currentNumber.toDouble()
    when (currentOperator) {
        '+' -> result += lastNumber
        '-' -> result -= lastNumber
        '*' -> result *= lastNumber
        '/' -> result /= lastNumber
    }

    return result
}
