package com.mobile4ra.a4ramobile

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        reg_btn.setOnClickListener{
            createAcc()
        }
        login_int.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createAcc() {
        val mail = et_email.text.toString()
        val password = et_password.text.toString()
        val name = name_et.text.toString()

        when {
            TextUtils.isEmpty(mail) -> {
                et_email.error = "Email is empty. Enter your email!"
                et_email.requestFocus()
            }
            TextUtils.isEmpty(password) -> {
                et_password.error = "Password is empty. Create your password!"
                et_password.requestFocus()
            }
            TextUtils.isEmpty(name) -> {
                name_et.error = "Name is empty. Enter your name!"
                name_et.requestFocus()
            }
            else -> {
                mAuth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
}
}
