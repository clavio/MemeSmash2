package com.ciphra.android.memesmash.SmashFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ciphra.android.memesmash.Meme
import com.ciphra.android.memesmash.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.smash_fragment.*
import java.util.*

class SmashFragment : Fragment() {


    private lateinit var viewModel: SmashViewModel

    fun updateCount() {
        val database = FirebaseDatabase.getInstance()
        val fatherMeme = database.getReference("memes")
        fatherMeme.addListenerForSingleValueEvent(getMemeCount)
    }

    var getMemeCount: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            viewModel.memeCount = dataSnapshot.childrenCount.toInt()
            setTwoMemes()
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    fun setTwoMemes() {
        viewModel.memeASettable = true
        viewModel.memeBSettable = true
        val database = FirebaseDatabase.getInstance()
        val random = Random()
        var indexOne = 0
        var indexTwo = 0

        while (indexOne == indexTwo) {
            indexOne = random.nextInt(viewModel.memeCount)
            indexTwo = random.nextInt(viewModel.memeCount)
        }
        val fatherMeme = database.getReference("memes")
        fatherMeme.orderByKey().limitToLast(indexOne + 1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                if (viewModel.memeASettable) {
                    val firstMemeRef = dataSnapshot.ref
                    firstMemeRef.addListenerForSingleValueEvent(memeListenerA)
                    viewModel.memeASettable = false
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        val motherMeme = database.getReference("memes")
        motherMeme.orderByKey().limitToLast(indexTwo + 1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                if (viewModel.memeBSettable) {
                    val firstMemeRef = dataSnapshot.ref
                    firstMemeRef.addListenerForSingleValueEvent(memeListenerB)
                    viewModel.memeBSettable = false
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }

    companion object {
        fun newInstance() = SmashFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.smash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SmashViewModel::class.java)
        viewModel.mAuth = FirebaseAuth.getInstance()
        updateCount()

    }


    internal var memeListenerA: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if(dataSnapshot.getValue(Meme::class.java!!) != null) loadMemeFromDataSnapShot(dataSnapshot.getValue(Meme::class.java!!)!!, dataSnapshot, memebutton_a)
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    internal var memeListenerB: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if(dataSnapshot.getValue(Meme::class.java!!) != null) loadMemeFromDataSnapShot(dataSnapshot.getValue(Meme::class.java!!)!!, dataSnapshot, memebutton_b)
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    fun loadMemeFromDataSnapShot(meme : Meme, dataSnapshot : DataSnapshot, memeButton : ImageView){
        if(dataSnapshot.key != null)meme?.id = dataSnapshot.key!!
        Glide
            .with(context!!)
            .load(meme!!.pictureId)
            .into(memeButton)
    }

}
