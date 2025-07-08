package com.example.notedown.feature_note.data.repository

import com.example.notedown.feature_note.data.data_source.NoteDao
import com.example.notedown.feature_note.domain.model.Note
import com.example.notedown.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplementation(private val dao: NoteDao): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
       return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}