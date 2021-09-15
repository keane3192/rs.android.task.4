package com.example.bookdb.sql

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookdb.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddSqlFragment : Fragment(R.layout.fragment_add_sql) {


    private lateinit var sqLiteHelper: SQLiteHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_sql, container, false)
        sqLiteHelper = SQLiteHelper(requireContext())
        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }
        return view

    }

    private fun insertDataToDatabase() {
        val title = bTitle.text.toString()
        val author = bAuthor.text.toString()
        val genre = bGenre.text.toString()

        if (inputCheck(title, author, genre)) {
            val book = BookModel(Title = title, Author = author, Genre = genre)
            val status = sqLiteHelper.insertBook(book)

            if (status > -1) {
                Toast.makeText(activity, "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate((R.id.action_addSqlFragment_to_listSqlFragment))
            } else {
                Toast.makeText(activity, "Record not saved", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(title: String, author: String, genre: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author) && TextUtils.isEmpty(genre))
    }


}









