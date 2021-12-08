package com.example.mymusicplayer.ui.news

import com.example.mymusicplayer.BaseFragment
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.NewsFragmentBinding

//主页
class NewsFragment : BaseFragment<NewsFragmentBinding,NewsViewModel>() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun getContentViewId(): Int {
        return R.layout.news_fragment
    }

    override fun bindViewModel() {
        mDataBinding.viewmodel = mViewModel
    }

    override fun getViewModelClass(): Class<NewsViewModel> {
        return NewsViewModel::class.java
    }
}
