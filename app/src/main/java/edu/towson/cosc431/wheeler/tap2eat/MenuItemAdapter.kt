package edu.towson.cosc431.wheeler.tap2eat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.menuitem_view.view.*

class MenuItemAdapter(val menu: List<menuItem>) : RecyclerView.Adapter<MenuItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        // 1. Inflate a view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menuitem_view, parent, false)

        val itemViewHolder = MenuItemViewHolder(view)

        itemViewHolder.itemView.setOnClickListener {
            val menuItem = menuItem(it.menu_name.text.toString(),
                    it.menu_description.text.toString(),
                    it.menu_price.text.toString()
            )
            Cart.putInCart(menuItem)
            Toast.makeText(parent.context, "Item added to cart", Toast.LENGTH_SHORT).show()
            Log.d("MenuItemAdapter", "Item added to cart: " + menuItem)
        }

        return itemViewHolder
    }

    override fun getItemCount(): Int {
        return menu.size
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        // 1. get the Song at the position
        val menuitem = menu.get(position)

        // 2. Set the view properties
        holder.itemView.menu_name.text = menuitem.name
        holder.itemView.menu_description.text = menuitem.description
        holder.itemView.menu_price.text = menuitem.price
//        holder.itemView.menu_img.se = menuitem.image
    }

}

class MenuItemViewHolder(view: View?) : RecyclerView.ViewHolder(view)