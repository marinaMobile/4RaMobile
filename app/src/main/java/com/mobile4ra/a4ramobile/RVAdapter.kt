package com.mobile4ra.a4ramobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt


class RVAdapter (private val context: Context, private val articles: List<Article>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        var image: ImageView = itemView.findViewById(R.id.imageView)
        var newTitle: TextView = itemView.findViewById(R.id.title_tv)
        var newDesc: TextView = itemView.findViewById(R.id.short_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article:Article = articles[position]
        holder.newTitle.text=article.title
        holder.newDesc.text=article.description
        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.sport)
            .error(R.drawable.sport)
            .fallback(R.drawable.sport)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}