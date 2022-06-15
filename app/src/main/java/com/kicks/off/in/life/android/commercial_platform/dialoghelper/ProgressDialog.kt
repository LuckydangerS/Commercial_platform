package com.kicks.off.`in`.life.android.commercial_platform.dialoghelper

import android.app.Activity
import android.app.AlertDialog
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ProgressDialogLayoutBinding


object ProgressDialog {
    fun createProgressDialog(act: Activity) : AlertDialog {
        val builder = AlertDialog.Builder(act)
        val rootDialogElement = ProgressDialogLayoutBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        return dialog

    }
}