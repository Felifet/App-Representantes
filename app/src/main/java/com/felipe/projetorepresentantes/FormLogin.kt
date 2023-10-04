package com.felipe.projetorepresentantes

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FormLogin : AppCompatActivity() {

    private lateinit var text_tela_cadastro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)
        supportActionBar?.hide()
        iniciarComponentes()

        text_tela_cadastro.setOnClickListener {
            val intent = Intent(this@FormLogin, FormCadastro::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarComponentes() {
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro)
    }

}

