package com.kicks.off.`in`.life.android.commercial_platform.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityEditAdsBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialogs.DialogSpinnerHelper
import com.kicks.off.`in`.life.android.commercial_platform.utils.CityHelper

class EditAdsAct : AppCompatActivity() {
    private lateinit var binding:ActivityEditAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCountry = CityHelper.getAllCountries(this)
        val dialog = DialogSpinnerHelper()
        dialog.showSpinnerDialog(this, listCountry)
    }

    //private fun init(){}


}