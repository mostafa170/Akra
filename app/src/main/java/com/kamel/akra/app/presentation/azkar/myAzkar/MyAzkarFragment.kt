package com.kamel.akra.app.presentation.azkar.myAzkar

import android.content.ContextWrapper
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.R
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.app.utilsView.MyDialog
import com.kamel.akra.databinding.DialogAddZekrBinding
import com.kamel.akra.databinding.FragmentMyAzkarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAzkarFragment : Fragment() {
    private val viewModel: MyAzkarViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(context) {
            "Context must not be null"
        }
        (context as ContextWrapper).baseContext as MainActivityEventsListener
    }

    private val myDialog: MyDialog by lazy {
        val activity = requireNotNull(this.activity) {
            "Activity is null"
        }
        MyDialog(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentMyAzkarBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        val addZekrDialogBinding: DialogAddZekrBinding =  DataBindingUtil.inflate(LayoutInflater.from(context), R.layout._dialog_add_zekr, null, false)
        val dialogAddZekr= myDialog.getDialogInstance(addZekrDialogBinding.root, Gravity.CENTER, false)
        addZekrDialogBinding.lifecycleOwner = viewLifecycleOwner
        addZekrDialogBinding.executePendingBindings()

        addZekrDialogBinding.onCancelClickedListener = View.OnClickListener {
            dialogAddZekr.dismiss()
        }

        addZekrDialogBinding.onConfirmClickedListener = View.OnClickListener {
            //TODO insert to database

        }


        viewModel.back.observe(viewLifecycleOwner){
            if (it !=null && it){
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }

        viewModel.addZekr.observe(viewLifecycleOwner){
            if (it !=null && it){
                dialogAddZekr.show()
                viewModel.onAddZekrDone()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it != null && it)
                mainActivityEventsListener.showLoading()
            else
                mainActivityEventsListener.hideLoading()
        }

        return binding.root
    }


}