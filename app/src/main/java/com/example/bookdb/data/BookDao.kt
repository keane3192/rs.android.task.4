package com.example.bookdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookdb.model.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Query("SELECT * FROM book_table ORDER BY (:sort) ASC")
    fun readAllData(sort: String): LiveData<List<Book>>


    @Query("SELECT * FROM book_table ORDER BY title ASC")
    fun titleASC(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table ORDER BY author ASC")
    fun authorASC(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table ORDER BY genre ASC")
    fun genreASC(): LiveData<List<Book>>

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)


}