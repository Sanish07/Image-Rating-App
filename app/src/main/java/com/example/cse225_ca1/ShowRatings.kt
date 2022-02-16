package com.example.cse225_ca1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShowRatings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ratings)

        var textArea = findViewById<TextView>(R.id.textAll)
        var overallRating = findViewById<RatingBar>(R.id.rateBarAv)
        var toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.appToolbar)
        var extraText = findViewById<TextView>(R.id.extraText)

        toolbar.setTitle("Your Ratings")
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener{
            val intent = Intent(this, ImageRatingApp::class.java)
            startActivity(intent)
            finish()
        }

        val bundle:Bundle? = intent.extras
        val ratings = bundle?.get("allRatings") // Getting ratings of each image
        val allRatingsAvg = bundle?.get("allRatingsAvg") // Getting all ratings average

        computationLatency()
        textArea.visibility = View.VISIBLE
        overallRating.visibility = View.VISIBLE
        extraText.visibility = View.VISIBLE

        var textToPrint = "Your Rated Images \n\n"

        var rateString = ratings.toString()
        rateString = rateString.subSequence(1,rateString.length-1).toString()

        val listRates = rateString.split(", ")

        for (key in listRates) {
            textToPrint += "$key \n"
        }

        textArea.text = "$textToPrint"

        overallRating.rating = allRatingsAvg.toString().toFloat()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_info,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if(id == R.id.it_stats){
            val vg : ViewGroup? = findViewById(R.id.toastDes)
            val inflater = layoutInflater

            val layout : View = inflater.inflate(R.layout.toast_design,vg)

            val tv = layout.findViewById<TextView>(R.id.txtToast)
            tv.text = "Already On Ratings Page"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 60)
            toast.duration = Toast.LENGTH_SHORT
            toast.setView(layout)
            toast.show()
            return true
        } else if(id == R.id.it_exit){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun computationLatency(){
        var progBar = findViewById<ProgressBar>(R.id.progLatency)
        var i = progBar.progress
        Thread{
            while(i<30){
                i = i+1
                Handler(Looper.getMainLooper()).post{
                    progBar.progress = i
                    if(i == 10){
                        progBar.visibility = View.INVISIBLE
                    }
                }
                try {
                    Thread.sleep(100)
                } catch (e:InterruptedException){
                    e.printStackTrace()
                }
            }
        }.start()

    }
}