package com.devaruluis.prestamos.ui.view.registries

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonRegistryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.saveBtn?.setOnClickListener {
            personViewModel.save(
                Person(
                    id = personViewModel.personLD.value?.id ?: 0,
                    names = binding?.namesInput?.text.toString(),
                    surnames = binding?.surnamesInput?.text.toString(),
                    occupation = binding?.occupationInput?.text.toString(),
                    income = binding?.incomeInput?.toFloat()
                )
            )
        }

//        view.findViewById<AppCompatButton>(R.id.newBtn)?.setOnClickListener {
        binding?.newBtn?.setOnClickListener {
            binding?.idInput?.setText("")
            binding?.namesInput?.setText("")
            binding?.surnamesInput?.setText("")
            binding?.occupationInput?.setText("")
            binding?.incomeInput?.setText("")
            personViewModel.clear()
        }

        binding?.deleteBtn?.setOnClickListener {
            personViewModel.deleteCurrentPerson()
            binding?.newBtn?.performClick()
        }

//        view.findViewById<AppCompatImageButton>(R.id.searchBtn).setOnClickListener {
        binding?.searchBtn?.setOnClickListener {
            val id = binding?.idInput?.toLong()
            if (id != null) {
                personViewModel.find(id)
            } else Snackbar.make(
                view,
                "Especifique un ID para buscar",
                Snackbar.LENGTH_LONG
            ).show()
        }

        personViewModel.personLD.observe(viewLifecycleOwner) {
            println("Person: " + it?.names)
            if (it != null) {
                binding?.idInput?.setText(it.id.toString())
                binding?.namesInput?.setText(it.names)
                binding?.surnamesInput?.setText(it.surnames)
                binding?.occupationInput?.setText(it.occupation)
                binding?.incomeInput?.setText(it.income.toString())
            }
        }

        personViewModel.success.observe(viewLifecycleOwner) {
            if (it)
                Snackbar.make(view, "Éxito", Snackbar.LENGTH_LONG).show()

        }
        personViewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        }


    }

    //    val args: PersonRegistryFragmentArgs by navArgs()
    companion object {
        @JvmStatic
        fun newInstance() =
            OccupationRegistryFragment().apply {

            }
    }

    fun TextInputEditText.toFloat() = text.toString().toFloatOrNull() ?: 0.0f
    fun TextInputEditText.toLong() = text.toString().toLongOrNull() ?: 0

}