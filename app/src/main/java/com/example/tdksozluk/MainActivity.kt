package com.example.tdksozluk

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tdksozluk.ui.theme.TDKSozlukTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDKSozlukTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(9f)
                .background(Color(red = 226, green = 237, blue = 255))
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 0.dp,
                        color = Color(red = 14, green = 14, blue = 103),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = Color(red = 14, green = 14, blue = 103),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(all = 30.dp)
            )
            {
                Search()
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .fillMaxSize(),
        ) {
            bottomNavigation()
        }
    }
}

@Composable
fun Search() {
    var text by remember {
        mutableStateOf("Bir kelime girin...")
    }
    OutlinedTextField(value = text, onValueChange = { newText ->
        text = newText
    },
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.background(Color(red = 226, green = 237, blue = 255))
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {}
        ),
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(
                width = 0.dp,
                color = Color(0x00000000), RoundedCornerShape(20.dp)
            )
            .onFocusChanged { focusState ->
                when {
                    focusState.isFocused ->
                        text = ""
                }
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0x00000000),
            textColor = Color(red = 171, green = 171, blue = 171)
        )

    )
}

val listItems = listOf("Kelime Ara", "Geçmiş", "Favorilerim")

@Composable
fun bottomNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation(
        modifier = Modifier.fillMaxSize(),
        contentColor = Color(79, 0, 148),
        backgroundColor = Color.White,
    ) {
        listItems.forEachIndexed { index, label -> //Her bir list elemanı için 1 Bottom Navigation Item oluşturuyoruz.
            BottomNavigationItem(
                icon = {
                    Icon(
                        tint = when {
                            selectedIndex == index -> Color(79, 0, 148)
                            else -> Color.Gray
                        },
                        modifier = Modifier.fillMaxWidth(),
                        imageVector = when {
                            label == "Kelime Ara" -> Icons.Filled.Search
                            label == "Favorilerim" -> Icons.Filled.Star
                            label == "Geçmiş" -> Icons.Filled.DateRange
                            else -> Icons.Filled.Check
                        },
                        contentDescription = "Favorite"
                    )
                },
                label = {
                    Text(
                        text = label, color = when {
                            selectedIndex == index -> Color(79, 0, 148)
                            else -> Color.Gray
                        }
                    )
                }, //List elemanının label'ı Item'ın label'ı olarak atanıyor.
                selected = selectedIndex == index, //Item'ın seçili olup olmadığını buradan tespit ediliyor.
                onClick = { selectedIndex = index }
            ) // Bu Item'a tıklandığında bu Item'ın index'i, selectedIndex olarak atanacak.
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TDKSozlukTheme {
        Greeting()
    }
}