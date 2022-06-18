package com.kicks.off.`in`.life.android.commercial_platform.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kicks.off.`in`.life.android.commercial_platform.data.Ad

class DbManager {
    val db = Firebase.database.getReference("main")

    fun publishAd(ad:Ad){
        db.child(ad.key ?: "empty" ).setValue(ad)
    }


}