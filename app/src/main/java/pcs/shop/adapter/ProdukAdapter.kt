package pcs.shop.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import pcs.shop.LoginActivity
import pcs.shop.R
import pcs.shop.api.BaseRetrofit
import pcs.shop.response.produk.Produk
import pcs.shop.response.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukAdapter(val listProduk: List<Produk>): RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {
    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHargaProduk.text = produk.harga

        val token = LoginActivity.sessionManager.getString("TOKEN")

        holder.btnDelete.setOnClickListener {
            api.deleteProduk(token.toString(), produk.id.toInt()).enqueue(object :
                Callback<ProdukResponsePost> {
                override fun onResponse(
                    call: Call<ProdukResponsePost>,
                    response: Response<ProdukResponsePost>
                ) {
                    Log.d("Data", response.toString())

                    Toast.makeText(holder.itemView.context, "Produk " + produk.nama + " Berhasil Di Hapus", Toast.LENGTH_LONG).show()

                    holder.itemView.findNavController().navigate(R.id.produkFragment)
                }

                override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                    Log.e("Data", t.toString())
                }
            })
        }

        holder.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("produk", produk)
            bundle.putString("status", "edit")

            holder.itemView.findNavController().navigate(R.id.produkFormFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return  listProduk.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNamaProduk = itemView.findViewById<TextView>(R.id.txtNamaProduk)
        val txtHargaProduk = itemView.findViewById<TextView>(R.id.txtHargaProduk)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
        val btnEdit = itemView.findViewById<ImageButton>(R.id.btnEdit)
    }
}