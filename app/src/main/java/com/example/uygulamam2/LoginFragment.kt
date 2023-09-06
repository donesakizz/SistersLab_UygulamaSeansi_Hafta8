package com.example.uygulamam2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.uygulamam2.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener {
            val email = binding.edtLoginEmail.text.toString()
            val password = binding.edtLoginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                Toast.makeText(requireContext(), "E-posta ve şifre boş olamaz.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Giriş başarılı
                    Toast.makeText(requireContext(), "Giriş başarılı.", Toast.LENGTH_SHORT).show()
                    // Giriş başarılıysa, ana ekrana yönlendirme yapabilirsiniz
                } else {
                    // Giriş başarısız
                    Toast.makeText(requireContext(), "Giriş başarısız. Hata: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }




}