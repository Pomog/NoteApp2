package com.example.noteapp2.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.noteapp2.model.Note
import java.time.LocalDateTime

class NoteDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): List<Note> {
        val now = LocalDateTime.now()

        return listOf(
            Note(
                title = "Buy groceries",
                description = "Milk, eggs, bread, vegetables",
                entryDate = now.minusDays(0)
            ),
            Note(
                title = "Workout",
                description = "30 min cardio + 15 min stretching",
                entryDate = now.minusDays(1)
            ),
            Note(
                title = "Read Kotlin docs",
                description = "Focus on data classes, sealed classes, and collections",
                entryDate = now.minusDays(2)
            ),
            Note(
                title = "Compose practice",
                description = "Create a simple list screen using LazyColumn",
                entryDate = now.minusDays(3)
            ),
            Note(
                title = "Call dentist",
                description = "Schedule appointment for next week",
                entryDate = now.minusDays(4)
            ),
            Note(
                title = "Study Git",
                description = "Review branches, merge vs rebase, and tags",
                entryDate = now.minusDays(5)
            ),
            Note(
                title = "Project cleanup",
                description = "Remove unused imports, rename packages, organize files",
                entryDate = now.minusDays(6)
            ),
            Note(
                title = "Plan weekend",
                description = "Decide on trip, shopping, and rest time",
                entryDate = now.minusDays(7)
            ),
            Note(
                title = "Fix emulator issue",
                description = "Check virtualization + AVD config",
                entryDate = now.minusDays(8)
            ),
            Note(
                title = "Learn Room basics",
                description = "Entity, DAO, Database + simple insert/read",
                entryDate = now.minusDays(9)
            )
        )
    }
}