package com.artistecomm.runtime_permission

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private var MY_PERMISSION_READ_CONTACTS=1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check if permission is already granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            //If NOt
            //Tell user why it is important
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS))
            {
               var alert: AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("Request Permission")
                alert.setMessage("You should enable this perimissin for")
                alert.setNegativeButton("No", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.READ_CONTACTS), MY_PERMISSION_READ_CONTACTS)
                    }})
                alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.READ_CONTACTS), MY_PERMISSION_READ_CONTACTS)
                    }})
                alert.show()
            }
            else
                ActivityCompat.requestPermissions(this as Activity, arrayOf(Manifest.permission.READ_CONTACTS), MY_PERMISSION_READ_CONTACTS)
        }
        else
        {
            //Permission is already granted
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSION_READ_CONTACTS->
                if(grantResults.size > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"Permission is given", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this,"Permission is not granted", Toast.LENGTH_SHORT).show()
        }
    }
}