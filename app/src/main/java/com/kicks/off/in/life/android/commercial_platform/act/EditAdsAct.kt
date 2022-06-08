package com.kicks.off.`in`.life.android.commercial_platform.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityEditAdsBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialogs.DialogSpinnerHelper
import com.kicks.off.`in`.life.android.commercial_platform.utils.CityHelper

class EditAdsAct : AppCompatActivity() {
    lateinit var binding:ActivityEditAdsBinding
    private var dialog = DialogSpinnerHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){

    }

    //OnClicks
    fun onClickSelectCountry(view: View){
        val listCountry = CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, listCountry, binding.tvCountry)
        if (binding.tvCity.text.toString() != getString(R.string.select_city)){
            binding.tvCity.text = getString(R.string.select_city)
        }

    }
    fun onClickSelectCity(view: View){
        val selectedCountry = binding.tvCountry.text.toString()
        if (selectedCountry !== getString(R.string.select_country)){
            val listCity = CityHelper.getAllCities(selectedCountry,this)
            dialog.showSpinnerDialog(this, listCity, binding.tvCity)
        } else {
            Toast.makeText(this, "No country selected", Toast.LENGTH_LONG).show()
        }


    }

}