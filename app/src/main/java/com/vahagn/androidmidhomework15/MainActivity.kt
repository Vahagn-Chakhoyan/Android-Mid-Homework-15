package com.vahagn.androidmidhomework15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val call = UserRetrofitService.retrofit.create(UserApi::class.java).getSingleProduct(2)

            call.enqueue(object: Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Request fail!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    lateinit var userModel: UserModel

                    if (response.isSuccessful) {
                        response.body()?.let {
                            userModel = it
                        } ?: run {
                            Toast.makeText(this@MainActivity, "Request fail!", Toast.LENGTH_SHORT).show()
                            return
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Request fail!", Toast.LENGTH_SHORT).show()
                        return
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        findViewById<TextView>(R.id.nameText).text = userModel.data?.name
                        findViewById<TextView>(R.id.descriptionText).text = userModel.data?.description
                        findViewById<TextView>(R.id.idText).text = userModel.data?.id.toString()
                        findViewById<TextView>(R.id.priceText).text = userModel.data?.price
                        findViewById<TextView>(R.id.discountAccountText).text = userModel.data?.discount_amount
                    }
                }
            })
        }
    }
}