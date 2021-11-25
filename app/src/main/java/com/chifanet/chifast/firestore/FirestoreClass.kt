package com.chifanet.chifast.firestore

import android.app.Activity
import android.util.Log
import com.chifanet.chifast.activities.AuthActivity
import com.chifanet.chifast.models.User
import com.chifanet.chifast.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: AuthActivity, userInfo: User) {
        mFireStore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSucess()
            }

            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error registrando al usuario.",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser !=null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

}