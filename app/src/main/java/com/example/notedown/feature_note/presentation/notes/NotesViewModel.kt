package com.example.notedown.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notedown.feature_note.domain.model.Note
import com.example.notedown.feature_note.domain.use_case.NotesUseCases
import com.example.notedown.feature_note.domain.util.NoteOrder
import com.example.notedown.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NotesUseCases
) : ViewModel() {
     private val _state = mutableStateOf(NotesState())
     val state: State<NotesState> = _state

     private var recentlyDeletedNote: Note? = null
     private var getNotesJob: Job? =null
     init {
         getNotes(NoteOrder.Date(OrderType.Descending))  // for default
     }

     fun onEvent(event: NotesEvent){
          when(event){
               is NotesEvent.Order -> {
                    if(state.value.noteOrder::class == event.noteOrder::class &&
                            state.value.noteOrder.orderType == event.noteOrder.orderType){
                         return
                    }
                    getNotes(event.noteOrder)
               }
               is NotesEvent.DeleteNote -> {
                    viewModelScope.launch {
                         noteUseCases.deleteNotesUseCase(event.note)
                         recentlyDeletedNote = event.note //we just saved reference of that object
                    }
               }
               is NotesEvent.RestoreNote -> {
                    viewModelScope.launch {
                         noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                         recentlyDeletedNote = null
                    }
               }
               is NotesEvent.ToggleOrderSection -> {
                   _state.value = state.value.copy(
                        isOrderSectionVisible = !state.value.isOrderSectionVisible
                   )
               }
          }

     }
     private fun getNotes(notesOrder: NoteOrder){
          getNotesJob?.cancel()
          getNotesJob = noteUseCases.getNotesUseCase(notesOrder).onEach { notes ->
               _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = notesOrder
               )
          }.launchIn(viewModelScope)
     }
}