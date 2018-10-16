package edu.towson.cosc431.wheeler.tap2eat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.menuitem_view.view.*

class MenuItemAdapter(val menu: List<menuItem>) : RecyclerView.Adapter<MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        // 1. Inflate a view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menuitem_view, parent, false)

        // 2. Create and return the ViewHolder
        return MenuItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        // 1. get the Song at the position
        val menuitem = menu.get(position)

        // 2. Set the view properties
        holder.itemView.itemName.text = menuitem.name
        holder.itemView.itemDescrip.text = menuitem.description
    }
}

class MenuItemViewHolder(view: View?) : RecyclerView.ViewHolder(view)