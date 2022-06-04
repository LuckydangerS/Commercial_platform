package com.kicks.off.`in`.life.android.commercial_platform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kicks.off.`in`.life.android.commercial_platform.act.EditAdsAct
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ActivityMainBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialoghelper.DialogConst
import com.kicks.off.`in`.life.android.commercial_platform.dialoghelper.DialogHelper
import com.kicks.off.`in`.life.android.commercial_platform.dialoghelper.GoogleAccConst


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
/*    private lateinit var tvAccount:TextView
    private lateinit var rootElement : ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)
        init()
    }*/
    private lateinit var tvAccount:TextView

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val dialogHelper = DialogHelper(this)
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.id_new_ads){
            val i = Intent(this, EditAdsAct::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE){
            //Log.d("MyLog", "Sign in result")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    dialogHelper.accHelper.signInFirebaseWithGoogle(account.idToken!!)
                }

            }catch (e:ApiException){
                Log.d("Mylog", "Api error : ${e.message}")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)
    }

    private fun init(){
        setSupportActionBar(binding.mainContent.toolbar)

        val toggle = ActionBarDrawerToggle(this,
            binding.drawerLayout,
            binding.mainContent.toolbar,
            R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.id_my_ads -> {
                Toast.makeText(this, "Presed id_my_ads", Toast.LENGTH_LONG).show()
            }
            R.id.id_car -> {
                Toast.makeText(this, "Presed id_my_!vvvv!", Toast.LENGTH_LONG).show()
            }
            R.id.id_pc -> {}
            R.id.id_dm -> {}
            R.id.id_sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
            }
            R.id.id_sign_in -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)
            }
            R.id.id_sign_out -> {
                uiUpdate(null)
                mAuth.signOut()
                dialogHelper.accHelper.signOutG()

            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?){
        tvAccount.text = if (user == null){
            resources.getString(R.string.not_reg)

        } else {
            user.email

        }

    }
}