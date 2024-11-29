package com.sachna.t5a3_chaghale_sara.Adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachna.t5a3_chaghale_sara.R
import com.sachna.t5a3_chaghale_sara.pojo.Cuenta

class CuentaAdapter(private val cuentas: List<Cuenta>) : RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account_card, parent, false)
        return CuentaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        val cuenta = cuentas[position]
        holder.bind(cuenta)
    }

    override fun getItemCount(): Int {
        return cuentas.size
    }

    inner class CuentaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val saldoTextView: TextView = itemView.findViewById(R.id.saldoCuenta)
        private val bancoTextView: TextView = itemView.findViewById(R.id.bancoCuenta)
        private val numeroCuentaTextView: TextView = itemView.findViewById(R.id.numeroCuenta)

        fun bind(cuenta: Cuenta) {
            saldoTextView.text = "Saldo: ${cuenta.getSaldoActual()}"
            bancoTextView.text = "Banco: ${cuenta.getBanco()}"
            numeroCuentaTextView.text = "Número: ${cuenta.getNumeroCuenta()}"
        }
    }
}
