package com.example.uygulamam2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.uygulamam2.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signUp(email, password)
            } else {
                Toast.makeText(requireContext(), "E-posta ve şifre boş olamaz.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı
                    Toast.makeText(requireContext(), "Kayıt başarılı.", Toast.LENGTH_SHORT).show()
                    // Kayıt başarılıysa, giriş ekranına yönlendirme yapabilirsiniz
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    // Kayıt başarısız
                    Toast.makeText(requireContext(), "Kayıt başarısız. Hata: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }



}