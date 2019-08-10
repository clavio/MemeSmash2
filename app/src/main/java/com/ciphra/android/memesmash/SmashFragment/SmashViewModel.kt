package com.ciphra.android.memesmash.SmashFragment

import androidx.lifecycle.ViewModel
import com.ciphra.android.memesmash.Meme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.StorageReference

class SmashViewModel : ViewModel() {
    lateinit var mAuth : FirebaseAuth

    var memeCount = 0
    var memeASettable = true
    var memeBSettable = true
    var mStorageRef: StorageReference? = null
    var user: FirebaseUser? = null

    var memeA: Meme? = null
    var memeB: Meme? = null


}
