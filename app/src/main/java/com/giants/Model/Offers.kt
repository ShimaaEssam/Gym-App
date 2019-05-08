package com.giants.Model

class Offers {
    var description: String? = null
    var motivation: String? = null
    var price: String? = null
    constructor(){}
    constructor(description: String?, motivation: String?, price: String?) {
        this.description = description
        this.motivation = motivation
        this.price = price
    }

}