package app.andika.dindinassignment.view.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.andika.dindinassignment.R
import app.andika.dindinassignment.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_bought_item.*


class BoughtItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bought_item)
        setupToolbar()

        getWindow().setStatusBarColor(this.getColor(R.color.white))

        val boughtItemFragment = BoughtItemFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, boughtItemFragment)
                .commit()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.visibility = View.VISIBLE
        supportActionBar?.title = "Menu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_back)
        toolbar.overflowIcon = drawable
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }
}