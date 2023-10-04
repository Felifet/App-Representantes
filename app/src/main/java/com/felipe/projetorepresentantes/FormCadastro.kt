package com.felipe.projetorepresentantes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FormCadastro : AppCompatActivity() {

    private lateinit var edit_nome: EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    private lateinit var bt_cadastrar: Button

    private val mensagens = arrayOf("Preencha todos os campos!", "Cadastro realizado com sucesso!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar?.hide()
        iniciarComponentes()

        bt_cadastrar.setOnClickListener {
            val nome = edit_nome.text.toString()
            val email = edit_email.text.toString()
            val senha = edit_senha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(it, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(resources.getColor(android.R.color.white))
                snackbar.setTextColor(resources.getColor(android.R.color.black))
                snackbar.show()
            } else {
                cadastrarUsuario(it)
            }
        }
    }

    private fun iniciarComponentes() {
        edit_nome = findViewById(R.id.edit_nome)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_senha)
        bt_cadastrar = findViewById(R.id.bt_cadastrar)
    }

    private fun cadastrarUsuario(v: View) {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(resources.getColor(android.R.color.white))
                    snackbar.setTextColor(resources.getColor(android.R.color.black))
                    snackbar.show()
                } else {
                    val erro: String = when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> "A senha deve conter no mínimo 6 caracteres!"
                        is FirebaseAuthUserCollisionException -> "O e-mail já está em uso!"
                        is FirebaseAuthInvalidCredentialsException -> "E-mail inválido!"
                        else -> "Erro ao cadastrar usuário!"
                    }

                    val snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(resources.getColor(android.R.color.white))
                    snackbar.setTextColor(resources.getColor(android.R.color.black))
                    snackbar.show()
                }
            }
    }


}