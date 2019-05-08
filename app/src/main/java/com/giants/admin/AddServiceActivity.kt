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
import com.giants.Model.Service
import com.giants.R
import com.giants.firebaseHelper.FirebaseWriter
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_add_service.*
import com.google.firebase.database.*


class AddServiceActivity : AppCompatActivity() {
    private lateinit var serviceName: EditText
    private lateinit var serviceFees: EditText
    private lateinit var serviceDesc: EditText
    private lateinit var serviceMot: EditText
    private lateinit var addBtn: Button
    private lateinit var progressBarC: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_service)
        serviceName = sername
        serviceFees = serfees
        serviceDesc = serdesc
        serviceMot = sermot
        addBtn=add
        progressBarC = progressBaradd

            addBtn.setOnClickListener OnClickListener@{

                val sName = serviceName.text.toString().trim()
                val sFees = serviceFees.text.toString().trim()
                val sDesc = serviceDesc.text.toString().trim()
                val sMot = serviceMot.text.toString().trim()

                progressBarC.visibility = View.VISIBLE
                val checker =
                    sName.isEmpty() ||  sFees.isEmpty()  || sDesc.isEmpty()||sMot.isEmpty()
                if (checker) {

                    progressBarC.visibility = View.GONE
                    if (sName.isEmpty())
                        serviceName.error = " Name is Empty"
                    else
                        serviceName.error = null

                    if (sFees.isEmpty())
                        serviceFees.error = "Fees is Empty"
                    else
                        serviceFees.error = null
                    if (sDesc.isEmpty())
                        serviceDesc.error = "Description is Empty"
                    else
                        serviceDesc.error = null
                    if (sMot.isEmpty())
                        serviceMot.error = "Motivation is Empty"
                    else
                        serviceMot.error = null
                } else {
                    val service = Service(sName,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStYyGUWkWubzRyThtZUpWBVddWWSJvfIFWptZKABZPSjtqhyL6",sMot,sDesc,sFees)

                    var size=0

                    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
                    val ref: DatabaseReference =database.getReference("Services")

                    progressBarC.visibility = View.GONE

                    val event=object:ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            size = p0.childrenCount.toInt()
                            Log.d("exercise1: ", size.toString()) // can log all
                            val builder1 = AlertDialog.Builder(this@AddServiceActivity)
                            builder1.setMessage("Your feedback sent successfully.")
                            builder1.setCancelable(true)

                            builder1.setPositiveButton(
                                "Yes"
                            ) { dialog, id -> dialog.cancel()
                                FirebaseWriter.writeWithCustomKey(service, OnSuccessListener<Void> {
                                    Toast.makeText(this@AddServiceActivity, "Service Added Successfully", Toast.LENGTH_LONG).show()
                                    finish()

                                }, "Services", (size + 1).toString())}


                            val alert11 = builder1.create()
                            alert11.show()

                        }
                    }
                        database.getReference("Services").addListenerForSingleValueEvent(event)

                    Toast.makeText(this@AddServiceActivity, "Service Added Successfully", Toast.LENGTH_LONG)

                }
                return@OnClickListener
            }

        }
    }

