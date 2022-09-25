package com.rifqipadisiliwangi.noteappnavigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContext = requireContext().applicationContext
        sharedPrefs = appContext.getSharedPreferences("USERNAME", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            dataUser(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun dataUser(email : String, password : String ){

        val navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()

        if (binding.etEmail.text?.length == 0 && binding.etPassword.text?.length == 0){
            Toast.makeText(context,"Masukkan User Account", Toast.LENGTH_LONG).show()
        }else {
            if (sharedPrefs.getString("useremail","")== email && sharedPrefs.getString("password","") == password){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment,null,navOptions)
                Toast.makeText(context,"Berhasil Login", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Data anda salah", Toast.LENGTH_LONG).show()
            }
        }
    }
}