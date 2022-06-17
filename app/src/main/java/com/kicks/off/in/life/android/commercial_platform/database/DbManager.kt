package com.kicks.off.`in`.life.android.commercial_platform.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DbManager {
    private val db = Firebase.database.getReference("main")

    fun publishAd(){

        db.setValue("Hola")
    }


}