package com.example.dreamlog

import android.content.Intent
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamlog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var button_add : Button
    private lateinit var dreamsList : ArrayList<Dream>
//    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView : RecyclerView

    private val dreamViewModel : DreamViewModel by viewModels {
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        dreamsList = ArrayList<Dream>()
//        recyclerView = binding.recyclerViewDreams
//        button_add = binding.buttonAddDream

        recyclerView = findViewById(R.id.recyclerView_Dreams)
        button_add = findViewById(R.id.button_addDream)

        val adapter = DreamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

        dreamViewModel.allDreams.observe(this, Observer {
            dreams -> dreams?.let {
                adapter.submitList(it)
            }
        })

        button_add.setOnClickListener {
            launchSecondActivity()
        }
    }

    private fun launchSecondActivity() {
        val intent = Intent(this@MainActivity, SecondActivity:: class.java) // this is the standard way to refer to the class that you're linking to
        // get id from recyclerView
        intent.putExtra("id", "0")
        startActivity(intent)
    }
}