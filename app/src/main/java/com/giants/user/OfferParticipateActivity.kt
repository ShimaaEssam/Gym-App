package com.giants.user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.giants.Model.Participation
import com.giants.R
import com.giants.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_offer_participate.*

class OfferParticipateActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cvV: EditText
    private lateinit var payBtn: Button
    private lateinit var progressBarC: ProgressBar
    private lateinit var serName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_participate)
            name = cardNameoff
            cardNumber = cardNumoff
            cvV = cvvoff
            payBtn = payoff
            progressBarC = progressBarCardoff

        if (intent.hasExtra("NAME")) {
            serName = intent.extras!!.getString("NAME")
            payBtn.setOnClickListener OnClickListener@{

                val cName = name.text.toString().trim()
                val cNumber = cardNumber.text.toString().trim()
                val cCVV = cvV.text.toString().trim()

                progressBarC.visibility = View.VISIBLE
                val checker =
                    cName.isEmpty() || cNumber.isEmpty() || cCVV.isEmpty() || cCVV.length < 4 || cNumber.length != 14
                if (checker) {

                    progressBarC.visibility = View.GONE
                    if (cName.isEmpty())
                        name.error = "CardHolder's Name is Empty"
                    else
                        name.error = null
                    if (cNumber.isEmpty())
                        cardNumber.error = "Card Number is Empty"
                    else
                        cardNumber.error = null
                    if (cNumber.length != 14)
                        cardNumber.error = "Card Number is not valid"
                    else
                        cardNumber.error = null
                    if (cCVV.isEmpty())
                        cvV.error = "CVV is Empty"
                    else
                        cvV.error = null
                    if (cCVV.length < 4)
                        cvV.error = "CVV is not valid"
                    else
                        cvV.error = null
                } else {
//                    val uID: String = FirebaseAuth.getInstance().currentUser!!.uid
//                    val participation = Participation(serName, cName, cNumber, cCVV, "No Classess")
//                    FirebaseWriter.writeWithAutoKey(participation, OnSuccessListener<Void> {
//                        name.text = null
//                        cardNumber.text = null
//                        cvV.text = null
//
//                    }, "OfferParticipation", uID)
//                    Toast.makeText(this@OfferParticipateActivity, "Payment Successfully", Toast.LENGTH_LONG)


                     var uID:String= FirebaseAuth.getInstance().currentUser!!.email!!
                    val email=FirebaseAuth.getInstance().currentUser!!.email
                   // val participation = Participation(serName, cName, cNumber, cCVV, "No Classess")
                    val food = Participation(serName, cName, cNumber, cCVV, "No Classess",email)
                    var size=0
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val ref: DatabaseReference =database.getReference("OfferParticipation")


                    val event=object: ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size= p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(food, OnSuccessListener<Void> {


                            }, "OfferParticipation")

                        }


                    }
                    database.getReference("OfferParticipation").addListenerForSingleValueEvent(event)
                    startActivity(Intent(this@OfferParticipateActivity, HomeActivity::class.java))
                    this.finish()

                }
                return@OnClickListener
            }

        }
    }
}
