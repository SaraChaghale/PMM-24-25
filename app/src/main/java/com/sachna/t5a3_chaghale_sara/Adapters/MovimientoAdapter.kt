package com.sachna.t5a3_chaghale_sara.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachna.t5a3_chaghale_sara.R
import com.sachna.t5a3_chaghale_sara.pojo.Movimiento

class MovimientoAdapter(private var movimientos: List<Movimiento>) :
    RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder>() {

    // ViewHolder para el RecyclerView
    class MovimientoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descripcion: TextView = view.findViewById(R.id.textViewDescripcion)
        val monto: TextView = view.findViewById(R.id.textViewMonto)
        val fecha: TextView = view.findViewById(R.id.textViewFecha)
    }

    // Método para actualizar la lista de movimientos
    fun setMovimientos(nuevosMovimientos: List<Movimiento>) {
        movimientos = nuevosMovimientos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movimiento, parent, false)
        return MovimientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovimientoViewHolder, position: Int) {
        val movimiento = movimientos[position]
        holder.descripcion.text = movimiento.getDescripcion()
        holder.monto.text = movimiento.getImporte().toString()
        holder.fecha.text = movimiento.getFechaOperacion().toString()
    }

    override fun getItemCount(): Int = movimientos.size
}
