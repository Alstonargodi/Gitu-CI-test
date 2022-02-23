package com.example.githubuser

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.Model.User
import com.example.githubuser.View.Home.Userhomerecadapter
import com.example.githubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var adapter : Userhomerecadapter
    lateinit var bind : ActivityMainBinding


    private var data = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        adapter = Userhomerecadapter()
        val recview = bind.recviewUser
        recview.adapter = adapter


        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recview.layoutManager = GridLayoutManager(this,2)
        }else{
            recview.layoutManager = LinearLayoutManager(this)
        }

        Inituser()

        window.statusBarColor = ContextCompat.getColor(this,R.color.maincolor)
        window.navigationBarColor = ContextCompat.getColor(this,R.color.subcolor)
    }

    fun Inituser(){
        val nama = resources.getStringArray(R.array.name)
        val image = resources.obtainTypedArray(R.array.avatar)
        for (i in nama.indices){
            val datauser = User(
                resources.getStringArray(R.array.name)[i],
                resources.getStringArray(R.array.username)[i],
                resources.getStringArray(R.array.location)[i],
                resources.getStringArray(R.array.repository)[i],
                resources.getStringArray(R.array.company)[i],
                resources.getStringArray(R.array.followers)[i],
                resources.getStringArray(R.array.following)[i],
                image.getResourceId(i,-1)
            )

            data.add(datauser)
            adapter.setdata(data)
        }
    }
}