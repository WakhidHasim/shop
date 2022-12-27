package pcs.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pcs.shop.R
import pcs.shop.response.produk.Produk

class TransaksiAdapter(val listProduk: List<Produk>): RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi, parent, false)
        return TransaksiAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiAdapter.ViewHolder, position: Int) {
        val  produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHargaProduk.text = produk.harga

        holder.btnPlus.setOnClickListener {
            val old_value = holder.txtQty.text.toString().toInt()
            val new_value = old_value+1

            holder.txtQty.setText(new_value.toString())
        }

        holder.btnMinus.setOnClickListener {
            val old_value = holder.txtQty.text.toString().toInt()
            val new_value = old_value-1

            if (new_value>=0) {
                holder.txtQty.setText(new_value.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return  listProduk.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNamaProduk = itemView.findViewById<TextView>(R.id.txtNamaProduk)
        val txtHargaProduk = itemView.findViewById<TextView>(R.id.txtHargaProduk)
        val txtQty = itemView.findViewById<TextView>(R.id.txtQty)
        val btnPlus = itemView.findViewById<ImageButton>(R.id.btnPlus)
        val btnMinus = itemView.findViewById<ImageButton>(R.id.btnMinus)
    }
}