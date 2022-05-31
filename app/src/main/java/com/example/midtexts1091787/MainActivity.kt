package com.example.midtexts1091787
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.midtexts1091787.databinding.ActivityMainBinding

@GlideModule
public final class MyAppGlideModule : AppGlideModule()


class MainActivity : AppCompatActivity(),
    GestureDetector.OnGestureListener,View.OnTouchListener {
    lateinit var mper: MediaPlayer
    lateinit var gDetector: GestureDetector

    lateinit var binding: ActivityMainBinding

    var check:Boolean = false
    var control:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var job: Job
        super.onCreate(savedInstanceState)
        gDetector = GestureDetector(this, this)
        mper = MediaPlayer()

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        img3.setOnTouchListener(this)
        binding.img.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                check = !check


                if(check==false){
                    img.setImageResource(R.drawable.start)

                }else{
                    img.setImageResource(R.drawable.stop)

                }


            }

        }
        )


        job = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                if(check) {
                    val canvas: Canvas = binding.mysv.holder.lockCanvas()
                    binding.mysv.drawSomething(canvas)
                    binding.mysv.holder.unlockCanvasAndPost(canvas)
                    img3.setImageResource(R.drawable.fly2)
                }
                delay(25)
                img3.setImageResource(R.drawable.fly1)
                delay(18)
            }

        }

        val img: ImageView = findViewById(R.id.image5)
        GlideApp.with(this)
            .load(R.drawable.pic)
            .circleCrop()
            .override(800, 600)
            .into(image5)

        //設定螢幕水平顯示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)


    }
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_MOVE && check) {
            v?.y = event.rawY - v!!.height / 2
        }
        gDetector.onTouchEvent(event)
        return true
    }
    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        if(check) {
            control = true
            mper.reset()
            mper = MediaPlayer.create(this, R.raw.shoot)
            mper.start()
            GlobalScope.launch(Dispatchers.Main) {
                img3.setImageResource(R.drawable.shoot1)
                delay(25)
                img3.setImageResource(R.drawable.shoot2)
                delay(25)
                img3.setImageResource(R.drawable.shoot3)
                delay(25)
                img3.setImageResource(R.drawable.shoot4)
                delay(25)
                img3.setImageResource(R.drawable.shoot5)
                delay(25)
            }
            control=false
        }
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return true
    }


}