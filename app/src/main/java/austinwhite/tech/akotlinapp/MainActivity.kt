package austinwhite.tech.akotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate: Button = findViewById(R.id.calculate)
        val result: TextView = findViewById(R.id.result) //tip total
        val totalprice: TextView = findViewById(R.id.totalprice) //price with tip
        val userinput: EditText = findViewById(R.id.userinput) //original price
        val custominput: EditText = findViewById(R.id.custompercentage) //input from custom percentage
        val custompercent: RadioButton = findViewById(R.id.customcheckbox) //toggles custom percentage on/off
        val tenpercent: RadioButton = findViewById(R.id.tenpercent) //10%
        val fifteenpercent: RadioButton = findViewById(R.id.fifteenpercent)//15%
        val twentypercent: RadioButton = findViewById(R.id.twentypercent)//20%

        //val df = DecimalFormat("###0.00")
        //df.roundingMode = RoundingMode.FLOOR

        calculate.setOnClickListener {
            when {
                tenpercent.isChecked -> {
                    val checkPriceInput = userinput.text.toString() //checking value to get tip from

                    if (checkPriceInput.isBlank()) {
                        result.text = getString(R.string.error)
                        totalprice.text = " "
                    } else {
                        val percentage = 0.10
                        calculatetotal(percentage)
                    }
                }
                fifteenpercent.isChecked -> {
                    val checkPriceInput = userinput.text.toString() //checking value to get tip from

                    if (checkPriceInput.isBlank()) {
                        result.text = getString(R.string.error)
                        totalprice.text = " "
                    } else {
                        val percentage = 0.15
                        calculatetotal(percentage)
                    }
                }
                twentypercent.isChecked -> {
                    val checkPriceInput = userinput.text.toString() //checking value to get tip from

                    if (checkPriceInput.isBlank()) {
                        result.text = getString(R.string.error)
                        totalprice.text = " "
                    } else {
                        val percentage = 0.20
                        calculatetotal(percentage)

                    }
                }
                custompercent.isChecked -> {
                    val checkPriceInput = userinput.text.toString() //checking value to get tip from
                    val checkCustomPercentInput = custominput.text.toString() //checking custom percentage

                    if (checkPriceInput.isBlank()) {
                        result.text = getString(R.string.error)
                        totalprice.text = " "

                    } else if (checkCustomPercentInput.isBlank()){
                        result.text = getString(R.string.error)
                        totalprice.text = " "

                    } else if (checkPriceInput.isBlank() && checkCustomPercentInput.isBlank()){
                        result.text = getString(R.string.error)
                        totalprice.text = " "

                    } else {
                        val usercustom = custominput.text.toString().toDouble() //custom percentage
                        val userpercent = usercustom * 0.01
                        val percentage = userpercent

                        calculatetotal(percentage)

                    }
                }
                else -> {
                    result.text = getString(R.string.error)
                    totalprice.text = " "
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatetotal(percentage: Double) {
        val input = userinput.text.toString().toDouble()
        val tip = input * percentage

        val df = DecimalFormat("###0.00")
        df.roundingMode = RoundingMode.FLOOR

        val total = tip + input

        result.text = getString(R.string.youtip) + df.format(tip)
        totalprice.text = getString(R.string.yourprice) + df.format(total)

    }
}