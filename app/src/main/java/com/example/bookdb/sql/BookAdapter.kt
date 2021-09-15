package com.example.bookdb.sql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdb.R
import kotlinx.android.synthetic.main.custom_row.view.*

class BookAdapter : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    private var bookList: ArrayList<BookModel> = ArrayList()


    fun addItems(items: ArrayList<BookModel>) {
        this.bookList = items
        notifyDataSetChanged()
    }


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
            val action =
                ListSqlFragmentDirections.actionListSqlFragmentToUpdateSqlFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }


    }


    override fun getItemCount(): Int {
        return bookList.size
    }

    /*
    class StudentViewHolder (var view: View): RecyclerView.ViewHolder(view){
        private var title = view.findViewById<TextView>(R.id.lTitle)
        private var author = view.findViewById<TextView>(R.id.lAuthor)
        private var genre = view.findViewById<TextView>(R.id.lGenre)




        fun bindView(std: BookModel){

            title.text = std.Title
            author.text = std.Author
            genre.text = std.Genre




        }


    }
    */


}


