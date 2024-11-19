package com.example.calculator.ui.theme


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.calculator.R

@Composable
fun Calculatrice(modifier : Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Box(modifier.fillMaxWidth().height(200.dp)) {
                TextField(
                    value = if (result.isNotEmpty()) result else input,
                    onValueChange = {} ,
                    readOnly = true ,
                    modifier = Modifier.fillMaxSize()
                )
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth() ,
            verticalArrangement = Arrangement.Bottom ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        input=""
                        result=""
                }) {
                    Text(text = "AC")
                }

                Button(onClick = {input+=""}) {
                    Text(text = "%")
                }

                Button(onClick = {input=input.dropLast(1)}) {
                    Icon(
                        painter = painterResource(id = R.drawable.backspace_24dp_5f6368) ,
                        contentDescription = ""
                    )
                }

                Button(onClick = {input+="÷"}) {
                    Text(text = "÷")
                }

            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = {input+="7"}) {
                    Text(text = "7")
                }


                Button(onClick = {input+="8"}) {
                    Text(text = "8")
                }


                Button(onClick = {input+="9"}) {
                    Text(text = "9")
                }


                Button(onClick = {input+="x"}) {
                    Text(text = "x")
                }

            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = {input+="4"}) {
                    Text(text = "4")
                }


                Button(onClick = {input+="5"}) {
                    Text(text = "5")
                }


                Button(onClick = {input+="6"}) {
                    Text(text = "6")
                }

                Button(onClick = {input+="-"}) {
                    Text(text = "-")
                }

            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = {input+="1"}) {
                    Text(text = "1")
                }


                Button(onClick = {input+="2"}) {
                    Text(text = "2")
                }


                Button(onClick = {input+="3"}) {
                    Text(text = "3")
                }


                Button(onClick = {input+="+"}) {
                    Text(text = "+")
                }

            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth() , horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {input+="00"}) {
                    Text(text = "00")
                }

                Button(onClick = {input+="0"}) {
                    Text(text = "0")
                }


                Button(onClick = {input+="."}) {
                    Text(text = ".")

                }

                Button(onClick = {
                    val expression = input.replace("x", "*").replace("÷", "/")
                    try {
                        val calculatedResult = evaluateExpression(expression)
                        result = calculatedResult.toString()
                    } catch (e: Exception) {
                        result = "Error"
                    }
                }) {
                    Text(text = "=")
                }

            }

        }
    }
}

private fun evaluateExpression(expression: String): Double {
    // Implement your expression evaluation logic here
    // You can use a library like "kotlinx-math" or write your own parser
    // For a simple example, you could use a basic approach:

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


