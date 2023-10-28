package org.d3if4502.buybuddy.Ui.Login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.d3if4502.buybuddy.R
import org.d3if4502.buybuddy.Ui.login.LoginViewModel
import org.d3if4502.buybuddy.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var Auth: FirebaseAuth
    private val contract = FirebaseAuthUIActivityResultContract()
    private val signInLauncher = registerForActivityResult(contract) { }
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.google.setOnClickListener { googleLogin() }

        binding = FragmentLoginBinding.bind(view)
        Auth = Firebase.auth
        Auth = FirebaseAuth.getInstance()
        Firebase.auth.signOut()
    }

        fun googleLogin() {
            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
            val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            signInLauncher.launch(intent)
            viewModel.authState.observe(viewLifecycleOwner, { user ->
                if (user == null) {
                    binding.root.visibility = View.VISIBLE
                } else {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                    )
                }
            })

            if (Auth.currentUser != null) {
                Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
                    .navigate(R.id.action_loginFragment_to_dashboardFragment)
            }

            binding.btn.setOnClickListener {
                val email = binding.emailEt.text.toString()
                val pass = binding.passET.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty()) {
                    Auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(ContentValues.TAG, "LoginFragment Success")
                            Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
                                .navigate(R.id.action_loginFragment_to_dashboardFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Email dan Password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email dan Password tidak boleh kosong !!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }