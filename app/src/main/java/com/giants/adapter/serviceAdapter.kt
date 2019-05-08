package com.giants.adapter


import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.giants.user.DetailActivity
import com.giants.Model.Service
import com.giants.R

public class serviceAdapter() : RecyclerView.Adapter<serviceAdapter.MyViewHolder>(), Parcelable {
    private lateinit var list:List<Service>
    private lateinit var  context: Context

    constructor(parcel: Parcel) : this() {

    }

    constructor(list: List<Service>, context: Context) : this() {
        this.list = list
        this.context = context
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(list[i])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):serviceAdapter.MyViewHolder {
        val view :View= LayoutInflater.from(viewGroup.context).inflate(R.layout.menu_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder:RecyclerView.ViewHolder{
         var menuName: TextView
        var menuMotivation: TextView
         var menuImage: ImageView

        constructor(itemView: View) : super(itemView){
            menuName=itemView.findViewById(R.id.menu_name)
            menuMotivation = itemView.findViewById(R.id.menu_motivation)
            menuImage = itemView.findViewById(R.id.menu_image)
        }
        fun bind(service: Service) = with(itemView) {
            menuName.text = service.name
            menuMotivation.text = service.motivation
            Glide.with(context)
                    .load(service.image)
                    .into(menuImage)
            this.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("NAME", service.name)
                intent.putExtra("MOT", service.motivation)
                intent.putExtra("DESC",service.description)
                intent.putExtra("IMG",service.image)
                intent.putExtra("fees",service.fees)
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

    companion object CREATOR : Parcelable.Creator<serviceAdapter> {
        override fun createFromParcel(parcel: Parcel): serviceAdapter {
            return serviceAdapter(parcel)
        }

        override fun newArray(size: Int): Array<serviceAdapter?> {
            return arrayOfNulls(size)
        }
    }


}

