package com.example.mymusicplayer.ui.custom

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.get
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.CustomViewFragmentBinding

class CustomViewFragment : Fragment() {

    companion object {
        fun newInstance() = CustomViewFragment()
    }

    private lateinit var viewModel: CustomViewViewModel

    private lateinit var binding: CustomViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomViewFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomViewViewModel::class.java)
        binding.model = viewModel
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
//        binding.fan.startAnimationRatotion()
//        binding.statistfan.insertData(intArrayOf(18,2,20,16,5,13,30))
//        binding.wifiscan.startAnimationScan()

        viewModel.isStart.observe(this){
//            binding.clock.startOrStopAnimationRatotion(!it)
//            binding.radarview.startOrStopAnimationScan(!it)
            if (it == true){
                binding.wifiscan.stopAnimationScan(true)
            }
        }

        binding.buttonAdd.setOnClickListener {
            binding.radarview.setSpeedAdd()
        }

        binding.buttonReduce.setOnClickListener {
            binding.radarview.setSpeedReduce()

        }

    }

}