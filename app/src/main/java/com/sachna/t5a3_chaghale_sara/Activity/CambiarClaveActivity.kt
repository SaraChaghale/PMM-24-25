package com.sachna.t5a3_chaghale_sara.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sachna.t5a3_chaghale_sara.databinding.ActivityCambiarClaveBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente
import com.sachna.t5a3_chaghale_sara.dao.ClienteDAO

class CambiarClaveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCambiarClaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarClaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el cliente logueado desde el Intent (si es que se pasa desde la actividad anterior)
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        binding.buttonGuardarClave.setOnClickListener {

            val nuevaClave = binding.editTextNuevaClave.text.toString()

            // Verificar que la clave tenga al menos 3 dígitos
            if (nuevaClave.length >= 3) {
                // Verificar si el cliente está logueado
                if (cliente != null) {
                    // Actualizar la clave en la base de datos
                    val clienteDAO = ClienteDAO()
                    cliente.setClaveSeguridad(nuevaClave)  // Asegúrate de tener un método setClave en la clase Cliente
                    val resultado = clienteDAO.update(cliente)

                    if (resultado != -1) {
                        // Si la actualización fue exitosa, mostrar el Toast
                        Toast.makeText(this, "Clave guardada exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        // Si hubo algún error al guardar, mostrar un mensaje
                        Toast.makeText(this, "Error al guardar la clave", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Si no hay cliente logueado, mostrar un mensaje
                    Toast.makeText(this, "No hay cliente logueado", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Si la clave no tiene al menos 3 dígitos, mostrar un mensaje de error
                Toast.makeText(this, "La clave debe tener al menos 3 dígitos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
