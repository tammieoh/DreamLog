package com.example.dreamlog

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import java.text.SimpleDateFormat
import java.util.*

class SecondActivity : AppCompatActivity() {
    private lateinit var time : Date
    private lateinit var title : EditText
    private lateinit var desc : EditText
    private lateinit var interpret : EditText
    private lateinit var spinner : Spinner
    private lateinit var button_save : Button
    private var selected_emotion = ""
    private var id: String? = null
    private val c : Calendar = Calendar.getInstance()
    private val df : SimpleDateFormat = SimpleDateFormat("dd-MMM-yyyy")
//    private var title_fill = false
//    private var desc_fill = false
//    private var interpret_fill = false


    private val dreamViewModel: DreamViewModel by viewModels {
        DreamViewModelFactory((application as DreamApplication).repository)
        // call the one and only repository we created in the Task Application class
        // so that we're not creating multiple instances of the repository in our app
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = findViewById(R.id.editText_dreamTitle)
        desc = findViewById(R.id.editText_description)
        interpret = findViewById(R.id.editText_interpret)
        button_save = findViewById(R.id.button_save)

        spinner = findViewById(R.id.spinner_emotions)

        id = intent.getStringExtra("id")

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.emotions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, pos: Int, id: Long
            ) {
                selected_emotion = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                selected_emotion = ""
            }
        }

        button_save.setOnClickListener{
            if(TextUtils.isEmpty(title.text) || TextUtils.isEmpty(desc.text)
                || TextUtils.isEmpty(interpret.text.toString()) || selected_emotion == "") {
                toastError("Missing Fields")
            }
            else {
                // think about the ID
                // if getDream doesn't return us anything

                if(id != "0") {
                    dreamViewModel.getDreamByID(Integer.parseInt(id)).observe(this, Observer {
                        dream -> dream?.let{
                            dreamViewModel.updateDream(title.text.toString(), desc.text.toString(),
                            interpret.text.toString(), selected_emotion, Integer.parseInt(id))
                            finish()
                        }
                    })
                }
                else {
                    val dream = Dream(
                        0,
                        title.text.toString(),
                        desc.text.toString(),
                        interpret.text.toString(),
                        selected_emotion
                    )
                    dreamViewModel.insert(dream)
                    finish()
                }
            }
        }
    }
    private fun toastError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}