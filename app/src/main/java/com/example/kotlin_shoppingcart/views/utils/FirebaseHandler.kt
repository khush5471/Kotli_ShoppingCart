package com.example.kotlin_shoppingcart.views.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseHandler {


    fun getFireBaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    fun getFireBaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    fun getFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }


    fun getFirebaseStorageReference(): StorageReference {
        return getFirebaseStorage().getReferenceFromUrl(Constants.FIREBASE_BUCKET)
    }

    fun getFirebaseDatabaseReference(reference: String): DatabaseReference {
        return getFireBaseDatabase().getReference(reference)
    }
}