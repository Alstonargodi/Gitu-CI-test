package com.example.githubuser.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.githubuser.MainActivity
import com.example.githubuser.model.xmlresponse.User
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityDetailBinding

class Detailactivity : AppCompatActivity() {
    private lateinit var bind : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val dataUser = intent.getParcelableArrayListExtra<User>("data")


        window.statusBarColor = ContextCompat.getColor(this, R.color.maincolor)
        val name = dataUser?.get(0)?.user.toString()
        bind.NameTvdetail.text = name

        bind.apply {
            FollowersTvdetail.setText(dataUser?.get(0)?.follower.toString())
            FollowingTvdetail.setText(dataUser?.get(0)?.following.toString())
            UsernameTvdetail.setText(dataUser?.get(0)?.username.toString())
            RepositoryTvdetail.setText(dataUser?.get(0)?.repo.toString())
            LocationTvdetail.setText(dataUser?.get(0)?.location.toString())
            CompanyTvdetail.setText(dataUser?.get(0)?.company.toString())
        }

        Glide.with(this)
            .load(dataUser?.get(0)?.avatar)
            .circleCrop()
            .into(bind.ImgTvdetail)

        bind.btnShare.setOnClickListener {
            val share= Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT,"Visit $name on github")

            startActivity(Intent.createChooser(share,"bagikan ke"))
        }

        bind.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}