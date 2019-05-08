package com.giants.Model

class Participation:BaseOrder {
    var serviceName:String?=null
     var cardholderName:String?=null
     var cardNumber:String?=null
     var cvv:String?=null
     var numClassess:String?=null
constructor(){}
    constructor( serviceName:String,cardholderName: String, cardNumber: String, cvv: String, numClassess: String,userID:String?) {
        this.cardholderName = cardholderName
        this.cardNumber = cardNumber
        this.cvv = cvv
        this.numClassess = numClassess
        this.serviceName=serviceName
        this.email=userID;

    }
}