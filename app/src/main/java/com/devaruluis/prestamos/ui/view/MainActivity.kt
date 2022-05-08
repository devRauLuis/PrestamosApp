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

        binding.saveBtn.setOnClickListener {
            personViewModel.save(
                Person(
                    names = binding.namesInput.text.toString(),
                    surnames = binding.surnamesInput.text.toString(),
                    occupation = binding.occupationInput.text.toString(),
                    income = binding.incomeInput.toFloat()
                )
            )
        }

        binding.newBtn.setOnClickListener {
            binding.idInput.setText("")
            binding.namesInput.setText("")
            binding.surnamesInput.setText("")
            binding.occupationInput.setText("")
            binding.incomeInput.setText("")
        }

        binding.deleteBtn.setOnClickListener {
            personViewModel.deleteCurrentPerson()
            binding.newBtn.performClick()
        }

        binding.searchBtn.setOnClickListener {
            val id = binding.idInput.toLong()
            if (id != 0L)
                personViewModel.find(id)
            else Snackbar.make(view, "Especifique un ID para buscar", Snackbar.LENGTH_LONG).show()
        }

        personViewModel.personLD.observe(this, Observer {
            if (it != null) {
                binding.idInput.setText(it.id.toString())
                binding.namesInput.setText(it.names)
                binding.surnamesInput.setText(it.surnames)
                binding.occupationInput.setText(it.occupation)
                binding.incomeInput.setText(it.income.toString())
            }
        })

        personViewModel.success.observe(this, Observer {
            if (it) {
                Snackbar.make(view, "Éxito", Snackbar.LENGTH_LONG).show()
            }
        })
        personViewModel.error.observe(this, Observer {
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })

    }

    fun TextInputEditText.toFloat() = text.toString().toFloatOrNull() ?: 0.0f
    fun TextInputEditText.toLong() = text.toString().toLongOrNull() ?: 0
}