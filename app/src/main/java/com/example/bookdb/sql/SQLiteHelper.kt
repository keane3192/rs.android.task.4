package com.example.bookdb.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {


        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "book_database"
        private const val TBL_BOOK = "book_table"

        private const val ID = "id"
        private const val TITLE = "title"
        private const val AUTHOR = "author"
        private const val GENRE = "genre"


    }


    fun insertBook(book: BookModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, book.id)
        contentValues.put(TITLE, book.Title)
        contentValues.put(AUTHOR, book.Author)
        contentValues.put(GENRE, book.Genre)

        val succes = db.insert(TBL_BOOK, null, contentValues)
        db.close()
        return succes
    }

    @SuppressLint("Recycle")
    fun getAllBook(sort: String): ArrayList<BookModel> {
        val bookList: ArrayList<BookModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_BOOK ORDER BY $sort ASC"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()

        }
        var id: Int
        var title: String
        var author: String
        var genre: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                title = cursor.getString(cursor.getColumnIndex("Title"))
                author = cursor.getString(cursor.getColumnIndex("Author"))
                genre = cursor.getString(cursor.getColumnIndex("Genre"))

                val book = BookModel(id = id, Title = title, Author = author, Genre = genre)
                bookList.add(book)

            } while (cursor.moveToNext())

        }
        return bookList

    }

    fun updateBook(book: BookModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, book.id)
        contentValues.put(TITLE, book.Title)
        contentValues.put(AUTHOR, book.Author)
        contentValues.put(GENRE, book.Genre)

        val success = db.update(TBL_BOOK, contentValues, "id=" + book.id, null)
        db.close()
        return success

    }

    fun deleteBookById(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_BOOK, "id=$id", null)
        db.close()
        return success


    }

    override fun onCreate(db: SQLiteDatabase?) {


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

}