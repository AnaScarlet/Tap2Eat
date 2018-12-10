package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.common_action_bar.*
import com.google.firebase.database.*


class activity_menu : AppCompatActivity(), IHasActionBar {

    var menu: MutableList<menuItem> = mutableListOf()
    private val broadcastReceiver = FoodOrderBroadcastReceiver()

    //initialize firebase
    var db = FirebaseDatabase.getInstance()
    var itemList = db.getReference("menuItem")
    lateinit var ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(my_toolbar)

//        ref = FirebaseDatabase.getInstance().getReference("menuItem")
//
//        // 2. set the LayoutManager on the recyclerview
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        ref.addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if(p0!!.exists()){
//
//                    for(h in p0.children){
//                        val recipe = h.getValue(menuItem::class.java)
//                        menu.add(recipe!!)
//                        Log.d("String",recipe.toString())
//                    }
//
//                    // 1. instantiate the MenuAdapter
//                    val menuAdapter = MenuItemAdapter(menu)
//
//
//
//                    // 3. set the adapter on the recyclerview
//                    recyclerView.adapter = menuAdapter
//
//                }
//            }
//
//        })



//        menu.add(menuItem("Cheese Steak", "Steak, lettuce,\n" +
//                "tomatoes, mayo, mushrooms, green peppers,\n" +
//                "onions, hot peppers & provolone", "7.00"))
//        menu.add(menuItem("Beef Weelington", "Blah  Blah Blah","5.00" ))
//        menu.add(menuItem("Spaghetti & Meatballs", "Blah  Blah Blah","6.00"))
//        menu.add(menuItem("Other Random Foods", "Blah  Blah Blah","7.00"))
//        menu.add(menuItem("Cheesecake", "Blah  Blah Blah","5.00"))

//        val string = getMenu()

//        menu.add(menuItem("Beef Weelington", string,"5.00" )
    }

    override fun onResume() {
        val filter = IntentFilter(LauncherActivity.ACTION_ORDER)
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(broadcastReceiver, filter)

        super.onResume()
    }

    override fun onPause() {
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(broadcastReceiver)

        super.onPause()
    }

//    private fun getMenu() : String {
//        var post = ""
//        val menuListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                post = dataSnapshot.child("menuItem").value.toString()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                println("loadPost:onCancelled ${databaseError.toException()}")
//            }
//        }
//        val item = itemList.child("menuItem").addListenerForSingleValueEvent(menuListener)
//        return post
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
            R.id.cart -> {
                launchCart()
            }
            else -> {
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun launchProfile() {
        intent = Intent()
        intent.component = ComponentName(this, UserProfileActivity::class.java)
        startActivity(intent)
    }

    override fun launchHome() {
        intent = Intent()
        intent.component = ComponentName(this, activity_home::class.java)
        startActivity(intent)
    }

    override fun launchCart() {
        intent = Intent()
        intent.component = ComponentName(this, CartActivity::class.java)
        startActivity(intent)
    }
}
