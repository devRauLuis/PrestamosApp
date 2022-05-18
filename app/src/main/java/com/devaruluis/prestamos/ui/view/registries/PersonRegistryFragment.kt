package com.devaruluis.prestamos.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.devaruluis.prestamos.databinding.FragmentPersonRegistryBinding
import com.devaruluis.prestamos.model.Person
import com.devaruluis.prestamos.ui.viewmodel.PersonViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class PersonRegistryFragment : Fragment() {
    private var _binding: FragmentPersonRegistryBinding? = null
    private val binding get() = _binding
    private val personViewModel: PersonViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding?.root

        binding?.saveBtn?.setOnClickListener {
            personViewModel.save(
                Person(
                    names = binding?.namesInput?.text.toString(),
                    surnames = binding?.surnamesInput?.text.toString(),
                    occupation = binding?.occupationInput?.text.toString(),
                    income = binding?.incomeInput?.toFloat()
                )
            )
        }

        binding?.newBtn?.setOnClickListener {
            binding?.idInput?.setText("")
            binding?.namesInput?.setText("")
            binding?.surnamesInput?.setText("")
            binding?.occupationInput?.setText("")
            binding?.incomeInput?.setText("")
        }

        binding?.deleteBtn?.setOnClickListener {
            personViewModel.deleteCurrentPerson()
            binding?.newBtn?.performClick()
        }

        binding?.searchBtn?.setOnClickListener {
            val id = binding?.idInput?.toLong()
            if (id != 0L)
                id?.let { it1 -> personViewModel.find(it1) }
            else view?.let { it1 ->
                Snackbar.make(
                    it1,
                    "Especifique un ID para buscar",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        personViewModel.personLD.observe(this, Observer {
            if (it != null) {
                binding?.idInput?.setText(it.id.toString())
                binding?.namesInput?.setText(it.names)
                binding?.surnamesInput?.setText(it.surnames)
                binding?.occupationInput?.setText(it.occupation)
                binding?.incomeInput?.setText(it.income.toString())
            }
        })

        personViewModel.success.observe(this, Observer {
            if (it && view != null)
                Snackbar.make(view, "Éxito", Snackbar.LENGTH_LONG).show()

        })
        personViewModel.error.observe(this, Observer {
            if (view != null)
                Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_occupation_registry, container, false)
        _binding = FragmentPersonRegistryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OccupationRegistryFragment().apply {

            }
    }


//    val args: PersonRegistryFragmentArgs by navArgs()


    fun TextInputEditText.toFloat() = text.toString().toFloatOrNull() ?: 0.0f
    fun TextInputEditText.toLong() = text.toString().toLongOrNull() ?: 0
}