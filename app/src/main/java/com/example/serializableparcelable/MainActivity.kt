package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.serializableparcelable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val book: MutableList<Person> = mutableListOf()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbarMain.title = "Книга адресов за кадром"
        binding.toolbarMain.subtitle = "версия 1.0"
        setSupportActionBar(binding.toolbarMain)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, book)
        binding.mainLV.adapter = adapter

        binding.saveBTN.setOnClickListener {
            if (binding.nameET.text.isEmpty() || binding.lastNameET.text.isEmpty() || binding.addressET.text.isEmpty() || binding.telET.text.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            book.add(
                Person(
                    binding.nameET.text.toString(),
                    binding.lastNameET.text.toString(),
                    binding.addressET.text.toString(),
                    binding.telET.text.toString().toInt()
                )
            )
            Toast.makeText(this, "Добавлен пользователь ${binding.nameET.text}", Toast.LENGTH_LONG).show()
            adapter.notifyDataSetChanged()
            binding.nameET.text.clear()
            binding.lastNameET.text.clear()
            binding.addressET.text.clear()
            binding.telET.text.clear()
        }
        binding.mainLV.onItemClickListener=
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val person = adapter.getItem(position)
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(Person::class.java.simpleName, person)
                startActivity(intent)
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
