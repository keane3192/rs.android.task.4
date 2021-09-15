package com.example.bookdb.sql

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdb.R
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListSqlFragment : Fragment() {

    private lateinit var sqLiteHelper: SQLiteHelper

    private var adapter = BookAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_sql, container, false)

        val recyclerView = view.recyclerview

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        sqLiteHelper = SQLiteHelper(requireContext())

        setData()
        setHasOptionsMenu(true)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listSqlFragment_to_addSqlFragment)
        }

        return view
    }


    private fun setData() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val res = prefs.getString("list", "Title")
        if (res == "Author") {
            val bookList = sqLiteHelper.getAllBook("Author")
            Log.e("pppp", "${bookList.size}")
            adapter.addItems(bookList)

            adapter.notifyDataSetChanged()
            Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()

        } else if (res == "Genre") {

            val bookList = sqLiteHelper.getAllBook("Genre")
            Log.e("pppp", "${bookList.size}")
            adapter.addItems(bookList)

            adapter.notifyDataSetChanged()
            Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()

        } else {
            val bookList = sqLiteHelper.getAllBook("Title")
            Log.e("pppp", "${bookList.size}")
            adapter.addItems(bookList)

            adapter.notifyDataSetChanged()
            Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()


        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
            findNavController().navigate(R.id.action_listSqlFragment_to_settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }


}