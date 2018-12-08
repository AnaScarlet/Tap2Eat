package edu.towson.cosc431.wheeler.tap2eat

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_launcher_activity.*
import kotlinx.android.synthetic.main.common_action_bar.*
import android.view.Menu
import android.view.MenuItem


class LauncherActivity : AppCompatActivity(), IHasActionBar {

    private val broadcastReceiver = FoodOrderBroadcastReceiver()

    companion object {
        val CHANNEL_ID = "CART FOOD ORDER"
        val NOTIF_ID = 333
        val MESSAGE = "Your order is ready!"
        val ACTION_ORDER = "ORDER THIS"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_activity)

        LaunchHomeBtn.setOnClickListener { launchHome() }
        LaunchLoginBtn.setOnClickListener { launchLogin() }
        signTv.setOnClickListener { launchSignup() }

        setSupportActionBar(my_toolbar)
        createNotificationChannel()

    }

    override fun onResume() {
        val filter = IntentFilter(ACTION_ORDER)
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

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  // Make sure Android version is compatible
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(IntentService.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
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
        intent.component = ComponentName(this, UserProfileActivity::class.java)
        startActivity(intent)
    }

    private fun launchSignup() {
        intent = Intent()
        intent.component = ComponentName(this, activity_signup::class.java)
        startActivity(intent)
    }

    private fun launchLogin() {
        intent = Intent()
        intent.component = ComponentName(this, activity_login::class.java)
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
