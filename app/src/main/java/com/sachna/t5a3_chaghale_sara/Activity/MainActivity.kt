package com.sachna.t5a3_chaghale_sara.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sachna.t5a3_chaghale_sara.R
import com.sachna.t5a3_chaghale_sara.bd.MiBancoOperacional
import com.sachna.t5a3_chaghale_sara.databinding.ActivityMainBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding.buttonLogin.setOnClickListener {
            if (validateFields()) {
                val cliente = Cliente()
                cliente.setNif(binding.editTextDNI.text.toString())
                cliente.setClaveSeguridad(binding.editTextPassword.text.toString())

                val clienteLogeado = mbo?.login(cliente) ?: -1
                if (clienteLogeado == -1) {
                    Toast.makeText(this, "El cliente no existe", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, BienvenidaActivity::class.java).apply {
                        putExtra("Cliente", clienteLogeado) // Pasar el DNI al Intent
                    }
                    startActivity(intent)
                }
            }
        }

        binding.buttonExit.setOnClickListener {
            finish()
        }
    }

    private fun validateFields(): Boolean {
        val dni = binding.editTextDNI.text.toString()
        val password = binding.editTextPassword.text.toString()

        val dniPattern = "^[0-9]{8}[A-Za-z]$".toRegex()

        return when {
            dni.isEmpty() || password.isEmpty() -> {
                Toast.makeText(this, getString(R.string.complete_fields), Toast.LENGTH_SHORT).show()
                false
            }
            !dni.matches(dniPattern) -> {
                Toast.makeText(this, getString(R.string.invalid_dni), Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 3 -> {
                Toast.makeText(this, "Error, la contraseña debe ser de 3 caracteres mínimo", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}

