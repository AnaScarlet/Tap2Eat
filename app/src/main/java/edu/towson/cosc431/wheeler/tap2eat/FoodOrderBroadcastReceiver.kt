package edu.towson.cosc431.wheeler.tap2eat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast

class FoodOrderBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, LauncherActivity.MESSAGE, Toast.LENGTH_LONG).show()
        if (context != null) {
            NotificationManagerCompat
                    .from(context)
                    .cancel(LauncherActivity.CHANNEL_ID, LauncherActivity.NOTIF_ID)
        }
    }
}