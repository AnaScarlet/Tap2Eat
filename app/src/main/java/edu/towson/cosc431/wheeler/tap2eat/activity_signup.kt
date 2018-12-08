package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.common_action_bar.*


class activity_signup : AppCompatActivity(), IHasActionBar {

    private lateinit var mAuth: FirebaseAuth
    private val broadcastReceiver = FoodOrderBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        signup.setOnClickListener { signUp() }

        setSupportActionBar(my_toolbar)
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

    private fun signUp() {

        //get user's email & password.

        val email = emailET.getText().toString().trim()
        val password = passwordET.getText().toString().trim()


        //check if the email is empty
        if(email.isEmpty()){
            Toast.makeText(this,"Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return
        }
        //check if the password is empty
        if(password.isEmpty()){

            Toast.makeText(this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return

        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val user = mAuth.currentUser
                        finish()
                        startActivity(Intent(applicationContext, activity_home::class.java))
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }


                }
    }

}
