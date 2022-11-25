package com.example.reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import retrofit2.*
import java.util.Date

const val BASE_URL ="https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
private  val dataList :MutableList<Date> = mutableListOf()
    private lateinit var myAdapter: MyAdapter
    private lateinit var myRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAdapter = MyAdapter(dataList)

    myRecyclerView = findViewById(R.id.my_recycler_view)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.addItemDecoration(DividerItemDecoration(this,OrientationHelper.VERTICAL))
        myRecyclerView.adapter = myAdapter
    AndroidNetwork.initialize(this)

        AndroidNetworking.get("https://reqres.in/api/users")
            .build()
            .getAsObject(Mydata::class.java,object: ParseRequestListener<Mydata>{
                override  fun onResponce(response: Mydata){
                dataList.addAll(response.data)
                    myAdapter.notifyDataSetChanged()
                }
                override fun orError(anError:ANError?){

                }
            }
    }


}