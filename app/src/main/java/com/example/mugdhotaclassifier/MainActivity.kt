package com.example.mugdhotaclassifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mugdhotaclassifier.ui.theme.MugdhotaClassifierTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MugdhotaClassifierTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInputScreen(
                        modifier = Modifier.padding(innerPadding),
                        onProcessText = ::processTextInput
                    )
                }
            }
        }
    }


    // This is the function which is called on each button Press
    // ........................................................//
    private fun processTextInput(input: String): String {
        return "You entered: $input"
    }
}

@Composable
fun TextInputScreen(
    modifier: Modifier = Modifier,
    onProcessText: (String) -> String
) {
    var textInput by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { resultText = onProcessText(textInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        if (resultText.isNotEmpty()) {
            Text(
                text = resultText,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextInputScreenPreview() {
    MugdhotaClassifierTheme {
        TextInputScreen(onProcessText = { "You entered: $it" })
    }
}