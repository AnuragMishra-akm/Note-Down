package com.example.notedown.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notedown.ui.theme.IceBlueLight
import com.example.notedown.ui.theme.LightGreenDark
import com.example.notedown.ui.theme.LightGreenLight
import com.example.notedown.ui.theme.RedOrangeDark
import com.example.notedown.ui.theme.RedOrangeLight
import com.example.notedown.ui.theme.RedPinkDark
import com.example.notedown.ui.theme.VioletDark
import com.example.notedown.ui.theme.VioletLight

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors = listOf(RedOrangeLight, RedOrangeDark,LightGreenLight, LightGreenDark,
            VioletLight,VioletLight, VioletDark, IceBlueLight,
            RedPinkDark
        )
    }
}

class InvalidNodeException(message: String): Exception(message)
