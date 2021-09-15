package com.example.bookdb.repository

import androidx.lifecycle.LiveData
import com.example.bookdb.data.BookDao
import com.example.bookdb.model.Book

class BookRepository(private val bookDao: BookDao) {
    val readAllData: LiveData<List<Book>> = bookDao.readAllData("genre")
    val titleASC: LiveData<List<Book>> = bookDao.titleASC()
    val authorASC: LiveData<List<Book>> = bookDao.authorASC()
    val genreASC: LiveData<List<Book>> = bookDao.genreASC()


    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }


    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }


}