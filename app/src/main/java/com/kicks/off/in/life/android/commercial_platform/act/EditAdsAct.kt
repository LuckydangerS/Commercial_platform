package com.kicks.off.`in`.life.android.commercial_platform.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityEditAdsBinding

class EditAdsAct : AppCompatActivity() {
    private lateinit var rootElement:ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
    }
}