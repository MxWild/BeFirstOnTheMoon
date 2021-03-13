package com.spacesale.befirstonthemoon.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spacesale.befirstonthemoon.databinding.FragmentProfileBinding
import com.spacesale.befirstonthemoon.domain.Purchase
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var recycler: RecyclerView? = null

    private var buttonBack: ImageView? = null

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        arguments?.getInt(USER_ID)?.let { userId ->
            profileViewModel.purchasesLiveData.observe(this.viewLifecycleOwner) {
                bindViews(it)
            }
            profileViewModel.showPurchases(userId)
        }

    }

    override fun onDestroyView() {
        buttonBack = null
        recycler?.adapter = null
        recycler = null
        super.onDestroyView()
    }

    private fun initViews() {
        buttonBack = binding.back

        recycler = binding.sectorsList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ProfileAdapter()
        }
    }

    private fun bindViews(purchases: List<Purchase>) {
        buttonBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        (recycler?.adapter as? ProfileAdapter)?.apply {
            bindProfile(purchases)
        }
    }

    companion object {

        fun instance(userId: Int) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ID, userId)
            }
        }

        private const val USER_ID = "SECTORS"

    }

}