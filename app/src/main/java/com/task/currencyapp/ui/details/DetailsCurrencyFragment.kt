package com.task.currencyapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.task.currencyapp.R
import com.task.currencyapp.ui.details.adapter.DetailsCurrencyAdapter
import kotlinx.android.synthetic.main.fragment_currency_details.rvCurrencyList
import kotlinx.android.synthetic.main.fragment_currency_details.tvTitle

class DetailsCurrencyFragment : Fragment() {

    private val args: DetailsCurrencyFragmentArgs by navArgs()

    private lateinit var adapter: DetailsCurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tvTitle.text = getString(R.string.details)
        adapter = DetailsCurrencyAdapter(ArrayList())
        rvCurrencyList.adapter = adapter
        adapter.addAll(args.ratesList)
        adapter.notifyDataSetChanged()
    }
}
