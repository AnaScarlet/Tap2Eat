package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.catagory_view.view.*

class Catagory_adapter (val catagoryMenu : List<category>): RecyclerView.Adapter<CategoryItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.catagory_view, parent, false)

        val viewHolder = CategoryItemViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            launchMenu(parent)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {

        return catagoryMenu.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val catagoryitem = catagoryMenu.get(position)
        holder.itemView.catagory_name.text = catagoryitem.name
        holder.itemView.catagory_image.setImageResource(catagoryitem.image)
    }

    private fun launchMenu(parent: ViewGroup) {
        val user = FirebaseAuth.getInstance().currentUser
        val intent = Intent()
        if (user != null) {
            intent.component = ComponentName(parent.context, activity_menu::class.java)
            parent.context.startActivity(intent)

        } else {
            Toast.makeText(parent.context, "Log in to make an order", Toast.LENGTH_SHORT).show();
            intent.component = ComponentName(parent.context, activity_login::class.java)
            parent.context.startActivity(intent)
        }
    }

}

class CategoryItemViewHolder(view: View?) : RecyclerView.ViewHolder(view)