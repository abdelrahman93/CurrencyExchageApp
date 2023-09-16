package com.task.currencyapp

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.task.currencyapp.base.BaseViewState
import com.task.currencyapp.base.ErrorViewState
import com.task.currencyapp.data.Loader
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(val vmClass: Class<VM>) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    private var dialog: Dialog? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        injectDagger()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(vmClass)
        viewModel.internalState.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is ErrorViewState.Error -> {
                        Toast.makeText(
                            activity,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is Loader.Progress -> {
                        if (it.show) showLoading() else hideLoading()
                    }
                    else -> {
                        renderView(it)
                    }
                }
            }
        )
        createCustomProgressDialog()
        initView()
        startRequest()
    }

    private fun createCustomProgressDialog() {
        context?.let {
            dialog = Dialog(it, R.style.DialogCustomTheme)
            dialog?.setContentView(R.layout.loader)
            dialog?.setCancelable(false)
        }
    }

    fun showLoading() {
        dialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun hideLoading() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    abstract fun injectDagger()

    abstract fun getLayout(): Int

    abstract fun renderView(viewState: BaseViewState?)

    open fun initView() {
        // pass
    }

    open fun startRequest() {
        // pass
    }
}
