package pcs.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pcs.shop.R
import pcs.shop.response.produk.Produk

class ProdukAdapter(val listProduk: List<Produk>): RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHargaProduk.text = produk.harga
    }

    override fun getItemCount(): Int {
        return  listProduk.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNamaProduk = itemView.findViewById<TextView>(R.id.txtNamaProduk)
        val txtHargaProduk = itemView.findViewById<TextView>(R.id.txtHargaProduk)
    }
}