package com.example.androidprotodatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.androidprotodatastore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val myViewModel : MyViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel.apply {
            info.observe(this@MainActivity){
              with(binding){
                  name.text = it.name
                  age.text = it.age.toString()
                  job.text = it.job
              }
            }
        }
        binding.save.setOnClickListener {
            myViewModel.updateContact(binding.edtName.text.toString().trim(),
            binding.edtAge.text.toString().trim().toInt(),
            binding.edtJob.text.toString().trim())
        }


    }
}