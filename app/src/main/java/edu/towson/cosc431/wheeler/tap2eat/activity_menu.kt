package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import edu.towson.cosc431.wheeler.tap2eat.R.id.recyclerView
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.common_action_bar.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.FirebaseError
import com.google.firebase.database.*


class activity_menu : AppCompatActivity(), IHasActionBar {

    var menu: MutableList<menuItem> = mutableListOf()

    //initialize firebase
    var db = FirebaseDatabase.getInstance()
    var itemList = db.getReference("menuItem")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(my_toolbar)

        // 1. instantiate the MenuAdapter
        val menuAdapter = MenuItemAdapter(menu)

        // 2. set the LayoutManager on the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. set the adapter on the recyclerview
        recyclerView.adapter = MenuItemAdapter(menu)


        menu.add(menuItem("Cheese Steak", "Steak, lettuce,\n" +
                "tomatoes, mayo, mushrooms, green peppers,\n" +
                "onions, hot peppers & provolone", "7.00"))
        menu.add(menuItem("Beef Weelington", "Blah  Blah Blah","5.00" ))
        menu.add(menuItem("Spaghetti & Meatballs", "Blah  Blah Blah","6.00"))
        menu.add(menuItem("Other Random Foods", "Blah  Blah Blah","7.00"))
        menu.add(menuItem("Cheesecake", "Blah  Blah Blah","5.00"))

//        getmenu()
    }

//    private fun getmenu() {
//
//
//
//        val messageListener = object : ValueEventListener {
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val message = dataSnapshot.getValue(activity_menu::class.java)
//                    // ...
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Failed to read value
//            }
//        }
//
//        itemList!!.addValueEventListener(messageListener)
//
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                launchProfile()
            }
            R.id.home -> {
                launchHome()
            }
        }
        return true
    }

    override fun launchProfile() {
        intent = Intent()
        intent.component = ComponentName(this, UserProfile::class.java)
        startActivity(intent)
    }

    override fun launchHome() {
        intent = Intent()
        intent.component = ComponentName(this, activity_home::class.java)
        startActivity(intent)
    }
}
