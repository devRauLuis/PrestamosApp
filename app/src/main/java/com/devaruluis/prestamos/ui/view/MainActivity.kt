package com.devaruluis.prestamos.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.devaruluis.prestamos.databinding.ActivityMainBinding
import com.devaruluis.prestamos.model.Person
import com.devaruluis.prestamos.ui.viewmodel.PersonViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val personViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addBtn.setOnClickListener {
            personViewModel.save(
                Person(
                    names = binding.namesInput.text.toString(),
                    surnames = binding.surnamesInput.text.toString(),
                    occupation = binding.occupationInput.text.toString(),
                    income = binding.incomeInput.toFloat()
                )
            )
        }

        personViewModel.success.observe(this, Observer {
            if (it) {
                Snackbar.make(view, "Guardo", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun TextInputEditText.toFloat() = text.toString().toFloatOrNull() ?: 0.0f
}