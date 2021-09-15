package com.example.bookdb.sql

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookdb.R
import kotlinx.android.synthetic.main.fragment_update_sql.*
import kotlinx.android.synthetic.main.fragment_update_sql.view.*


class UpdateSqlFragment : Fragment() {


    private val args by navArgs<UpdateSqlFragmentArgs>()



    private lateinit var sqLiteHelper: SQLiteHelper



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_sql, container, false)


        sqLiteHelper = SQLiteHelper(requireContext())


        view.upSqlTitle.setText(args.currentItem.Title)
        view.upSqlAuthor.setText(args.currentItem.Author)
        view.upSqlGenre.setText(args.currentItem.Genre)


        view.upSql_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem() {
        val title = upSqlTitle.text.toString()
        val author = upSqlAuthor.text.toString()
        val genre = upSqlGenre.text.toString()



        if (inputCheck(title, author, genre)) {
            val std =
                BookModel(id = args.currentItem.id, Title = title, Author = author, Genre = genre)
            val status = sqLiteHelper.updateBook(std)

            if (status > -1) {
                Toast.makeText(activity, "Added", Toast.LENGTH_SHORT).show()
                findNavController().navigate((R.id.action_updateSqlFragment_to_listSqlFragment))
            } else {
                Toast.makeText(activity, "Record not saved", Toast.LENGTH_SHORT).show()

            }


        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun inputCheck(title: String, author: String, genre: String): Boolean {
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
            sqLiteHelper.deleteBookById(args.currentItem.id)
            Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateSqlFragment_to_listSqlFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentItem.Title}?")
        builder.setMessage("Are you sure you wnt to delete ${args.currentItem.Title}?")
        builder.create().show()
    }


}











