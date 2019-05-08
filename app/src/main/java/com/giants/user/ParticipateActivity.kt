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
import kotlinx.android.synthetic.main.activity_participate.*

class ParticipateActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cvV: EditText
    private lateinit var classes: EditText
    private lateinit var payBtn: Button
    private lateinit var serName: String
    private lateinit var progressBarC: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participate)
        name = cardName
        cardNumber = cardNum
        cvV = cvv
        classes = comment
        payBtn = pay
        progressBarC = progressBarCard

        if (intent.hasExtra("NAME")) {
            serName = intent.extras!!.getString("NAME")
            payBtn.setOnClickListener OnClickListener@{

                val cName = name.text.toString().trim()
                val cNumber = cardNumber.text.toString().trim()
                val cCVV = cvV.text.toString().trim()
                val cClass = classes.text.toString().trim()

                progressBarC.visibility = View.VISIBLE
                val checker =
                    cName.isEmpty() || cNumber.isEmpty() || cCVV.isEmpty() || cCVV.length < 4 || cNumber.length != 14 || cClass.isEmpty()
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
                    if (cClass.isEmpty())
                        classes.error = "classes Number is Empty"
                    else
                        classes.error = null
                } else {

                    var uID:String= FirebaseAuth.getInstance().currentUser!!.email!!
                    val email=FirebaseAuth.getInstance().currentUser!!.email
                    val food = Participation(serName, cName, cNumber, cCVV, cClass,email)
                    var size=0
                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val ref: DatabaseReference =database.getReference("Participation")


                    val event=object: ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size= p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            FirebaseWriter.writeWithAutoKey(food, OnSuccessListener<Void> {


                            }, "Participation")

                        }


                    }
                    database.getReference("OfferParticipation").addListenerForSingleValueEvent(event)
                    startActivity(Intent(this@ParticipateActivity, HomeActivity::class.java))
                    this.finish()
                }
                return@OnClickListener
            }

        }
    }
}
