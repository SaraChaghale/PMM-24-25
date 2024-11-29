package com.sachna.t5a3_chaghale_sara.Activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sachna.t5a3_chaghale_sara.R

class TransferActivity : AppCompatActivity() {
    private lateinit var spinnerCuentaOrigen: Spinner
    private lateinit var radioCuentaPropia: RadioButton
    private lateinit var radioCuentaAjena: RadioButton
    private lateinit var spinnerCuentaDestino: Spinner
    private lateinit var editTextCuentaDestino: EditText
    private lateinit var editTextImporte: EditText
    private lateinit var spinnerDivisa: Spinner
    private lateinit var checkBoxJustificante: CheckBox
    private lateinit var buttonEnviar: Button
    private lateinit var buttonCancelar: Button

    // Cuentas de ejemplo
    private val cuentas = listOf("ES60-2100-0418-40-12345676522", "ES80-2100-0416-40-12345476500", "ES45-2432-0418-40-123456765259", "ES34-9890-0418-40-12345698928")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        // Inicialización de vistas
        spinnerCuentaOrigen = findViewById(R.id.spinnerCuentaOrigen)
        radioCuentaPropia = findViewById(R.id.radioCuentaPropia)
        radioCuentaAjena = findViewById(R.id.radioCuentaAjena)
        spinnerCuentaDestino = findViewById(R.id.spinnerCuentaDestino)
        editTextCuentaDestino = findViewById(R.id.editTextCuentaDestino)
        editTextImporte = findViewById(R.id.editTextImporte)
        spinnerDivisa = findViewById(R.id.spinnerDivisa)
        checkBoxJustificante = findViewById(R.id.checkBoxJustificante)
        buttonEnviar = findViewById(R.id.buttonEnviar)
        buttonCancelar = findViewById(R.id.buttonCancelar)

        // Adaptador para los Spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCuentaOrigen.adapter = adapter
        spinnerCuentaDestino.adapter = adapter

        // Acciones para los RadioButton
        radioCuentaPropia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                spinnerCuentaDestino.visibility = View.VISIBLE
                editTextCuentaDestino.visibility = View.GONE
            }
        }
        radioCuentaAjena.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                spinnerCuentaDestino.visibility = View.GONE
                editTextCuentaDestino.visibility = View.VISIBLE
            }
        }

        // Acción para el botón Enviar
        buttonEnviar.setOnClickListener {
            val cuentaOrigen = spinnerCuentaOrigen.selectedItem.toString()
            val cuentaDestino = if (radioCuentaPropia.isChecked) {
                spinnerCuentaDestino.selectedItem.toString()
            } else {
                editTextCuentaDestino.text.toString()
            }
            val importe = editTextImporte.text.toString()
            val justificante = if (checkBoxJustificante.isChecked) "Sí" else "No"

            // Validar que los campos no estén vacíos
            if (cuentaOrigen.isNotEmpty() && cuentaDestino.isNotEmpty() && importe.isNotEmpty()) {
                val mensaje = "Cuenta Origen: $cuentaOrigen\n\nCuenta Destino: $cuentaDestino\n\nImporte: $importe\n\nJustificante: $justificante"
                // Mostrar el mensaje en el Toast
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
                finish()

            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            }
        }


        // Acción para el botón Cancelar
        buttonCancelar.setOnClickListener {
            spinnerCuentaOrigen.setSelection(0)
            spinnerCuentaDestino.setSelection(0)
            editTextCuentaDestino.text.clear()
            editTextImporte.text.clear()
            spinnerDivisa.setSelection(0)
            checkBoxJustificante.isChecked = false
        }
    }
}
