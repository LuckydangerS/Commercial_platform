package com.kicks.off.`in`.life.android.commercial_platform.utils

import androidx.appcompat.app.AppCompatActivity
import com.fxn.pix.Options
import com.fxn.pix.Pix


object ImagePicker {
    const val MAX_IMAGE_COUNT = 3
    const val REQUEST_CODE_GET_IMAGES = 999
    const val REQUEST_CODE_GET_SINGLE_IMAGES = 998

    fun getImages(context: AppCompatActivity, imageCounter : Int, rCode : Int){
        val options = Options.init()
            .setRequestCode(rCode)
            .setCount(imageCounter)
            .setFrontfacing(false)
            .setMode(Options.Mode.Picture)
            .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)
            .setPath("/pix/images")

        Pix.start(context, options)
    }


}












/*


    private fun getOptions(imageCounter : Int) : BitmapFactory.Options {
        val options = BitmapFactory.Options().apply {
           // ratio = Ratio.RATIO_AUTO                                    //Image/video capture ratio
            count = imageCounter
            isFrontFacing =  false
            mode = Mode.Picture
            path = "Pix/Camera"
        }
        return options
    }


    fun getImages(context: AppCompatActivity){

        }
        addPixToActivity(R.id.container, options) {
            when (it.status) {
                PixEventCallback.Status.SUCCESS -> //use results as it.data
                    PixEventCallback.Status.BACK_PRESSED -> // back pressed called
            }
        }
*/
