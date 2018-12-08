package edu.towson.cosc431.wheeler.tap2eat

import android.app.PendingIntent
import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.common_action_bar.*

class CartActivity : AppCompatActivity(), IHasActionBar{

    private val broadcastReceiver = FoodOrderBroadcastReceiver()

    companion object {
        //val CHANNEL_ID = "CART FOOD ORDER"
        //val NOTIF_ID = 333
        //val MESSAGE = "Your order is ready!"
        //val ACTION_ORDER = "ORDER THIS"
        val TAG = "CartActivity"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setSupportActionBar(my_toolbar)
        //createNotificationChannel()

        cart_recycler_view.layoutManager = LinearLayoutManager(this)
        val cartAdapter = CartAdapter()
        cart_recycler_view.adapter = cartAdapter

        submit_cart_button.setOnClickListener {
            Toast.makeText(this, "Your order has been placed", Toast.LENGTH_LONG).show()
            processOrder()
            for (i in 0..(Cart.getNumberOfItems()-1)) {
                Cart.deleteFromCart(0)
            }
            cartAdapter.notifyDataSetChanged()
        }
    }

    private fun processOrder() {
        object : Thread() {
            override fun run() {
                Log.d(TAG, "Processing Order")
                // Sleep for 10 seconds - pretend to process order
                Thread.sleep(10*1000)

                val broadcastReceiverIntent = Intent()
                broadcastReceiverIntent.setAction(LauncherActivity.ACTION_ORDER)

                val activityIntent = Intent(this@CartActivity, LauncherActivity::class.java)
                activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                val pendingIntent = PendingIntent.getActivity(this@CartActivity, 0, activityIntent, 0)

                val result = LocalBroadcastManager
                        .getInstance(this@CartActivity)
                        .sendBroadcast(broadcastReceiverIntent) // Returns true if someone received the broadcast

                Log.d(TAG, "result from sending broadcast = " + result.toString())
                if (!result) {
                    // Show notification
                    val notification = NotificationCompat.Builder(this@CartActivity, LauncherActivity.CHANNEL_ID) // ChannelId - notifications can be grouped into channels
                            .setContentTitle(LauncherActivity.MESSAGE)
                            .setSmallIcon(android.R.drawable.ic_input_get)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .build() // Returns the actual notification

                    NotificationManagerCompat.from(this@CartActivity).notify(LauncherActivity.NOTIF_ID, notification)
                }

            }
        }.start()
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
        // Already here
    }


}

