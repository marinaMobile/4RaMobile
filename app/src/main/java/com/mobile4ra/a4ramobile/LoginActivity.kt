package com.mobile4ra.a4ramobile

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login_btn.setOnClickListener{
            loginAcc()
        }
        register_int.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
    private fun loginAcc() {
        val mail = et_login.text.toString()
        val password = et_password.text.toString()

        when {
            TextUtils.isEmpty(mail) -> {
                et_login.error = "Email is empty. Enter your email!"
                et_login.requestFocus()
            }
            TextUtils.isEmpty(password) -> {
                et_password.error = "Password is empty. Create your password!"
                et_password.requestFocus()
            }
            else -> {
                mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

            }            }

    }
}