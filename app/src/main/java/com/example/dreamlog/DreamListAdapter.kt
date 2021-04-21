package com.example.dreamlog

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter : ListAdapter<Dream, DreamListAdapter.DreamViewHolder>(DreamComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DreamListAdapter.DreamViewHolder, position: Int) {
        val currentDream = getItem(position)
        holder.bindText(currentDream.id.toString(), holder.dreamId)
//        holder.bindText(currentDream.dateTime.toString(), holder.dreamId)
        holder.bindText(currentDream.title, holder.dreamTextView)

    }


    class DreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val dreamId : TextView = itemView.findViewById(R.id.textView_id)
        val dreamTextView : TextView = itemView.findViewById(R.id.textView_dreamTitle)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindText(text: String?, textView: TextView) {
            textView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): DreamViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view)
            }
        }
        override fun onClick(v: View?) {
            val intent: Intent = Intent(v?.context, ThirdActivity:: class.java)
            intent.putExtra("clicked_id", dreamId.text.toString())
            v?.context?.startActivity(intent)
        }
    }
    class DreamComparator : DiffUtil.ItemCallback<Dream>() {
        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {
           return oldItem.title == newItem.title
            // possibly change to ID later
        }
        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem === newItem
        }
    }


}

