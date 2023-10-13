package com.example.roomrecyclerview


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.adapter.RecycleAdapter
import com.example.model.DataItems
import com.example.retrofit.ApiInterface
import com.example.roomdatabase.AppDatabase
import com.example.roomrecyclerview.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var mAdapter: RecycleAdapter? = null;
    private var mList: MutableList<DataItems> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/posts/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiInterface::class.java)

        val jsonObject = JSONObject()
        jsonObject.put("userId", 234)
        jsonObject.put("id", 23)
        jsonObject.put("title", "hello this is post request")
        jsonObject.put("body", "find the body of post request")

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = api.createEmployee(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(response.body()?.string())
                    )
                    Log.e("Pretty Printed JSON :",prettyJson)
                }
                else{
                    Log.e("RETROFIT_ERROR",response.code().toString())
                }
            }
        }

        api.postEmpDetails().enqueue(object : Callback<List<DataItems>?> {
            override fun onResponse(
                call: Call<List<DataItems>?>,
                response: Response<List<DataItems>?>
            ) {
                val heroList: List<DataItems> = response.body()!!
                val heroes = arrayOfNulls<String>(heroList.size)

                for (i in heroList.indices) {
                    heroes[i] = heroList[i].title
                }
                val listView: RecyclerView = binding.recyclerView
                listView.layoutManager = LinearLayoutManager(applicationContext)

                mAdapter = RecycleAdapter(applicationContext, mList, R.layout.recycle_items)
                listView.adapter = mAdapter

            }

            override fun onFailure(call: Call<List<DataItems>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_SHORT).show()
            }
        })

//        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database")
//            .build()
//
//        db.postDao()

    }
}



