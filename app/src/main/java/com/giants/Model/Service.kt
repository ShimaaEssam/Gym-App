package com.giants.Model

class Service {
    var name: String? = null
    var image: String? = null
    var motivation: String? = null
    var description: String? = null
    var fees: String? = null
  constructor(){}
    constructor(name: String?, image: String?, motivation: String?, description: String?, fees: String?) {
        this.name = name
        this.image = image
        this.motivation = motivation
        this.description = description
        this.fees = fees
    }

}