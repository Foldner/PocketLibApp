package com.example.pocketlibapp.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketlibapp.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var newBookLine = mutableListOf<BookLine>()
        var newBooks = mutableListOf<BookCard>()
        newBooks.add(BookCard("автор1", "Название1", ""))
        newBooks.add(BookCard("автор2", "Название2", ""))
        newBooks.add(BookCard("автор3", "Название3", ""))
        newBooks.add(BookCard("автор4", "Название4", ""))
        newBooks.add(BookCard("автор5", "Название5", ""))
        newBookLine.add(BookLine("НОВИНКИ", newBooks))

        newBooks.clear()
        newBooks.add(BookCard("автор1", "Название1", ""))
        newBooks.add(BookCard("автор2", "Название2", ""))
        newBooks.add(BookCard("автор3", "Название3", ""))
        newBooks.add(BookCard("автор4", "Название4", ""))
        newBooks.add(BookCard("автор5", "Название5", ""))
        newBookLine.add(BookLine("ПОПУЛЯРНОЕ", newBooks))

        var recyclerNewLineBooks: RecyclerView = view.findViewById(R.id.recyclerNewLineBooks)

        recyclerNewLineBooks.layoutManager = GridLayoutManager(view.context, newBookLine.size)
        val verticalLayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerNewLineBooks.layoutManager = verticalLayoutManager
        recyclerNewLineBooks.adapter = CustomRecyclerAdapterLine(newBookLine)

        var recyclerViewAllBooks: RecyclerView = view.findViewById(R.id.recyclerAllBooks)
        recyclerViewAllBooks.layoutManager = GridLayoutManager(view.context, 2)
        recyclerViewAllBooks.adapter = CustomRecyclerAdapter2(newBooks)

    }

}

class CustomRecyclerAdapter(private val names: List<BookCard>) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.text_name)
        val authorText: TextView = itemView.findViewById(R.id.text_author)
        val imageUrl: ImageView = itemView.findViewById(R.id.imgBookCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameText.text = names[position].name
        holder.authorText.text = names[position].author
    }

    override fun getItemCount() = names.size
}

class CustomRecyclerAdapter2(private val names: List<BookCard>) : RecyclerView
.Adapter<CustomRecyclerAdapter2.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.text_name)
        val authorText: TextView = itemView.findViewById(R.id.text_author)
        val imageUrl: ImageView = itemView.findViewById(R.id.imgBookCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_card2, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameText.text = names[position].name
        holder.authorText.text = names[position].author
    }

    override fun getItemCount() = names.size
}


class CustomRecyclerAdapterLine(private val names: List<BookLine>) : RecyclerView
.Adapter<CustomRecyclerAdapterLine.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerText: TextView = itemView.findViewById(R.id.book_line_header)
        val bookItems: RecyclerView = itemView.findViewById(R.id.recyclerNewBooks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_line, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.headerText.text = names[position].header

        holder.bookItems.layoutManager = GridLayoutManager(holder.itemView.context, names[position].books.size)
        val horizontalLayoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.bookItems.layoutManager = horizontalLayoutManager
        holder.bookItems.adapter = CustomRecyclerAdapter(names[position].books)
    }

    override fun getItemCount() = names.size
}
