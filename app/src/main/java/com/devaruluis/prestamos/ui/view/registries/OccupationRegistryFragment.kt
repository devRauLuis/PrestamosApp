package com.devaruluis.prestamos.ui.view.registries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.devaruluis.prestamos.databinding.FragmentOccupationRegistryBinding
import com.devaruluis.prestamos.model.Occupation
import com.devaruluis.prestamos.ui.viewmodel.OccupationViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class OccupationRegistryFragment : Fragment() {
    private var _binding: FragmentOccupationRegistryBinding? = null
    private val binding get() = _binding
    private val occupationViewModel: OccupationViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_occupation_registry, container, false)
        _binding = FragmentOccupationRegistryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.saveBtn?.setOnClickListener {
            occupationViewModel.save(
                Occupation(
                    id = occupationViewModel.occupationLD.value?.id ?: 0,
                    description = binding?.occupationInput?.text.toString(),
                )
            )
            Snackbar.make(view, "Save button pressed!", Snackbar.LENGTH_LONG).show()
        }

        binding?.newBtn?.setOnClickListener {
            binding?.idInput?.setText("")
            binding?.occupationInput?.setText("")
            occupationViewModel.clear()
        }

        binding?.deleteBtn?.setOnClickListener {
            occupationViewModel.deleteCurrentOccupation()
            binding?.newBtn?.performClick()
        }

        binding?.searchBtn?.setOnClickListener {
            val id = binding?.idInput?.toLong()
            if (id != null)
                occupationViewModel.find(id)
            else
                Snackbar.make(
                    view,
                    "Especifique un ID para buscar",
                    Snackbar.LENGTH_LONG
                ).show()

        }


        occupationViewModel.occupationLD.observe(viewLifecycleOwner) {
            if (it != null) {
                binding?.idInput?.setText(it.id.toString())
                binding?.occupationInput?.setText(it.description)
            }
        }

        occupationViewModel.success.observe(viewLifecycleOwner) {
            if (it)
                Snackbar.make(view, "Éxito", Snackbar.LENGTH_LONG).show()
        }

        occupationViewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = OccupationRegistryFragment().apply {
        }
    }


    fun TextInputEditText.toFloat() = text.toString().toFloatOrNull() ?: 0.0f
    fun TextInputEditText.toLong() = text.toString().toLongOrNull() ?: 0
}