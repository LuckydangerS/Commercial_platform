package com.kicks.off.`in`.life.android.commercial_platform.accounthelper

import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import com.kicks.off.`in`.life.android.commercial_platform.MainActivity
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.constans.FirebaseAuthConstans
import com.kicks.off.`in`.life.android.commercial_platform.dialoghelper.GoogleAccConst

class AccountHelper(private val act: MainActivity) {

    private lateinit var signInClient: GoogleSignInClient

    fun signUpWithEmail(email:String, password:String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            act.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
                if(task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user)

                } else{
                    Toast.makeText(act, act.resources.getString(R.string.sign_up_error),
                        Toast.LENGTH_LONG).show()
                    if (task.exception is FirebaseAuthUserCollisionException){
                        val exception = task.exception as FirebaseAuthUserCollisionException
                        if (exception.errorCode == FirebaseAuthConstans.ERROR_EMAIL_ALREADY_IN_USE)
                        {
                            Toast.makeText(act, FirebaseAuthConstans.ERROR_EMAIL_ALREADY_IN_USE,
                                Toast.LENGTH_LONG).show()
                            //Link email
                            linkEmailToG(email, password)
                        }

                    } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        val exception = task.exception as FirebaseAuthInvalidCredentialsException
                        if (exception.errorCode == FirebaseAuthConstans.ERROR_INVALID_EMAIL) {
                            Toast.makeText(act, FirebaseAuthConstans.ERROR_INVALID_EMAIL, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

    }

    fun signInWithEmail(email:String, password:String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            act.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task->
                if(task.isSuccessful){
                    act.uiUpdate(task.result?.user)

                } else{
                    Toast.makeText(act, act.resources.getString(R.string.sign_in_error),
                        Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    private fun linkEmailToG(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        act.mAuth.currentUser?.linkWithCredential(credential)?.addOnCompleteListener { task->
            if (task.isSuccessful){
                Toast.makeText(act, act.resources.getString(R.string.link_done), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getSignInClient() : GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(act.getString(com.firebase.ui.auth.R.string.default_web_client_id)).requestEmail().build()
        return GoogleSignIn.getClient(act, gso)
    }
    fun signInWithGoogle(){
        signInClient = getSignInClient()
        val intent =signInClient.signInIntent
        act.startActivityForResult(intent, GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE)
    }
    fun signInFirebaseWithGoogle(token:String){
        val credential = GoogleAuthProvider.getCredential(token, null)
        act.mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(act, "Sign in done", Toast.LENGTH_LONG).show()
                act.uiUpdate(task.result?.user)
            } else {
                Log.d("Mylog", "Google Sign In Exception : ${task.exception}")
            }
        }
    }

    private fun sendEmailVerification(user: FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(act, act.resources.getString(R.string.send_verification_done),
                    Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(act, act.resources.getString(R.string.send_verification_error),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

}