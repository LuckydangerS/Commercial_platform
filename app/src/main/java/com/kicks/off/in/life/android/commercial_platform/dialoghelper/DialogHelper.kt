package com.kicks.off.`in`.life.android.commercial_platform.dialoghelper

import android.app.AlertDialog
import com.kicks.off.`in`.life.android.commercial_platform.MainActivity
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.accounthelper.AccountHelper
import com.kicks.off.`in`.life.android.commercial_platform.databinding.SingDialogBinding


class DialogHelper(private val act: MainActivity) {
    private val accHelper = AccountHelper(act)

    fun createSignDialog(index:Int) {
        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SingDialogBinding.inflate(act.layoutInflater)
    //    builder.setView(rootDialogElement.root)
        val view = rootDialogElement.root
        builder.setView(view)
        if(index == DialogConst.SIGN_UP_STATE){
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)

        } else{
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)

        }
        val dialog = builder.create()
        rootDialogElement.btSignUpIn.setOnClickListener {
            dialog.dismiss()
            if(index == DialogConst.SIGN_UP_STATE){
                accHelper.signUpWithEmail(rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString())
            } else {
                accHelper.signInWithEmail(rootDialogElement.edSignEmail.text.toString(),
                    rootDialogElement.edSignPassword.text.toString())

            }
        }

        dialog.show()

    }
}