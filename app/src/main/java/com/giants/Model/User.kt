package com.giants.Model

class User {
    var firstName: String? = null
    var LastName: String? = null
    var phone: String? = null
    var email: String? = null
    var address: String? = null
    var userName: String? = null

    constructor( firstName:String,LastName:String,phone: String,email: String,address:String,userName:String ) {
        this.firstName=firstName
        this.LastName=LastName
        this.email = email
        this.phone = phone
        this.address=address
        this.userName=userName
    }
}