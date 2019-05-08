package com.giants.admin

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.giants.Model.Offers
import com.giants.R
import com.giants.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_offer.*

class AddOfferActivity : AppCompatActivity() {
    private lateinit var offerDesc: EditText
    private lateinit var offerMot: EditText
    private lateinit var offerprice: EditText
    private lateinit var addBtn: Button
    private lateinit var progressBarC: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_offer)
        offerDesc = offdesc
        offerMot = offmot
        offerprice = offfees
    addBtn=addOffer
    progressBarC = progressBaraddOffer

    addBtn.setOnClickListener OnClickListener@{

        val oDesc = offerDesc.text.toString().trim()
        val oMot = offerMot.text.toString().trim()
        val oFees = offerprice.text.toString().trim()

        progressBarC.visibility = View.VISIBLE
        val checker =
            oDesc.isEmpty() || oMot.isEmpty() || oFees.isEmpty()
        if (checker) {

            progressBarC.visibility = View.GONE
            if (oDesc.isEmpty())
                offerDesc.error = " Description is Empty"
            else
                offerDesc.error = null
            if (oMot.isEmpty())
                offerMot.error = "Motivation is Empty"
            else
                offerMot.error = null
            if (oFees.isEmpty())
                offerprice.error = "Fees is Empty"
            else
                offerprice.error = null

        } else {
            val offer = Offers(oDesc,oMot,oFees)
            var size=0
            var database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val ref: DatabaseReference =database.getReference("Offers")

            progressBarC.visibility = View.GONE

            val event=object:ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    size = p0.childrenCount.toInt()
                    Log.d("exercise1: ", size.toString()) // can log all
                    val builder1 = AlertDialog.Builder(this@AddOfferActivity)
                    builder1.setMessage("Your feedback sent successfully.")
                    builder1.setCancelable(true)

                    builder1.setPositiveButton(
                        "Yes"
                    ) { dialog, id -> dialog.cancel()
                        FirebaseWriter.writeWithCustomKey(offer, OnSuccessListener<Void> {
                            Toast.makeText(this@AddOfferActivity, "Offer Added Successfully", Toast.LENGTH_LONG).show()
                            finish()
                        }, "Offers", (size + 1).toString())}


                    val alert11 = builder1.create()
                    alert11.show()

                }
            }
            database.getReference("Offers").addListenerForSingleValueEvent(event)



        }
        return@OnClickListener
    }

}
}