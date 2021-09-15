package com.example.bookdb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdb.R
import com.example.bookdb.model.Book
import kotlinx.android.synthetic.main.custom_row.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var bookList = emptyList<Book>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.lTitle.text = currentItem.Title
        holder.itemView.lAuthor.text = currentItem.Author
        holder.itemView.lGenre.text = currentItem.Genre

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }


    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(book: List<Book>) {
        this.bookList = book
        notifyDataSetChanged()

    }


}