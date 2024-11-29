package com.sachna.t5a3_chaghale_sara.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachna.t5a3_chaghale_sara.Adapters.CuentaAdapter
import com.sachna.t5a3_chaghale_sara.bd.MiBancoOperacional
import com.sachna.t5a3_chaghale_sara.databinding.FragmentAccountBinding
import com.sachna.t5a3_chaghale_sara.pojo.Cliente
import com.sachna.t5a3_chaghale_sara.pojo.Cuenta

// Constante global antes de la clase
private const val ARG_CLIENTE = "cliente"

class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration


    private lateinit var cliente: Cliente
    private lateinit var listener: AccountsListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        val mbo : MiBancoOperacional?= MiBancoOperacional.getInstance(context)
        val  listaCuentas: ArrayList<Cuenta>?= mbo?.getCuentas(cliente as Cliente? ) as ArrayList<Cuenta>?

        cuentaAdapter = CuentaAdapter(listaCuentas, this)
        linearLayoutManager= LinearLayoutManager(context)
        itemDecoration= DividerItemDecoration(context, DividerItemDecoration.VERTICAL)


        binding.recyclerCuentasId.apply{
            layoutManager= linearLayoutManager
            adapter=cuentaAdapter
            addItemDecoration(itemDecoration)
        }
    return binding.root

    }

    fun setCuentasListener(listener: AccountsListener){
        this.listener=listener
    }

    override fun onClick(cuenta: Cuenta) {
      if (listener != null){
          listener.onCuentaSeleccionada(cuenta)


      }      }

        // Configuración inicial

    }

companion object {
    /**
     * Método para instanciar el fragmento con un cliente.
     */
    @JvmStatic
    fun newInstance(c: Cliente) =
        AccountsFragment().apply {
        arguments = Bundle().apply {
            putSerializable(ARG_CLIENTE, c)
        }
    }
}
