package austinwhite.tech.akotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate: Button = findViewById(R.id.calculate)
        val result: TextView = findViewById(R.id.result) //tip total
        val totalPrice: TextView = findViewById(R.id.totalPrice) //price with tip
        val userInput: EditText = findViewById(R.id.userInput) //original price
        val customInput: EditText = findViewById(R.id.customPercentage) //input from custom percentage
        val customPercent: RadioButton = findViewById(R.id.customRadioButton) //toggles custom percentage on/off
        val tenPercent: RadioButton = findViewById(R.id.tenPercent) //10%
        val fifteenPercent: RadioButton = findViewById(R.id.fifteenPercent)//15%
        val twentyPercent: RadioButton = findViewById(R.id.twentyPercent)//20%

        calculate.setOnClickListener {
            when {
                tenPercent.isChecked -> {
                    val checkPriceInput = userInput.text.toString() //checking value to get tip from, will need to be checked every time

                    if (checkPriceInput.isBlank()) {
                        blankError(result, totalPrice)

                    } else {
                        val percentage = 0.10
                        calculateTotal(percentage, result, totalPrice, checkPriceInput)
                    }
                }
                fifteenPercent.isChecked -> {
                    val checkPriceInput = userInput.text.toString()

                    if (checkPriceInput.isBlank()) {
                        blankError(result, totalPrice)
                    } else {
                        val percentage = 0.15
                        calculateTotal(percentage, result, totalPrice, checkPriceInput)
                    }
                }
                twentyPercent.isChecked -> {
                    val checkPriceInput = userInput.text.toString()

                    if (checkPriceInput.isBlank()) {
                        blankError(result, totalPrice)

                    } else {
                        val percentage = 0.20
                        calculateTotal(percentage, result, totalPrice, checkPriceInput)

                    }
                }
                customPercent.isChecked -> {
                    val checkPriceInput = userInput.text.toString() //checking value to get tip from
                    val checkCustomPercentInput = customInput.text.toString() //checking custom percentage

                    if (checkPriceInput.isBlank()) {
                        blankError(result, totalPrice)

                    } else if (checkCustomPercentInput.isBlank()){
                        blankError(result, totalPrice)

                    } else if (checkPriceInput.isBlank() && checkCustomPercentInput.isBlank()){
                        blankError(result, totalPrice)

                    } else {
                        val usercustom = checkCustomPercentInput.toDouble() //custom percentage
                        val percentage = usercustom * 0.01

                        calculateTotal(percentage, result, totalPrice, checkPriceInput)

                    }
                }
                else -> {
                    blankError(result, totalPrice)
                }
            }
        }
    }

    private fun blankError(result: TextView, totalPrice: TextView) {
        result.text = getString(R.string.error)
        totalPrice.text = " "
    }

    @SuppressLint("SetTextI18n")
    private fun calculateTotal(percentage: Double, result: TextView, totalPrice: TextView, checkPriceInput: String) {
        val df = DecimalFormat("###0.00")
        df.roundingMode = RoundingMode.FLOOR

        val input = checkPriceInput.toDouble()
        val tip = input * percentage

        val total = tip + input

        result.text = getString(R.string.youTip) + df.format(tip)
        totalPrice.text = getString(R.string.yourPrice) + df.format(total)

    }
}