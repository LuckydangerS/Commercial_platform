package com.kicks.off.`in`.life.android.commercial_platform.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
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

        setDialogState(index, rootDialogElement)

        val dialog = builder.create()
        rootDialogElement.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, rootDialogElement, dialog)
        }
        rootDialogElement.btForgetP.setOnClickListener {
            setOnClickResetPassword(rootDialogElement, dialog)
        }
        dialog.show()

    }

    private fun setOnClickResetPassword
                (rootDialogElement: SingDialogBinding, dialog: AlertDialog?) {
        if (rootDialogElement.edSignEmail.text.isNotEmpty()) {
            act.mAuth.sendPasswordResetEmail(rootDialogElement.edSignEmail.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(act, R.string.email_reset_password_was_sent,
                            Toast.LENGTH_LONG).show()
                    }
            }
            dialog?.dismiss()
        } else {

            rootDialogElement.tvDialogMesage.visibility = View.VISIBLE

        }

    }

    private fun setOnClickSignUpIn(
        index: Int, rootDialogElement: SingDialogBinding, dialog: AlertDialog?) {
        dialog?.dismiss()
        if(index == DialogConst.SIGN_UP_STATE){
            accHelper.signUpWithEmail(rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString())
        } else {
            accHelper.signInWithEmail(rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString())

        }

    }

    private fun setDialogState(index: Int, rootDialogElement: SingDialogBinding) {

        if(index == DialogConst.SIGN_UP_STATE){
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)

        } else{
            rootDialogElement.tvSingTitle.text = act.resources.getString(R.string.ac_sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
            rootDialogElement.btForgetP.visibility = View.VISIBLE
        }

    }
}