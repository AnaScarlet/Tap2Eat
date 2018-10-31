package edu.towson.cosc431.wheeler.tap2eat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import edu.towson.cosc431.wheeler.tap2eat.R.id.recyclerView
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


        menu.add(menuItem(" Cheese Steak", "Steak, lettuce,\n" +
                "tomatoes, mayo, mushrooms, green peppers,\n" +
                "onions, hot peppers & provolone", "7.00", R.drawable.ic_launcher_background))
        menu.add(menuItem("Beef Weelington", "Blah  Blah Blah","5.00",R.drawable.ic_launcher_background ))
        menu.add(menuItem("Spaghetti & Meatballs", "Blah  Blah Blah","6.00",R.drawable.ic_launcher_background))
        menu.add(menuItem("Other Random Foods", "Blah  Blah Blah","7.00",R.drawable.ic_launcher_background))
        menu.add(menuItem("Cheesecake", "Blah  Blah Blah","5.00",R.drawable.ic_launcher_background))
    }
}
