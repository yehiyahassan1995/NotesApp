package com.yehiya.notesapplication

import androidx.lifecycle.LiveData
import androidx.room.*

/*
DAO is a data access object which is used to specify SQL queries and then associate them with different method calls.
DAO may be an abstract class or an interface.
Inside the DAO class, we have to create different methods such as inserting, deleting the data, and reading the data from our database.
So this class will basically interact with our database to add or delete data inside our database.
*/
@Dao
interface NotesDao {

    // below is the insert method for
    // adding a new entry to our database.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    // below is the delete method
    // for deleting our note.

    @Delete
    suspend fun delete(note: Note)

    // below is the method to read all the notes
    // from our database we have specified the query for it.
    // inside the query we are arranging it in ascending
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data.

    @Query("select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    // below method is use to update the note.
    @Update
    suspend fun update(note: Note)


}