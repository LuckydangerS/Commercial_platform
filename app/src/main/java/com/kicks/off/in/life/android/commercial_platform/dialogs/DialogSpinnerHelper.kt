package com.kicks.off.`in`.life.android.commercial_platform.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.utils.CityHelper

class DialogSpinnerHelper {

    fun showSpinnerDialog(context: Context,list: ArrayList<String>, tvSelection:TextView){
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        val rootView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, null)
        val adapter = RcViewDialogSpinnerAdapter(tvSelection, dialog)
        val rcView = rootView.findViewById<RecyclerView>(R.id.rcSpView)
        val sv = rootView.findViewById<android.widget.SearchView>(R.id.svSpinner)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter

        dialog.setView(rootView)
        adapter.updateAdapter(list)
        setSearchView(adapter, list, sv)

        dialog.show()


    }

    private fun setSearchView(adapter: RcViewDialogSpinnerAdapter, list: ArrayList<String>, sv: android.widget.SearchView?) {
        sv?.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tempList = CityHelper.filterListData(list, newText)
                adapter.updateAdapter(tempList)
                return true
            }
        })

    }

}