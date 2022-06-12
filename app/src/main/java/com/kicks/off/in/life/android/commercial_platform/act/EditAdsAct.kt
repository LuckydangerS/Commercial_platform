package com.kicks.off.`in`.life.android.commercial_platform.act

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.fxn.pix.Pix
import com.fxn.utility.PermUtil
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.adapters.ImageAdapter
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityEditAdsBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialogs.DialogSpinnerHelper
import com.kicks.off.`in`.life.android.commercial_platform.frag.FragmentCloseInterface
import com.kicks.off.`in`.life.android.commercial_platform.frag.ImageListFrag
import com.kicks.off.`in`.life.android.commercial_platform.frag.SelectImageItem
import com.kicks.off.`in`.life.android.commercial_platform.utils.CityHelper
import com.kicks.off.`in`.life.android.commercial_platform.utils.ImagePicker

class EditAdsAct : AppCompatActivity(), FragmentCloseInterface {
    lateinit var binding:ActivityEditAdsBinding
    private var dialog = DialogSpinnerHelper()
    private lateinit var imageAdapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ImagePicker.REQUEST_CODE_GET_IMAGES) {
            if (data != null){
                val returnValues = data.getStringArrayListExtra(Pix.IMAGE_RESULTS)
                if (returnValues?.size!! > 1){
                    binding.scroolViewMain.visibility = View.GONE
                    val fm = supportFragmentManager.beginTransaction()
                    fm.replace(R.id.place_holder, ImageListFrag(this, returnValues))
                    fm.commit()
                }




            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    ImagePicker.getImages(this, 3)
                } else {
                //    isImagesPermissionGranted = false
                    Toast.makeText(this, "Apppppp", Toast.LENGTH_LONG).show()

                }
                return

            }
        }
    }

    private fun init(){
        imageAdapter = ImageAdapter()
        binding.vpimages.adapter = imageAdapter

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
    fun onClickGetImages(view: View){
        ImagePicker.getImages(this, 3)
    }

    override fun onFragClose(list : ArrayList<SelectImageItem>) {
        binding.scroolViewMain.visibility = View.VISIBLE
        imageAdapter.update(list)

    }
}