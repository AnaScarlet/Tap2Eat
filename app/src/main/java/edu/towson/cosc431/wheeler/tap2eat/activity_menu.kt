package edu.towson.cosc431.wheeler.tap2eat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_menu.*

class activity_menu : AppCompatActivity() {

    var menu: MutableList<menuItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // 1. instantiate the SongAdapter
        val menuAdapter = MenuItemAdapter(menu)

        // 2. set the LayoutManager on the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. set the adapter on the recyclerview
        recyclerView.adapter = MenuItemAdapter(menu)


        menu.add(menuItem("Onion Rings", "Blah  Blah Blah"))
        menu.add(menuItem("Beef Weelington", "Blah  Blah Blah"))
        menu.add(menuItem("Spaghetti & Meatballs", "Blah  Blah Blah"))
        menu.add(menuItem("Other Random Foods", "Blah  Blah Blah"))
        menu.add(menuItem("Cheesecake", "Blah  Blah Blah"))
    }
}
