package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.common_action_bar.*

class activity_home : AppCompatActivity(), IHasActionBar {


    var catagoryList: MutableList<category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        order_button.setOnClickListener{ launchMenu() }


        setSupportActionBar(my_toolbar)

        val adapter = Catagory_adapter(catagoryList)


        catagoryRV.layoutManager= LinearLayoutManager(this)

        catagoryRV.adapter = adapter

                populateCatagory()
    }

    private fun populateCatagory() {



        if(catagoryList.isEmpty()){



            catagoryList.add(category(3,"Pizza",R.drawable.pizza_cat))
            catagoryList.add(category(4,"Sandwitch",R.drawable.subs_cat))
            catagoryList.add(category(2,"Pasta",R.drawable.pasta_cat))
            catagoryList.add(category(1,"Soda",R.drawable.soda_cat))

        }else{
            (1..catagoryList.size).forEach {

                catagoryList.get(5)
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



    private fun launchMenu() {

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            intent = Intent()
            intent.component = ComponentName(this, activity_menu::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this,"Log in to make an order",Toast.LENGTH_SHORT).show();
            intent.component = ComponentName(this,activity_login::class.java)
            startActivity(intent)
        }

    }

}
