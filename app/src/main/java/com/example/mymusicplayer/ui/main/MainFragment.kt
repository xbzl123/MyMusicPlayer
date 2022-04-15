package com.example.mymusicplayer.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.MainFragmentBinding

//主页
class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getContentViewId(): Int {
        return R.layout.main_fragment
    }

    override fun bindViewModel() {
        mDataBinding.viewmodel = mViewModel
//        openZipDocument?.launch(null)

    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        bundle.putString("tag","MainFragment")
        firebaseAnalytics.logEvent("currFragment", bundle)
//        mDataBinding.radarview.setSpeedx2()
//        mDataBinding.radarview.startAnimationScan()

//        mDataBinding.radarview.insertData(intArrayOf(18,2,20,16,5,13,30))
    }

    private val openZipDocument : ActivityResultLauncher<Uri>? = registerForActivityResult(
        ActivityResultContracts.OpenDocumentTree()){}

}