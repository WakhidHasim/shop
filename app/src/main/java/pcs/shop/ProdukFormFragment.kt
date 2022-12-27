package pcs.shop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import pcs.shop.api.BaseRetrofit
import pcs.shop.response.login.LoginResponse
import pcs.shop.response.produk.Produk
import pcs.shop.response.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukFormFragment : Fragment() {
    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_produk_form, container, false)
        val btnProsesProduk = view.findViewById<Button>(R.id.btnProsesProduk)
        val txtFormNamaProduk = view.findViewById<TextView>(R.id.txtFormNamaProduk)
        val txtFormHargaProduk = view.findViewById<TextView>(R.id.txtFormHargaProduk)
        val txtFormStokProduk = view.findViewById<TextView>(R.id.txtFormStokProduk)

        val status = arguments?.getString("status")
        val produk = arguments?.getParcelable<Produk>("produk")

        Log.d("produkForm", produk.toString())

        if (status == "edit") {
            txtFormNamaProduk.setText(produk?.nama.toString())
            txtFormHargaProduk.setText(produk?.harga.toString())
            txtFormStokProduk.setText(produk?.stok.toString())
        }

        btnProsesProduk.setOnClickListener {
            val token = LoginActivity.sessionManager.getString("TOKEN")
            val adminId = LoginActivity.sessionManager.getString("ADMIN_ID")

            val txtFormNamaProduk = view.findViewById<TextInputEditText>(R.id.txtFormNamaProduk)
            val txtFormHargaProduk = view.findViewById<TextInputEditText>(R.id.txtFormHargaProduk)
            val txtFormStokProduk = view.findViewById<TextInputEditText>(R.id.txtFormStokProduk)

            if (status == "edit") {
                api.putProduk(token.toString(), adminId.toString().toInt(), produk?.id.toString().toInt(), txtFormNamaProduk.text.toString(), txtFormHargaProduk.text.toString().toInt(), txtFormStokProduk.text.toString().toInt()).enqueue(object :
                    Callback<ProdukResponsePost> {
                    override fun onResponse(
                        call: Call<ProdukResponsePost>,
                        response: Response<ProdukResponsePost>
                    ) {
                        Log.d("ResponData", response.body()!!.data.toString())

                        Toast.makeText(activity?.applicationContext, "Produk " + response.body()!!.data.produk.nama + " Berhasil Di Edit", Toast.LENGTH_LONG).show()

                        findNavController().navigate(R.id.produkFragment)
                    }

                    override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                        Log.e("Error", t.toString())
                    }
                })
            } else {
                api.postProduk(token.toString(), adminId.toString().toInt(), txtFormNamaProduk.text.toString(), txtFormHargaProduk.text.toString().toInt(), txtFormStokProduk.text.toString().toInt()).enqueue(object :
                    Callback<ProdukResponsePost> {
                    override fun onResponse(
                        call: Call<ProdukResponsePost>,
                        response: Response<ProdukResponsePost>
                    ) {
                        Log.d("Data", response.toString())

                        Toast.makeText(activity?.applicationContext,  "Data Produk Berhasil Di Tambahkan", Toast.LENGTH_LONG).show()

                        findNavController().navigate(R.id.produkFragment)
                    }

                    override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                        Log.e("Error", t.toString())
                    }
                })
            }
        }
        return view
    }
}