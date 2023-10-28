package org.d3if4502.buybuddy.Ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.d3if4502.buybuddy.R
import org.d3if4502.buybuddy.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private var kategori: String = "all"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDashboardBinding.bind(view)
//        listImages = arrayListOf()
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        databaseReference = FirebaseDatabase.getInstance().getReference("resep")
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
////                    listImages.clear() // Membersihkan list sebelum menambahkan item baru
//                    for (dataSnapshot in snapshot.children) {
//                        val imageUrl    = dataSnapshot.child("url").getValue(String::class.java)
//
//                        val resepData = Resep(imageUrl)
//                        listImages.add(resepData)
//                    }
//                    recyclerView.adapter = ResepAdapter(requireContext(), listImages)
//                    recyclerView.adapter?.notifyDataSetChanged()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
//            }
//        })

        binding.profile.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
                .navigate(R.id.action_dashboardFragment_to_profileFragment)
        }

        binding.All.setOnClickListener {
            kategori = "All"
//            loadImages()
        }

        binding.food.setOnClickListener {
            kategori = "Food"
//            loadImages()
        }

        binding.Electronic.setOnClickListener {
            kategori = "Electronic"
//            loadImages()
        }

        binding.topUp.setOnClickListener {
            kategori = "Top Up"
//            loadImages()
        }

//        binding.daftarMenu.setOnClickListener {
//            val position = it.tag as Int
//            val item = listImages[position]
//            val imageUrl = item.url
//            val detailFragment = DetailFragment()
//            val bundle = Bundle()
//            bundle.putString("url", imageUrl)
//            detailFragment.arguments = bundle
//
//            // Navigasi ke DetailFragment menggunakan action yang telah ditentukan di nav_graph.xml
//            val navController = findNavController()
//            navController.navigate(R.id.action_dashboardFragment_to_detailFragment, bundle)
//        }
//        loadImages()
//
//        binding.addButton.setOnClickListener {
//            val navController = findNavController()
//            navController.navigate(R.id.action_dashboardFragment_to_tambahFragment )
//        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.person -> {
                    // Handle search icon press
                    Navigation.findNavController(requireActivity(), R.id.myNavHostFragment)
                        .navigate(R.id.action_dashboardFragment_to_profileFragment)
                    true
                }

                else -> false
            }
        }
    }

//    private fun loadImages() {
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    listImages.clear() // Membersihkan list sebelum menambahkan item baru
//                    for (dataSnapshot in snapshot.children) {
//                        val kategori = dataSnapshot.child("kategori").getValue(String::class.java)
//
//                        // Filter berdasarkan kategori yang dipilih
//                        if (kategori == kategori || kategori == "all") {
//                            val imageUrl = dataSnapshot.child("url").getValue(String::class.java)
//
//                            val resepData = Resep(imageUrl)
//                            listImages.add(resepData)
//                        }
//                    }
//                    recyclerView.adapter?.notifyDataSetChanged()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
