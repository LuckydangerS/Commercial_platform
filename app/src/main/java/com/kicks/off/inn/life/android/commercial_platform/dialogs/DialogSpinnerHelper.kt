package com.kicks.off.inn.life.android.commercial_platform.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.inn.life.android.commercial_platform.R

class DialogSpinnerHelper {

    fun showSpinnerDialog(context: Context,list: ArrayList<String>){
        val builder = AlertDialog.Builder(context)
        val rootView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null)
        val adapter = RcViewDialogSpinner()
        val rcView = rootView.findViewById<RecyclerView>(R.id.rcSpView)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter

        builder.setView(rootView)
        adapter.updateAdapter(list)
        builder.show()

    }
}