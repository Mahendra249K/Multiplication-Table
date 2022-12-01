package com.mahendra249k.multiplicationtable.activity

import android.content.Context
import android.os.*
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mahendra249k.multiplicationtable.R
import com.mahendra249k.multiplicationtable.databinding.ActivityHomeBinding
import com.mahendra249k.multiplicationtable.fragment.FragOptionBottomSheet
import java.math.BigDecimal


class HomeActivity : AppCompatActivity() {
    private var exit = false
    var binding: ActivityHomeBinding? = null
    var index: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        doMultiply(index)
        onClicksListners()
    }

    private fun onClicksListners() {

        binding?.imagePrevious?.setOnClickListener {
            if (index > 1)
                doMultiply(((index - 1).also { index = it }))
        }
        binding?.imageNext?.setOnClickListener {
            doMultiply(((index + 1).also { index = it }))
        }
        binding?.imageExit?.setOnClickListener {
            onBackPressed()
        }
        binding?.imageSaerch?.setOnClickListener {
            showBottom()
        }
    }

    fun doMultiply(number: Double) {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }

        binding?.linMultiplication?.removeAllViews()
        for (i in 1 until 11) {

            val inflate = View.inflate(this, R.layout.table_layout, null)

            val textMultiplication1 = inflate.findViewById<TextView>(R.id.textMultiplication1)
            val textMultiplication2 = inflate.findViewById<TextView>(R.id.textMultiplication2)
            val textMultiplication3 = inflate.findViewById<TextView>(R.id.textMultiplication3)

            textMultiplication1.text = "" + number.toInt()
            textMultiplication2.text = "" + i
            textMultiplication3.text = "" + BigDecimal(number).multiply(BigDecimal(i))

            binding?.linMultiplication?.addView(inflate)
        }
        binding?.textTitle?.text = "Multiplication table of ${number.toInt()}"
    }


    fun showBottom() {
        val bottomSheetDialogFragment = FragOptionBottomSheet()
        bottomSheetDialogFragment.show((this).supportFragmentManager, bottomSheetDialogFragment.tag)
        bottomSheetDialogFragment.setMultiplicationClickListener(object :
            FragOptionBottomSheet.OnMultiplicationClickListener {
            override fun OnMultiplicationClick(mIndex: Double) {
                index = mIndex
                doMultiply(index)
            }

        })
    }

    override fun onBackPressed() {
        if (exit) {
            finish() // finish activity
        } else {
            Toast.makeText(
                this, "Press Back again to Exit.",
                Toast.LENGTH_SHORT
            ).show()
            exit = true
            Handler(Looper.getMainLooper()).postDelayed({
                exit = false
            }, 2000)
        }
    }
}