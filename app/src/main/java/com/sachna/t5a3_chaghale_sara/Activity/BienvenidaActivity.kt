package com.sachna.t5a3_chaghale_sara.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sachna.t5a3_chaghale_sara.R
import com.sachna.t5a3_chaghale_sara.databinding.BienvenidaBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente

class BienvenidaActivity : AppCompatActivity() {
    private lateinit var binding: BienvenidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtiene el objeto Cliente desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente

        // Asegúrate de que cliente no sea nulo antes de acceder a sus propiedades
        cliente?.let {
            binding.textViewWelcome.text = getString(R.string.welcome_message) + ", \n ${it.getNombre()} !"
        } ?: run {
            binding.textViewWelcome.text = getString(R.string.welcome_message) + ", \n Usuario desconocido"
        }

        // Navegación a la actividad CambiarClaveActivity
        binding.buttonCambiarClave.setOnClickListener {
            val cliente = intent.getSerializableExtra("Cliente") as? Cliente// obtener el cliente logueado
            val intent = Intent(this, CambiarClaveActivity::class.java)
            intent.putExtra("Cliente", cliente)  // Asegúrate de que 'cliente' sea un Serializable
            startActivity(intent)

        }

        // Navegación a la actividad TransferActivity
        binding.buttontrasnferencias.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        // Navegación a la actividad GlobalPositionActivity
        binding.buttonPosicionGlobal.setOnClickListener {
            val cliente = intent.getSerializableExtra("Cliente") as? Cliente
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)  //
            startActivity(intent)
        }

        binding.movimientos.setOnClickListener{
            val cliente = intent.getSerializableExtra("Cliente") as? Cliente
            val intent = Intent(this, MovimientosActivity::class.java)
            intent.putExtra("Cliente", cliente)  //
            startActivity(intent)

        }

        // Cerrar la actividad actual
        binding.buttonSalir.setOnClickListener {
            finish()
        }
    }
}
