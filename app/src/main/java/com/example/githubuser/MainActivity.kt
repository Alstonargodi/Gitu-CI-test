package com.example.githubuser

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.model.User
import com.example.githubuser.view.home.Userhomerecadapter
import com.example.githubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var adapter : Userhomerecadapter
    private lateinit var bind : ActivityMainBinding


    private var data = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        adapter = Userhomerecadapter()
        val recView = bind.recviewUser
        recView.adapter = adapter


        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recView.layoutManager = GridLayoutManager(this,2)
        }else{
            recView.layoutManager = LinearLayoutManager(this)
        }

        Inituser()

        window.statusBarColor = ContextCompat.getColor(this,R.color.maincolor)
        window.navigationBarColor = ContextCompat.getColor(this,R.color.subcolor)


    }

    fun Inituser(){
        val Name = resources.getStringArray(R.array.name)
        val Photo = resources.obtainTypedArray(R.array.avatar)
        for (i in Name.indices){
            val dataUser = User(
                resources.getStringArray(R.array.name)[i],
                resources.getStringArray(R.array.username)[i],
                resources.getStringArray(R.array.location)[i],
                resources.getStringArray(R.array.repository)[i],
                resources.getStringArray(R.array.company)[i],
                resources.getStringArray(R.array.followers)[i],
                resources.getStringArray(R.array.following)[i],
                Photo.getResourceId(i,-1)
            )

            data.add(dataUser)
            adapter.setdata(data)
        }
    }




}

