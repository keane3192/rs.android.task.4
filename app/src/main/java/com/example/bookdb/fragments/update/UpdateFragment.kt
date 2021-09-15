package com.example.bookdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookdb.R
import com.example.bookdb.model.Book
import com.example.bookdb.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {


    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mBookViewModel: BookViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)


        view.upTitle.setText(args.currentItem.Title)
        view.upAuthor.setText(args.currentItem.Author)
        view.upGenre.setText(args.currentItem.Genre)


        view.up_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem() {
        val title = upTitle.text.toString()
        val author = upAuthor.text.toString()
        val genre = upGenre.text.toString()

        if (inputCheck(title, author, genre)) {
            val updatedBook = Book(args.currentItem.id, title, author, genre)

            mBookViewModel.updateBook(updatedBook)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(title: String, author: String, genre: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author) && TextUtils.isEmpty(genre))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteBook()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mBookViewModel.deleteBook(args.currentItem)
            Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentItem.Title}?")
        builder.setMessage("Are you sure you wnt to delete ${args.currentItem.Title}?")
        builder.create().show()
    }


}