package com.kicks.off.`in`.life.android.commercial_platform.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityEditAdsBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialogs.DialogSpinnerHelper
import com.kicks.off.`in`.life.android.commercial_platform.utils.CityHelper

class EditAdsAct : AppCompatActivity() {
    private lateinit var rootElement:ActivityEditAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(rootElement.root)

        val listCountry = CityHelper.getAllCountries(this)
        val dialog = DialogSpinnerHelper()
        dialog.showSpinnerDialog(this, listCountry)
    }

    //private fun init(){}


}