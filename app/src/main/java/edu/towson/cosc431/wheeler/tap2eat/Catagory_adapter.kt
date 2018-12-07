package edu.towson.cosc431.wheeler.tap2eat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.catagory_view.view.*

class Catagory_adapter (val catagoryMenu : List<category>): RecyclerView.Adapter<CatagoryItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatagoryItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.catagory_view, parent, false)

        return CatagoryItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return catagoryMenu.size
    }

    override fun onBindViewHolder(holder: CatagoryItemViewHolder, position: Int) {
        val catagoryitem = catagoryMenu.get(position)
        holder.itemView.catagory_name.text = catagoryitem.name
        holder.itemView.catagory_image.setImageResource(catagoryitem.image)
    }
}

class CatagoryItemViewHolder(view: View?) : RecyclerView.ViewHolder(view)