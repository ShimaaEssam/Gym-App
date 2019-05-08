package com.giants.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.giants.Model.Offers
import com.giants.user.OfferDetailActivity
import com.giants.R

class offerAdapter() : RecyclerView.Adapter<offerAdapter.MyViewHolder>(), Parcelable {
    override fun onBindViewHolder(myViewHolder: MyViewHolder,i: Int) {
        myViewHolder.bind(list[i])
    }


    private lateinit var list:List<Offers>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: List<Offers>, context: Context) : this() {
        this.list = list
        this.context = context
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.offer_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder: RecyclerView.ViewHolder{
       // var offerDesc: TextView
        var offerMot: TextView

        constructor(itemView: View) : super(itemView){
         //   offerDesc=itemView.findViewById(R.id.menu_name)
            offerMot = itemView.findViewById(R.id.offer_motiv)
        }
        fun bind(offer: Offers) = with(itemView) {
            offerMot.text =offer.motivation
            this.setOnClickListener {
                val intent = Intent(context, OfferDetailActivity::class.java)
                intent.putExtra("MOT", offer.motivation)
                intent.putExtra("DESC",offer.description)
                intent.putExtra("PRICE",offer.price)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<offerAdapter> {
        override fun createFromParcel(parcel: Parcel): offerAdapter {
            return offerAdapter(parcel)
        }

        override fun newArray(size: Int): Array<offerAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

