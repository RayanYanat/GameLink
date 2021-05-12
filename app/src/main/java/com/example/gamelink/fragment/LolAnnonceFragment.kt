package com.example.gamelink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelink.R
import com.example.gamelink.adapter.AnnonceAdapter
import com.example.gamelink.model.Annonce
import com.example.gamelink.model.User
import com.example.gamelink.viewModel.FirebaseViewModel

class LolAnnonceFragment : Fragment(),AnnonceAdapter.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAnnonce: ArrayList<Annonce>
    private lateinit var adapter: AnnonceAdapter
    private lateinit var mViewModel: FirebaseViewModel
    private val ANNONCE_ID = "ANNONCE_ID"
    private val USER_ANNONCE_ID = "USER_ANNONCE_ID"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_lol_annonce, container, false)
        mViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        recyclerView = view.findViewById(R.id.fragment_annonces_list_recyclerview)
        configureRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getSavedUsers().observe(viewLifecycleOwner, Observer<List<User>> {
            this.listAnnonce.clear()
            it.forEach { user ->
                if (user.listAnnonce != null) {
                    user.listAnnonce.forEach {
                        if (it.gameAnnonce == "lol") {
                            this.listAnnonce.add(it)
                        }
                    }
                    Log.d("CreateAnnonceFrag", "CreateAnnonceFrag:" + listAnnonce)
                    updateUI(listAnnonce)
                }
            }
        })

        Log.d("CreateAnnonceFrag", "CreateAnnonceFrag2:" + listAnnonce)
    }

    private fun configureRecyclerView() {
        this.listAnnonce = ArrayList()
        adapter = AnnonceAdapter(listAnnonce, this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun updateUI(results: List<Annonce>) {

        adapter.setResults(results)
        Log.d("CreateAnnonceFrag", "CreateAnnonceFrag3:" + results)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClickListener(annonce: Annonce) {

        val bundle = Bundle()
        val fragmentDetailAnnonce = DetailAnnonceFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        bundle.putString(ANNONCE_ID, annonce.annonceText)
        bundle.putString(USER_ANNONCE_ID, annonce.uid)
        Log.d("CreateAnnonceFraguid", "CreateAnnonceBundle:" + annonce.uid)
        fragmentDetailAnnonce.arguments = bundle
        transaction.replace(R.id.main_fragment, fragmentDetailAnnonce).commit()

    }
}