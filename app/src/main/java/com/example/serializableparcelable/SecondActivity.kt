package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.serializableparcelable.databinding.ActivityMainBinding
import com.example.serializableparcelable.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private var person: Person? = null
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbarSecond.title = "Полное описание пользователя"
        setSupportActionBar(binding.toolbarSecond)

        person = intent.extras?.getParcelable(Person::class.java.simpleName) as Person?

        val name: String = person?.name.toString()
        val lastName = person?.lastName.toString()
        val address = person?.address.toString()
        val tel = person?.tel.toString()

        binding.nameTV.text = name
        binding.lastNameTV.text = lastName
        binding.addressTV.text = address
        binding.telTV.text = tel

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}