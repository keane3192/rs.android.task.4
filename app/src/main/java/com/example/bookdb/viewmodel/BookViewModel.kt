package com.example.bookdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookdb.data.BookDatabase
import com.example.bookdb.model.Book
import com.example.bookdb.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Book>>
    val titleASC: LiveData<List<Book>>
    val authorASC: LiveData<List<Book>>
    val genreASC: LiveData<List<Book>>
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
        titleASC = repository.titleASC
        authorASC = repository.authorASC
        genreASC = repository.genreASC

    }


    /*
    fun mysort(par: String){
        repository.sortBy(par)
    }

     */

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }

    }

    fun updateBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(book)
        }

    }

    fun deleteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }

    }


}