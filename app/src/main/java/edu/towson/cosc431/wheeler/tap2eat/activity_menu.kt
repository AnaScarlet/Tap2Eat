package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(my_toolbar)

        // 1. instantiate the MenuAdapter
        val menuAdapter = MenuItemAdapter(menu)

        // 2. set the LayoutManager on the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. set the adapter on the recyclerview
        recyclerView.adapter = menuAdapter


        menu.add(menuItem("Build Your Own Pizza","Your choice of any 5 toppings.","$13.99"))
        menu.add(menuItem("Chicken Tikka Pizza","Fresh tandoori chicken with green pepper, red onion and special mint sauce.","$14.99"))
        menu.add(menuItem("Veggie Delight Pizza","Mushrooms, green peppers, black olives, fresh sliced tomatoes, onions, jalapeno hot peppers, and double cheese.","$12.99"))
        menu.add(menuItem("Steak on Pizza","We blend mozzarella cheese with Philly beef steak, onions, green peppers, mushrooms, and double cheese.","$12.99"))
        menu.add(menuItem("Buffalo Chicken Cheese Steak","Meat: 8'' sub 8 oz. , 12'' Sub 12 oz.","$6.99"))
        menu.add(menuItem("SPAGHETTI WITH TOMATO SAUCE","Add meatballs, mushrooms, chicken parmesan, sausage parmesan, meat sauce, or any other fresh pizza toppings for only $1.25.","$7.99"))
        menu.add(menuItem("BAKED ZITI","BAKED ZITI pasta","$7.99"))
        menu.add(menuItem("Coke","BEVERAGES","$0.99"))
        menu.add(menuItem("Sprite","BEVERAGES","$0.99"))


//        getmenu()
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
