package edu.towson.cosc431.wheeler.tap2eat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.firebase.auth.*
import android.widget.Toast
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.content.Intent
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*


class activity_login : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        signInBtn.setOnClickListener{signIn()}

    }

    private fun signIn() {
        //get user's email & password.
        val email = nameET.getText().toString().trim()
        val pass = passET.getText().toString().trim()

        //check if the email is empty
        if(email.isEmpty()){
            Toast.makeText(this,"Please Enter Your Email",Toast.LENGTH_SHORT).show();
                return
        }
        //check if the password is empty
        if(pass.isEmpty()){

            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            return

        }
        //connecting to DB & check if the user exists
        // https://firebase.google.com/docs/auth/android/start/ ->> for more details
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                       
                        val user = mAuth.currentUser
                        finish()
                        startActivity(Intent(applicationContext, activity_home::class.java))
                    } else {
                        Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                       
                    }
                }

    }



}
