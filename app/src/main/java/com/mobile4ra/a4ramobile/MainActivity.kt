package com.mobile4ra.a4ramobile


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getNews()

            val popupMenu = androidx.appcompat.widget.PopupMenu(this, account_iv)
            popupMenu.inflate(R.menu.popupmenu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {

                    R.id.menu2 -> {
                        mAuth.signOut()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

        account_iv.setOnClickListener{
            popupMenu.show()
        }
        post_iv.setOnClickListener{

            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = mAuth.currentUser

        if (user==null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getNews() {
        val news: Call<News> = ServNews.newsInst.getArticle("in",  "sports")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    newsRv.adapter = RVAdapter(this@MainActivity, news.articles)
                    newsRv.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }
}

