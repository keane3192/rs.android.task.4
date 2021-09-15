package com.example.bookdb.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookdb.R
import com.example.bookdb.model.Book
import com.example.bookdb.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
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
            val book = Book(0, title, author, genre)

            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()
            findNavController().navigate((R.id.action_addFragment_to_listFragment))
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(title: String, author: String, genre: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author) && TextUtils.isEmpty(genre))
    }


}