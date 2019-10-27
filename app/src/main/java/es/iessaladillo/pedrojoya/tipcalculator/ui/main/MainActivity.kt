package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.TipCalculator

class MainActivity : AppCompatActivity() {

    private lateinit var txtBill: EditText
    private lateinit var txtPercentage: EditText
    private lateinit var txtTip: EditText
    private lateinit var txtTotal: EditText
    private lateinit var txtDiners: EditText
    private lateinit var txtPerDiner: EditText
    private lateinit var txtPerDinerRounded: EditText
    private lateinit var btnResetTip: Button
    private lateinit var btnResetDiners: Button
    private lateinit var tipCalculator: TipCalculator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        txtBill.requestFocus()
    }

    private fun resetTip(){
        txtBill.setText("0.00")
        txtPercentage.setText("10.00")
        txtTip.setText("0.00")
        txtTotal.setText("0.00")
        txtPerDiner.setText("0.00")
        txtPerDinerRounded.setText("0.00")

        txtBill.requestFocus()
    }

    private fun resetDiners(){
        txtDiners.setText("1")


        if (txtBill.text.toString() == "0.00") {
            txtPerDiner.setText("0.00")
            txtPerDinerRounded.setText("0.00")
        }
        txtDiners.requestFocus()
    }

    private fun setupViews() {
        txtBill = findViewById(R.id.txtBill)
        txtPercentage = findViewById(R.id.txtPercentage)
        txtTip = findViewById(R.id.txtTip)
        txtTotal = findViewById(R.id.txtTotal)
        txtDiners = findViewById(R.id.txtDiners)
        txtPerDiner = findViewById(R.id.txtPerDiner)
        txtPerDinerRounded = findViewById(R.id.txtPerDinerRounded)
        btnResetTip = findViewById(R.id.btnResetTip)
        btnResetDiners = findViewById(R.id.btnResetDiners)

        btnResetTip.setOnClickListener { resetTip() }
        btnResetDiners.setOnClickListener { resetDiners() }

        txtBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtBill.text.toString() == "") {
                    txtBill.setText("0.00")
                }
            }

            override fun afterTextChanged(s: Editable) {
                tipCalculator = TipCalculator(
                    txtBill.text.toString().toFloat(),
                    txtPercentage.text.toString().toFloat(),
                    txtDiners.text.toString().toInt()
                )
                txtTip.setText(tipCalculator.calculateTip().toString())
                txtTotal.setText(tipCalculator.calculateTotal().toString())
                txtPerDiner.setText(tipCalculator.calculatePerDiner().toString())
                txtPerDinerRounded.setText(tipCalculator.calculatePerDinerRounded().toString())
            }
        })

        txtPercentage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                tipCalculator = TipCalculator(
                    txtBill.text.toString().toFloat(),
                    txtPercentage.text.toString().toFloat(),
                    txtDiners.text.toString().toInt()
                )
                txtTip.setText(tipCalculator.calculateTip().toString())
                txtTotal.setText(tipCalculator.calculateTotal().toString())
                txtPerDiner.setText(tipCalculator.calculatePerDiner().toString())
                txtPerDinerRounded.setText(tipCalculator.calculatePerDinerRounded().toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtPercentage.text.toString() == "") {
                    txtPercentage.setText("10.00")
                }

            }

        })

        txtDiners.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                tipCalculator = TipCalculator(
                    txtBill.text.toString().toFloat(),
                    txtPercentage.text.toString().toFloat(),
                    txtDiners.text.toString().toInt()
                )

                txtPerDiner.setText(tipCalculator.calculatePerDiner().toString())
                txtPerDinerRounded.setText(tipCalculator.calculatePerDinerRounded().toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtDiners.text.toString() == "") {
                    txtDiners.setText("1")
                }
            }

        })
    }
}
