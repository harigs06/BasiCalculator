package com.example.calculator.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ButtonContent
import com.example.calculator.evaluateExpression


@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }

    val buttons = listOf(
        ButtonContent("CE", buttonColor2 ){  text = ""},
        ButtonContent("x", buttonColor2){text = if(text == "Error") "" else if(text.isNotEmpty()) text.dropLast(1) else text },
        ButtonContent("%", buttonColor2){if(text == "Error") text = "%" else text += "%"},
        ButtonContent("/", buttonColor2){if(text == "Error") text = "/" else text += "/"},
        ButtonContent("7", buttonColor1){if(text == "Error") text = "7" else text += "7"},
        ButtonContent("8", buttonColor1){if(text == "Error") text = "8" else text += "8"},
        ButtonContent("9", buttonColor1){if(text == "Error") text = "9" else text += "9"},
        ButtonContent("*", buttonColor2){if(text == "Error") text = "*" else text += "*"},
        ButtonContent("4", buttonColor1){if(text == "Error") text = "4" else text += "4"},
        ButtonContent("5", buttonColor1){if(text == "Error") text = "5" else text += "5"},
        ButtonContent("6", buttonColor1){if(text == "Error") text = "6" else text += "6"},
        ButtonContent("-", buttonColor2){if(text == "Error") text = "-" else text += "-"},
        ButtonContent("1", buttonColor1){if(text == "Error") text = "1" else text += "1"},
        ButtonContent("2", buttonColor1){if(text == "Error") text = "2" else text += "2"},
        ButtonContent("3", buttonColor1){if(text == "Error") text = "3" else text += "3"},
        ButtonContent("+", buttonColor2 ){if(text == "Error") text = "+" else text += "+"},
        ButtonContent("0", buttonColor1){if(text == "Error") text = "0" else text += "0"},
        ButtonContent("√", buttonColor1){if(text == "Error") text = "√" else text += "√"},
        ButtonContent(".", buttonColor1){
            if (text.isEmpty()) {
                text += "0."
            } else {
                val lastNumber = text.split(Regex("[+\\-*/]")).last()
                if (!lastNumber.contains(".")) {
                    text += "."
                }
            }
            },
        ButtonContent("=", buttonColor2){ text = evaluateExpression(text) },

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(15.dp)
    ) {
        // Top Part - 45% of the height
        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            UpsidePart(
                text = text,
                modifier = Modifier.fillMaxSize(),
                onTextChange = { text = it }
            )
        }

        // Bottom Part - 55% of the height
        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomPart(
                buttons = buttons,
                onKeyPress = { button -> button.onClick() }
            )
        }
    }
}

@Composable
fun UpsidePart(
    modifier :Modifier = Modifier,
    text : String,
    onTextChange : (String) -> Unit
){

    Box(
        modifier = modifier.
        fillMaxSize().
        padding(15.dp)
    ){
        Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(100.dp)){
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                readOnly = false,
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier.
                    fillMaxSize().
                padding(12.dp).
                focusable(false),
                textStyle = TextStyle(
                    fontSize = 28.sp,
                    color = Color.White,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun BottomPart(
    modifier: Modifier = Modifier,
    buttons : List<ButtonContent>,
    onKeyPress : (ButtonContent) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier.padding(4.dp),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
         items(buttons.size){
             IndividualButton(
                 button = buttons[it],
                 modifier = Modifier.width(80.dp).
                 height(80.dp).
                 clip(RoundedCornerShape(15.dp)),
                 onClick = onKeyPress
             )
         }
    }
}

@Composable
fun IndividualButton(
    modifier: Modifier = Modifier,
    button: ButtonContent,
    onClick: (ButtonContent) -> Unit
){
        Box(modifier = modifier.
            fillMaxSize().
            background(button.color).
            padding(14.dp).

            clickable { onClick (button)},
            contentAlignment = Alignment.Center){
            Text(text = button.text ,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
                )
        }
}