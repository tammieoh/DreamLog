package com.example.dreamlog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.coroutines.Job

class ThirdActivity : AppCompatActivity() {

    public lateinit var dream : Dream
    private lateinit var dreamTitle: TextView
    private lateinit var dreamFeel: TextView
    private lateinit var dreamDesc: TextView
    private lateinit var dreamInterpret:  TextView
    private lateinit var button_delete : Button
    private lateinit var button_update : Button
//    private val id = 0;
    private var id: String? = null

    // pass the id as an intent and use that to delete the item from the recyclerview

    private val dreamViewModel: DreamViewModel by viewModels {
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        id = intent.getStringExtra("clicked_id")

        button_delete = findViewById(R.id.button_delete)
        button_update = findViewById(R.id.button_update)

        dreamTitle = findViewById(R.id.textView_dreamTitleResult)
        dreamFeel = findViewById(R.id.textView_dreamFeelResult)
        dreamDesc = findViewById(R.id.textView_dreamDescResult)
        dreamInterpret = findViewById(R.id.textView_dreamInterpretResult)

        dreamViewModel.getDreamByID(Integer.parseInt(id)).observe(this, Observer {
            dream -> dream?.let{
                dreamTitle.setText(dream.title).toString()
                dreamFeel.setText("How Do You Feel?\n" + dream.emotion).toString()
                dreamDesc.setText("What Happened?\n" + dream.content).toString()
                dreamInterpret.setText("What's Your Interpretation?\n" + dream.reflection).toString()
            }
        })
//        dreamTitle.setText(dreamViewModel.getDreambyID(Integer.parseInt(id)))
//        dream = dreamViewModel.getDreambyID(Integer.parseInt(id))

        button_delete.setOnClickListener {
            dreamViewModel.deleteByTask(Integer.parseInt(id))
            finish()
        }

        button_update.setOnClickListener {
            launchSecondActivity()
        }
    }
    private fun launchSecondActivity() {
        val intent = Intent(this@ThirdActivity, SecondActivity:: class.java) // this is the standard way to refer to the class that you're linking to
        intent.putExtra("id", id)
        startActivity(intent)
    }
}

