package com.example.noteapp2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp2.data.NoteDataSource
import com.example.noteapp2.screen.NoteScreen
import com.example.noteapp2.ui.theme.NoteApp2Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApp2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NoteScreen(
                        notes = NoteDataSource().loadNotes(),
                        onAddNote = {},
                        onRemoveNote = {},
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteApp2Theme {
        NoteScreen(
            notes = emptyList(),
            onAddNote = {},
            onRemoveNote = {},
        )
    }
}