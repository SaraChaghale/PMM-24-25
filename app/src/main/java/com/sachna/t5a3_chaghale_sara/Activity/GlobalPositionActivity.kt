package com.sachna.t5a3_chaghale_sara.Activity

import com.sachna.t5a3_chaghale_sara.Adapters.CuentaAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachna.t5a3_chaghale_sara.databinding.ActivityGlobalPositionBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente
import com.sachna.t5a3_chaghale_sara.pojo.Cuenta
import com.t5a3_chaghale_sara.dao.CuentaDAO

class GlobalPositionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el cliente logueado desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente


      //  val frgAccounts: AccountsFragment = AccountsFragment.newInstance(clienteLogeado as Cliente)
     //   supportFragmentManager.beginTransaction()
     //       .add(R.id.containerAccountsFragment. frgAccounts).commit()
      //  frgAccounts.setCuentasListener(this)

        // Verificar si el cliente está logueado
        if (cliente != null) {
            // Utilizar el método getCuentas de CuentaDAO para obtener las cuentas del cliente
            val cuentaDAO = CuentaDAO()
            val cuentas = cuentaDAO.getCuentas(cliente) as List<Cuenta>
            Log.d("GlobalPositionActivity", "Cuentas obtenidas: ${cuentas.size}")
            // Obtener cuentas del cliente

            // Configurar el RecyclerView
            val cuentaAdapter = CuentaAdapter(cuentas)
            binding.recyclerViewAccounts.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewAccounts.adapter = cuentaAdapter
            binding.recyclerViewAccounts.visibility = View.VISIBLE
        } else {
            // Si no hay cliente logueado, mostrar un Toast y no mostrar el RecyclerView
            Toast.makeText(this, "No hay cliente logueado", Toast.LENGTH_SHORT).show()
            binding.recyclerViewAccounts.visibility = View.GONE
        }
    }
}
