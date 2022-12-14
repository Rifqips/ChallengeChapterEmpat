package com.rifqipadisiliwangi.noteappnavigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContext = requireContext().applicationContext
        sharedPrefs = appContext.getSharedPreferences("USERNAME", Context.MODE_PRIVATE)


        binding.btnSignUp.setOnClickListener {
            saveData()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            Toast.makeText(context,"Berhasil Sign Up", Toast.LENGTH_LONG).show()
        }
    }

    fun saveData(){
        val user = binding.etUsername.text.toString()
        val fullName = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirm.text.toString()
        var addUser = sharedPrefs.edit()
        addUser.putString("username", user)
        addUser.putString("useremail", fullName)
        addUser.putString("password", password)
        addUser.putString("confirm", confirm)
        addUser.apply()
    }
}