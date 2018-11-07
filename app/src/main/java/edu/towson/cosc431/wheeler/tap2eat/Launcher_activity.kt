package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launcher_activity.*

class Launcher_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_activity)

        LaunchHomeBtn.setOnClickListener { launchHome() }
        LaunchLoginBtn.setOnClickListener { launchLogin() }
        signUpTv.setOnClickListener { launchSignup() }


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

    private fun launchHome() {
        intent = Intent()
        intent.component = ComponentName(this, activity_home::class.java)
        startActivity(intent)
    }
}
