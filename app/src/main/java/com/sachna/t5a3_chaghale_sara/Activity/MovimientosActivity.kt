package com.sachna.t5a3_chaghale_sara.Activity

import com.sachna.t5a3_chaghale_sara.Adapters.MovimientoAdapter
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachna.t5a3_chaghale_sara.dao.ClienteDAO
import com.sachna.t5a3_chaghale_sara.dao.MovimientoDAO
import com.sachna.t5a3_chaghale_sara.databinding.ActivityMovimientosBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente
import com.sachna.t5a3_chaghale_sara.pojo.Cuenta
import com.sachna.t5a3_chaghale_sara.pojo.Movimiento
import com.t5a3_chaghale_sara.dao.CuentaDAO

class MovimientosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovimientosBinding
    private lateinit var cuentas: List<Cuenta>
    private lateinit var movimientos: List<Movimiento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intentar obtener el cliente desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente ?: obtenerClienteLogueado()

        if (cliente != null) {
            // Cargar las cuentas del cliente
            val cuentaDAO = CuentaDAO()
            cuentas = cuentaDAO.getCuentas(cliente) as List<Cuenta>

            // Configurar el Spinner con las cuentas
            val cuentasAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                cuentas.map { it.getNumeroCuenta() ?: "Cuenta desconocida" }
            )
            cuentasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCuentas.adapter = cuentasAdapter

            // Configurar el RecyclerView para los movimientos
            movimientos = listOf()
            val movimientosAdapter = MovimientoAdapter(movimientos)
            binding.recyclerViewMovimientos.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewMovimientos.adapter = movimientosAdapter

            // Cuando se seleccione una cuenta, actualizar los movimientos
            binding.spinnerCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val cuentaSeleccionada = cuentas[position]
                    movimientos = obtenerMovimientos(cuentaSeleccionada)
                    movimientosAdapter.setMovimientos(movimientos)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        } else {
            Toast.makeText(this, "No hay cliente logueado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerClienteLogueado(): Cliente? {
        val sharedPreferences = getSharedPreferences("usuario_logueado", Context.MODE_PRIVATE)
        val clienteId = sharedPreferences.getInt("cliente_id", -1)
        return if (clienteId != -1) {
            val cliente = Cliente()
            cliente.setId(clienteId)
            val clienteDAO = ClienteDAO()
            clienteDAO.search(cliente) as Cliente
        } else {
            null
        }
    }

    private fun obtenerMovimientos(cuenta: Cuenta): List<Movimiento> {
        val movimientoDAO = MovimientoDAO()
        return movimientoDAO.getMovimientos(cuenta) as ArrayList<Movimiento>
    }
}
