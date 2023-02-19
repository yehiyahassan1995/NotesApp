package com.yehiya.notesapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
What is basically a Room Database?

Room is basically a database layer on top of the SQLite database.
Room takes care of mundane tasks that you used to handle with an SQLite Open Helper.
Room uses the DAO to issue queries to its database.
Room provides compile-time checks of SQLite statements.
*/
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDataBase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java, "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}