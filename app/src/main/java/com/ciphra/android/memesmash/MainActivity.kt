package com.ciphra.android.memesmash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ciphra.android.memesmash.SmashFragment.SmashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToSmashFragment()
    }

    fun goToSmashFragment(){
            val fragmentA = supportFragmentManager.findFragmentByTag("smashFragment")
            if (fragmentA == null) {
                Log.i("wxyz", "wxyz")
                val fragment = SmashFragment.newInstance()
                fragment.retainInstance = true
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, "smashFragment")
                    .commitAllowingStateLoss()
                Log.i("wxyz", "wxyz")
        }
    }
}
