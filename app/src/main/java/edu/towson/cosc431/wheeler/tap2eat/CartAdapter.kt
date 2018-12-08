package edu.towson.cosc431.wheeler.tap2eat

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.menuitem_view.view.*

class CartAdapter() : RecyclerView.Adapter<CartItemViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        Log.d("CartAdapter", "OnCreateViewHolder() called")
        // 1. Inflate a view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menuitem_view, parent, false)

        val itemViewHolder = CartItemViewHolder(view)

        // Delete from cart on click
        itemViewHolder.itemView.setOnLongClickListener {

            val builder = AlertDialog.Builder(it.context)

            builder.setMessage(R.string.delete_dialog)
                    .setPositiveButton(R.string.delete_btn,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Send the positive button event back to the host activity
                                deleteEventListener(itemViewHolder, parent)
                            })
                    .setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                // Do nothing
                            })

            val alertDialog = builder.create()
            alertDialog.show()
            
            true
        }

        return itemViewHolder
    }

    override fun getItemCount(): Int {
        return Cart.getNumberOfItems()
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val menuitem = Cart.getItem(position)
        Log.d("CartAdapter", "menuItem: " + menuitem)

        // 2. Set the view properties
        holder.itemView.menu_name.text = menuitem.name
        holder.itemView.menu_description.text = menuitem.description
        holder.itemView.menu_price.text = menuitem.price
//        holder.itemView.menu_img.se = menuitem.image
    }

    fun deleteEventListener(itemViewHolder: CartItemViewHolder, parent: ViewGroup) {
        val position = itemViewHolder.adapterPosition
        Cart.deleteFromCart(position)
        Toast.makeText(parent.context, "Item removed from cart", Toast.LENGTH_LONG).show()
        notifyItemRemoved(position)
    }

}

class CartItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)