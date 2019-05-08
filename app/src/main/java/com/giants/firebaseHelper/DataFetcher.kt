package com.giants.firebaseHelper

import android.util.Log
import com.google.firebase.database.DataSnapshot

import java.util.ArrayList


object DataFetcher {
    fun getListFromSnapShot(obClass: Class<*>, dataSnapshot: DataSnapshot): ArrayList<Any> {
        val list = ArrayList<Any>()
        for (dataShot in dataSnapshot.children) {
            Log.e("TAG", dataShot.value.toString() + "  " + obClass.simpleName )
            list.add(dataShot.getValue(obClass)!!)
        }
        return list
    }

}
