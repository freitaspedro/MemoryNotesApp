package com.example.core.usecase

import com.example.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note) = count(note.title) + count(note.content)

    private fun count(words: String) =
        words.split(" ", " \n").count { it.contains(Regex(".*[a-zA-Z].*")) }

}