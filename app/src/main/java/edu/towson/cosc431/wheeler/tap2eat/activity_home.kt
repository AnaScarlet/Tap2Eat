package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.common_action_bar.*

class activity_home : AppCompatActivity(), IHasActionBar {

    var categoryList: MutableList<category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        //order_button.setOnClickListener{ launchMenu() }


        setSupportActionBar(my_toolbar)

        val adapter = Catagory_adapter(categoryList)


        catagoryRV.layoutManager= LinearLayoutManager(this)

        catagoryRV.adapter = adapter

                populateCatagory()
    }

    private fun populateCatagory() {

        if(categoryList.isEmpty()){

            categoryList.add(category(3,"Pizza",R.drawable.pizza_cat))
            categoryList.add(category(4,"Sandwitch",R.drawable.subs_cat))
            categoryList.add(category(2,"Pasta",R.drawable.pasta_cat))
            categoryList.add(category(1,"Soda",R.drawable.soda_cat))

        }else{
            (1..categoryList.size).forEach {

                categoryList.get(5)
            }
        }
    }

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
        intent.component = ComponentName(this, UserProfile::class.java)
        startActivity(intent)
    }

    override fun launchHome() {
        // Already here
    }

    override fun launchCart() {
        intent = Intent()
        intent.component = ComponentName(this, CartActivity::class.java)
        startActivity(intent)
    }



}
