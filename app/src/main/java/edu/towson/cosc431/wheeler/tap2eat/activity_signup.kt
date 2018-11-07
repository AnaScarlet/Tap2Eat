package edu.towson.cosc431.wheeler.tap2eat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class activity_signup : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()


        signup.setOnClickListener { signUp() }

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
