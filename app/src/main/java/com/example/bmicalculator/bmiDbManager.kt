package com.example.bmicalculator

import android.R
import com.google.firebase.database.*


class bmiDbManager() {

    private var database = FirebaseDatabase.getInstance().getReference("BMIs")

    //only insert new if record of user ID doesn't exist before, otherwise update
     fun insert(bmi: BMI): Boolean {
        val id = bmi.getID().toString()

        var success: Boolean = false

        if(database.child(id).setValue(bmi).isSuccessful)
        {
            success = true
        }

        return success
    }

    private fun checkExisting(id: String): Boolean {
        val query: Query = database
            .child("BMIs")
            .orderByChild("id")
            .equalTo(id)

        var found = false

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.childrenCount > 0) {
                    found = true
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return found
    }
}