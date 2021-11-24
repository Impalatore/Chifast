package com.maid.chifast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import android.R.*;
import android.annotation.SuppressLint

class LoginActivity : AppCompatActivity() {
    val btnlogin = findViewById<Button>(R.id.btnlogin)
    val btnregister = findViewById<Button>(R.id.btnregister)
    val username = findViewById<EditText>(R.id.username)
    val password = findViewById<EditText>(R.id.password)


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.layout.activity_intro)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integración de Firebase completa")
        analytics.logEvent("InitScreen",bundle)

        setup()
    }

    private fun setup() {
        title = "Autenticación"

        btnlogin.setOnClickListener {
            if (username.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(username.text.toString(),
                        password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }

        btnregister.setOnClickListener {
            if (username.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(username.text.toString(),
                        password.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }
        }

    }

    private fun showAlert() {
        val build = AlertDialog.Builder(this)
        build.setTitle("Error")
        build.setMessage("Se ha producido un error en la autenticacion del usruario")
        build.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = build.create()
        dialog.show()
    }

    private fun showhome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, R.layout.activity_main::class.java).apply {
            putExtra("email", email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }
}