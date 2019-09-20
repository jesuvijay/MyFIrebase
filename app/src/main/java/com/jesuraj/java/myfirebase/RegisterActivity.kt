package com.jesuraj.java.myfirebase


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        btn_loginhere.setOnClickListener {


            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        btn_register.setOnClickListener {
            val email = editText3.text.toString().trim()
            val passwd = editText4.text.toString().trim()
            if (email.isEmpty()) {
                editText3.error = "Email Required"
                editText3.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editText3.error = "valid email required"
                editText3.requestFocus()
                return@setOnClickListener
            }
            if (passwd.isEmpty() || passwd.length < 6) {
                editText4.error = "6 char required"
                editText4.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, passwd)


        }
    }

    private fun registerUser(email: String, passwd: String) {

        mAuth.createUserWithEmailAndPassword(email, passwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
                // register success
                else {
                    task.exception?.message?.let { toast(it) }

                }

            }
    }

}
