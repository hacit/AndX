package com.handroid.andx

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.handroid.model.dataVO
import kotlinx.android.synthetic.*
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private final val TAG:String = "MainActivity"

    val client = OkHttpClient()
    lateinit var tv:TextView
    lateinit var data:dataVO

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iv:ImageView = findViewById(R.id.imageView)
        tv = findViewById(R.id.textView)

        permissionChecker()
        Glide.with(this).load("https://goo.gl/gEgYUd").into(iv)

        run("https://api.github.com/repos/[owner]/[repo]/contributors")



    }

    fun run(url:String){
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.d(TAG, e.stackTrace.toString())
            }
            override fun onResponse(call: Call, response: Response) {
//                Log.d(TAG, "response.body : "+response.body?.string())

//                var temp:JSONArray = response.body

                var result = response.body?.string()
                Log.d(TAG, "response.body : "+result)
//                var jsonResult = JSONObject(result)
                var gson = GsonBuilder().create()
                data = gson.fromJson(result, dataVO::class.java)
//
                Log.d(TAG, "id : "+data.id)
                Log.d(TAG, "id : "+data.message)
                Log.d(TAG, "id : "+data.documentation_url)
                Log.d(TAG, "id : "+data.toString())
                Log.d(TAG, "id : "+data.toString())
//                parse(data)
            }
//            fun parse(data:dataVO){
//                this. = data
//            }
        })

    }

    fun permissionChecker(){

        var MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100
        var MY_PERMISSIONS_REQUEST_WRITE_CONTACTS = 200
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }
}
