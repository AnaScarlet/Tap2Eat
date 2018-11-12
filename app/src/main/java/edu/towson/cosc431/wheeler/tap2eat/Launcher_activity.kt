package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launcher_activity.*
import kotlinx.android.synthetic.main.common_action_bar.*
import android.view.Menu
import android.view.MenuItem


class Launcher_activity : AppCompatActivity(), IHasActionBar {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_activity)

        LaunchHomeBtn.setOnClickListener { launchHome() }
        LaunchLoginBtn.setOnClickListener { launchLogin() }
        signTv.setOnClickListener { launchSignup() }

        setSupportActionBar(my_toolbar)
        //supportActionBar

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
}
