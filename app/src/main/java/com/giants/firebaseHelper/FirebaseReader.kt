package com.giants.firebaseHelper

import android.webkit.ValueCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList



object FirebaseReader {
    fun getSelectedFireDataList(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>> , whereClause : String , compareVal : String , vararg params: String) {
        FireHelper.getRequiredPath(*params)
            .orderByChild(whereClause)
            .equalTo(compareVal)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {

                        callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
    }
    fun getFireDataList(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>, vararg params: String) {
        FireHelper.getRequiredPath(*params)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {

                            callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
    }
    fun getFiliterdServices(obClass: Class<*>, callback: ValueCallback<ArrayList<Any>>,id:String){

        FireHelper.getRequiredPath("Services").orderByChild("email").equalTo(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        callback.onReceiveValue(DataFetcher.getListFromSnapShot(obClass, dataSnapshot))
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
    }
    fun getSnapShot(callback: ValueCallback<DataSnapshot>, vararg params: String) {
        FireHelper.getRequiredPath(*params).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onReceiveValue(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }


    fun getSnapShotSinglieListner(callback: ValueCallback<DataSnapshot>, vararg params: String) {

        FireHelper.getRequiredPath(*params).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback.onReceiveValue(dataSnapshot)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }

    fun getFireDataSingleObject(obClass: Class<*>, callback: ValueCallback<Any>, vararg params: String) {
        FireHelper.getRequiredPath(*params)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            callback.onReceiveValue(dataSnapshot.getValue(obClass))
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })

    }


}
