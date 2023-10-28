package org.d3if4502.buybuddy.Ui.login.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.d3if4502.buybuddy.R
import org.d3if4502.buybuddy.liveData.User
import org.d3if4502.buybuddy.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var Auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        Auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        binding.btn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val fullname = binding.fullname.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmpassET.text.toString()

            if (email.isNotEmpty() && fullname.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    Auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            var currentUser = Auth.currentUser
                            if (currentUser != null) {
                                var uid = currentUser.uid

                                var user = User(email, fullname, pass)

                                var userRef = database.child("users").child(uid)
                                userRef.setValue(user).addOnCompleteListener { userTask ->
                                    if (userTask.isSuccessful) {
                                        Navigation.findNavController(view)
                                            .navigate(R.id.action_registerFragment_to_loginFragment)
                                    }

                                    else {
                                        Toast.makeText(requireContext(), userTask.exception.toString(), Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                        else {
                            Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                else {
                    Toast.makeText(requireContext(), "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }

            else {
                Toast.makeText(requireContext(), "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}