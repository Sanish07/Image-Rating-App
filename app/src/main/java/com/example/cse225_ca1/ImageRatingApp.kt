package com.example.cse225_ca1

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap


class ImageRatingApp : AppCompatActivity() {
    lateinit var textRate : TextView
    lateinit var preImg : ImageView
    lateinit var rateBar : RatingBar
    val arrRatings = HashMap<String,Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_rating_app)

        var toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.appToolbar)
        textRate = findViewById(R.id.textRate)
        preImg = findViewById(R.id.previewImg)
        rateBar = findViewById(R.id.imgRate)


        var img1 = findViewById<ImageView>(R.id.img1)
        var img2 = findViewById<ImageView>(R.id.img2)
        var img3 = findViewById<ImageView>(R.id.img3)
        var img4 = findViewById<ImageView>(R.id.img4)
        var img5 = findViewById<ImageView>(R.id.img5)
        var img6 = findViewById<ImageView>(R.id.img6)
        var img7 = findViewById<ImageView>(R.id.img7)



        var activeImageRating : Float ?= 0.0f

        arrRatings.put("img1",0.0f)
        arrRatings.put("img2",0.0f)
        arrRatings.put("img3",0.0f)
        arrRatings.put("img4",0.0f)
        arrRatings.put("img5",0.0f)
        arrRatings.put("img6",0.0f)
        arrRatings.put("img7",0.0f)

        var activeBar : String = ""

        toolbar.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener{
            val vg : ViewGroup? = findViewById(R.id.toastDes)
            val inflater = layoutInflater

            val layout : View = inflater.inflate(R.layout.toast_design,vg)

            val tv = layout.findViewById<TextView>(R.id.txtToast)
            tv.text = "Already On Main Page"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 60)
            toast.duration = Toast.LENGTH_SHORT
            toast.setView(layout)
            toast.show()
        }

        img1.setOnClickListener{
            activeBar = "img1"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img1,activeImageRating) //passing the image info and current image rating to function
        }

        img2.setOnClickListener{
            activeBar = "img2"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img2,activeImageRating)
        }

        img3.setOnClickListener{
            activeBar = "img3"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img3,activeImageRating)
        }

        img4.setOnClickListener{
            activeBar = "img4"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img4,activeImageRating)
        }

        img5.setOnClickListener{
            activeBar = "img5"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img5,activeImageRating)
        }

        img6.setOnClickListener{
            activeBar = "img6"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img6,activeImageRating)
        }

        img7.setOnClickListener{
            activeBar = "img7"
            activeImageRating = arrRatings.get(activeBar)
            showRate(img7,activeImageRating)
        }

        rateBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener{
                ratingBar : RatingBar?, rating : Float, fromUser : Boolean ->
             arrRatings.replace(activeBar,rating)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_info,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if(id == R.id.it_stats){
            openRatingsActivity() //Will call the function which opens another activity(Splash Screen)
            return true
        } else if(id == R.id.it_exit){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }



     fun showRate(img : ImageView, currRating : Float?) {
        textRate.visibility = View.VISIBLE
        preImg.visibility = View.VISIBLE
        rateBar.visibility = View.VISIBLE
         val curImgBitmap : Bitmap = img.drawable.toBitmap()
         preImg.setImageBitmap(curImgBitmap)
         if (currRating != null) {
             rateBar.rating = currRating
         }
    }

    fun openRatingsActivity() {

        var allRatingAvg : Float = 0.0f

        for ((key, value) in arrRatings) {
            allRatingAvg += value
        }

        allRatingAvg /= arrRatings.size

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ShowRatings::class.java)
            intent.putExtra("allRatings",arrRatings)
            intent.putExtra("allRatingsAvg",allRatingAvg)
            startActivity(intent)
            finish()
        }, 1000)
    }


}