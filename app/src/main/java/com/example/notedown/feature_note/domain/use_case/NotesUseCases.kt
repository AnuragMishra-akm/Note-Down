package com.example.notedown.feature_note.domain.use_case

data class NotesUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNotesUseCase: DeleteNotesUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)
