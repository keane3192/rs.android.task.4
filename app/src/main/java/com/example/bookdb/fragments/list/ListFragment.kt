package com.example.bookdb.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdb.R
import com.example.bookdb.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private val adapter = ListAdapter()
    private lateinit var mBookViewModel: BookViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val res = prefs.getString("database", "Room")
        if (res == "SQLite") {
            findNavController().navigate(R.id.action_listFragment_to_listSqlFragment)
        }

        val view = inflater.inflate(R.layout.fragment_list, container, false)


        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setData()
        setHasOptionsMenu(true)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    private fun setData() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        val res = prefs.getString("list", "Title")

        when (res) {
            "Author" -> {

                mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
                mBookViewModel.authorASC.observe(viewLifecycleOwner, Observer { book ->
                    adapter.setData(book)
                })
                adapter.notifyDataSetChanged()
                Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()

            }
            "Genre" -> {

                mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
                mBookViewModel.genreASC.observe(viewLifecycleOwner, Observer { book ->
                    adapter.setData(book)
                })
                adapter.notifyDataSetChanged()
                Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()

            }
            else -> {
                mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
                mBookViewModel.titleASC.observe(viewLifecycleOwner, Observer { book ->
                    adapter.setData(book)
                })
                Toast.makeText(activity, "$res", Toast.LENGTH_SHORT).show()


            }
        }


    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
            findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }


}