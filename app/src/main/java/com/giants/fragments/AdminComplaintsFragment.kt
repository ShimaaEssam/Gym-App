package com.giants.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import com.giants.R
import com.giants.adapter.complaintsAdapter
import com.giants.firebaseHelper.FirebaseReader
import com.google.firebase.database.FirebaseDatabase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AdminComplaintsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AdminComplaintsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AdminComplaintsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var database: FirebaseDatabase
    //lateinit var service: DatabaseReference
    lateinit var  recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_admin_complaints, container, false)
        //database= FirebaseDatabase.getInstance()
        //service=database.getReference("Services")
        //load menu
        recyclerView=rootView.findViewById(R.id.complaintrecycler)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        loadMenu()


        return rootView
    }

    private fun loadMenu() {

        FirebaseReader.getFireDataList(Any::class.java, ValueCallback { objects ->
            var list = objects as ArrayList<HashMap<String,String>> // ArrayList of HashMap response
            var adapterList = ArrayList<String>()
            for(hashObj : HashMap<String,String> in list) {

                for ((key, value) in hashObj) { // iterating over hashMap to get the values of complains
                    adapterList.add(value)
                }
            }

            val adapter= complaintsAdapter(adapterList,context!!)
            recyclerView.adapter=adapter


            Log.e("Response", list.get(0).toString())





        }, "Complaints")
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminComplaintsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AdminComplaintsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
