package com.giants.Model

class Adminhistory {
    //  var id:String?=null
    lateinit var map:HashMap<String,Pair<User,ArrayList<Participation>>>
    var user:User ?=null
    var   orders:ArrayList<Participation> = ArrayList<Participation>()
    fun addNewOrder(newOrder:Participation){
        orders.add(newOrder)
    }
    fun getAdminOrders():ArrayList<Participation>{
        return this.orders
    }
}