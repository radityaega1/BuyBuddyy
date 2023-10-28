package org.d3if4502.buybuddy.Ui.login

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.d3if4502.buybuddy.R
import org.d3if4502.buybuddy.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var Auth: FirebaseAuth
    private lateinit var Database: DatabaseReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var storageRef: StorageReference
    private var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
        Auth = FirebaseAuth.getInstance()
        Database = FirebaseDatabase.getInstance().reference

        binding.profile.setOnClickListener {
            uploadImage()
            val wimage = binding.profile

            var currentUser = Auth.currentUser
            if (currentUser != null) {
                var uid = currentUser.uid

                /*var user = User(wimage = )*/

                var userRef = Database.child("users").child(uid)
                /*userRef.setValue(user).addOnCompleteListener { userTask ->
                    if (userTask.isSuccessful) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_registerFragment_to_loginFragment)
                    }

                    else {
                        Toast.makeText(requireContext(), userTask.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        }

        initVars()
        registerClickEvents()

    }

    private fun registerClickEvents() {
        binding.profile.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
        binding.profile.setImageURI(it)
    }

    private fun initVars() {
        storageRef = FirebaseStorage.getInstance().reference.child("User_Profile/*")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage() {
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val map = HashMap<String, Any>()
                        map["pic"] = uri.toString()
                        firebaseFirestore.collection("image").add(map).addOnCompleteListener { firestoreTask ->
                            if (firestoreTask.isSuccessful) {
//                                Toast.makeText(requireContext(), R.string.success, Toast.LENGTH_LONG).show()
                                Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
                                    .navigate(R.id.action_profileFragment_to_dashboardFragment)
                            }

                            else {
//                                Toast.makeText(requireContext(), R.string.Failed, Toast.LENGTH_LONG).show()
                            }

//                            binding.profile.setImageResource(R.drawable.image_profile)
                        }
                    }
                }

                else {
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_LONG).show()
//                    binding.profile.setImageResource(R.drawable.image_profile)
                }
            }
        }
    }
}