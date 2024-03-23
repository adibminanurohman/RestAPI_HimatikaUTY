package com.laznaslmi.restapi_himatikauty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.laznaslmi.restapi_himatikauty.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityMainBinding
private lateinit var adapter: RVAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RVAdapter(this@MainActivity, arrayListOf())

        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)

        remoteGetUsers()
    }

    fun remoteGetUsers(){
        ApiClient.apiService.getUsers().enqueue(object : Callback<ArrayList<ResponseModelItem>>{

            override fun onResponse(
                call: Call<ArrayList<ResponseModelItem>>,
                response: Response<ArrayList<ResponseModelItem>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    setDataToAdapter(data!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseModelItem>>, t: Throwable) {
                Log.d("error", "" + t.stackTraceToString())
            }

        })
    }

    fun setDataToAdapter(data: ArrayList<ResponseModelItem>){
        adapter.setData(data)
    }
}