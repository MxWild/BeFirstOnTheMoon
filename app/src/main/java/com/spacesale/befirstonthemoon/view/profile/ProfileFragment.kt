package com.spacesale.befirstonthemoon.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Purchase

class ProfileFragment : Fragment() {

    private var recycler: RecyclerView? = null

    private var buttonBack: TextView? = null

    private var purchases: List<Purchase>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        purchases = arguments?.getParcelableArrayList(SECTORS)
        purchases?.let {
            bindViews(it)
        }
    }

    override fun onDestroyView() {
        buttonBack = null
        recycler?.adapter = null
        recycler = null
        super.onDestroyView()
    }

    private fun initViews(view: View) {
        buttonBack = view.findViewById(R.id.back)

        recycler = view.findViewById<RecyclerView>(R.id.sectors_list).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ProfileAdapter()
        }
    }

    private fun bindViews(purchases: List<Purchase>) {
        buttonBack?.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        (recycler?.adapter as? ProfileAdapter)?.apply {
            bindProfile(purchases)
        }
    }

    companion object {

        fun instance(purchases: ArrayList<Purchase>) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(SECTORS, purchases)
            }
        }

        private const val SECTORS = "SECTORS"

    }

}