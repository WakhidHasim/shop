package pcs.shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pcs.shop.adapter.ProdukAdapter
import pcs.shop.adapter.TransaksiAdapter
import pcs.shop.api.BaseRetrofit
import pcs.shop.response.produk.ProdukResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiFragment : Fragment() {
    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaksi, container, false)
        getProduk(view)

        return view
    }

    fun getProduk(view: View) {
        val token = LoginActivity.sessionManager.getString("TOKEN")

        api.getProduk(token.toString()).enqueue(object  : Callback<ProdukResponse> {
            override fun onResponse(
                call: Call<ProdukResponse>,
                response: Response<ProdukResponse>
            ) {
                Log.d("ProdukData", response.body().toString())

                val rv = view.findViewById<RecyclerView>(R.id.rv_transaksi)

                rv.setHasFixedSize(true)
                rv.layoutManager = LinearLayoutManager(activity)
                val rvAdapter = TransaksiAdapter(response.body()!!.data.produk)
                rv.adapter = rvAdapter
            }

            override fun onFailure(call: Call<ProdukResponse>, t: Throwable) {
                Log.e("ProdukError", t.toString())
            }

        })
    }
}