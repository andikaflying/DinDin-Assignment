package app.andika.dindinassignment.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import app.andika.dindinassignment.R
import app.andika.dindinassignment.utilities.SharedPreferencesManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        SharedPreferencesManager.removeAllData(this)
        val mainFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, mainFragment)
            .commit()
    }
}