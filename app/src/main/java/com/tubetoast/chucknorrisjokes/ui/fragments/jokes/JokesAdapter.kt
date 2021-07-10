package com.tubetoast.chucknorrisjokes.ui.fragments.jokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.chucknorrisjokes.R
import org.jsoup.Jsoup

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        private val textView = itemView.findViewById<TextView>(R.id.joke_text)

        fun setContent(joke: String) {
            textView.text = joke
        }

    }

    internal var jokes : List<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        jokes?.let{
            holder.setContent(jokes!![position])
        }
    }

    override fun getItemCount(): Int {
        return jokes?.size ?: 0
    }


}
