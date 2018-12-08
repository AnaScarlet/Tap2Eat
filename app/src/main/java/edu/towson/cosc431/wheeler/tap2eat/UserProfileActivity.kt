package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.common_action_bar.*

class UserProfileActivity : AppCompatActivity(), IHasActionBar {

    val user = FirebaseAuth.getInstance().currentUser
    private val broadcastReceiver = FoodOrderBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        setSupportActionBar(my_toolbar)

        logoutBtn.setOnClickListener{logout()}

        welcome_message.setText("Hi,"+ user?.getEmail())

            f_name_input.setText(user?.displayName)
//            password_input.setText(user?.)
                phone_input.setText(user?.phoneNumber)
        val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(" Usertest")
                .build()


        user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Tag", "User profile updated.")
                    }
                }
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

    private fun logout() {
        if(user != null){

            FirebaseAuth.getInstance().signOut();

            intent = Intent()
            intent.component = ComponentName(this,activity_login::class.java)
            startActivity(intent)

        }else {

            intent.component = ComponentName(this,activity_home::class.java)
            startActivity(intent)
        }
    }

    override fun launchProfile() {
        // Already here
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
