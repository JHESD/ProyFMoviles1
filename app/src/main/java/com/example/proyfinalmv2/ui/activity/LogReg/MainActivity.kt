package com.example.proyfinalmv2.ui.activity.LogReg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.proyfinalmv2.R
import com.example.proyfinalmv2.databinding.ActivityMainBinding
import com.example.proyfinalmv2.repositories.items.LogReg.TokenRepository
import com.example.proyfinalmv2.ui.activity.Rest.glb.MActivityRestGlb

import com.example.proyfinalmv2.ui.activity.Rest.inv.ActivityInvitado
import com.example.proyfinalmv2.ui.viewmodels.MainViewModel
import org.json.JSONObject.NULL

class MainActivity : AppCompatActivity() {
    // Datos del Layout
    lateinit var txtLinkRegister: TextView // TextView para el enlace de registro
    lateinit var txtInvitado: TextView

    // Iniciar el activity main
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar el TextView
        txtLinkRegister = findViewById(R.id.txtLinkRegister)
        txtLinkRegister.setOnClickListener {
            goToInitRegister()
        }

        // Inicializar el TextView
        txtInvitado = findViewById(R.id.txtInvitado)
        txtInvitado.setOnClickListener {
            goToInitInvitado()
        }

        // Comprobar el token
        checkToken()

        setupEventListeners()
        setupViewModelObservers()
    }

    // Observers
    private fun setupViewModelObservers() {
        // Error
        model.errorMessage.observe(this) {
            // Comprobar si hay un error
            if (it.isNotEmpty()) {
                Toast.makeText(
                    this,
                    it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Funciones del iniciar sesion
    private fun setupEventListeners() {
        // Boton para iniciar sesion
        binding.bttLogin.setOnClickListener {
            // Obtener los datos del usuario
            val email = binding.lgNameUser.text.toString()
            val password = binding.lgPasswordUser.text.toString()
            model.login(email, password, this)
        }
    }


    // Comprobar el token
    private fun checkToken() {
        // Comprobar si hay un token
        val token = TokenRepository.getToken(this)
        if (token != null) {
            // Mostrar el token
            Toast.makeText(
                this,
                "El token es: $token",
                Toast.LENGTH_SHORT).show()
        }
    }


    // -- Funciones de los TXT  -- \\
    //Función para ir al registro
    private fun goToInitRegister() {
        // Ir al registro
        val intent = Intent(
            this,
            MainActivityRegister::class.java // Actividad de registro
        )
        startActivity(intent)
    }

    // Funcion para ingresar como Invitado
    private fun goToInitInvitado() {
        // Ir al registro
        val intent = Intent(
            this,
            ActivityInvitado::class.java // Actividad de registro
        )
        startActivity(intent)
    }

}