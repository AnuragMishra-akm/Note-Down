package com.example.notedown.feature_note.domain.use_case

import com.example.notedown.feature_note.domain.model.Note
import com.example.notedown.feature_note.domain.repository.NoteRepository
import com.example.notedown.feature_note.domain.util.NoteOrder
import com.example.notedown.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase (
    private val repository : NoteRepository // make sure this is interface type , not implementation type , otherwise it will not replaceable)
){
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {  //Operator function That will override the invoke operator so we can call our class like a function in the end
        return repository.getAllNotes().map { notes ->
            when (noteOrder.orderType) {
                 is OrderType.Ascending -> {
                     when(noteOrder){
                         is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                         is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                         is NoteOrder.Color -> notes.sortedBy { it.color }
                     }
                 }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}