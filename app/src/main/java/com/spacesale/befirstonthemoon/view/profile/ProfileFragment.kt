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

class ProfileFragment : Fragment() {

    private var recycler: RecyclerView? = null

    private var buttonBack: TextView? = null

    //private var sectors: List<Sector>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        //todo
        //sectors = arguments?.getParcelableArrayList<Sector>(SECTORS)
        bindViews(/**/)
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

    //todo
    private fun bindViews(/*data: List<Sectors>*/) {
        buttonBack?.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        (recycler?.adapter as? ProfileAdapter)?.apply {
            bindProfile()
        }
    }

    companion object {

        //todo
        fun instance(/*sectors: List<Sector>*/) = ProfileFragment().apply {
            arguments = Bundle().apply {
                //putParcelableArrayList(SECTORS, sectors)
            }
        }

        private const val SECTORS = "SECTORS"

    }

}