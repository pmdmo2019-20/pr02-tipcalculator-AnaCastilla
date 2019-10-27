package es.iessaladillo.pedrojoya.tipcalculator.model

import kotlin.math.round

// TipCalculator class. Its constructor receives bill, percentage and diners

class TipCalculator(val bill:Float, val percentage:Float, val diners:Int) {

    fun calculateTip(): Float {
        return bill*(percentage/100)
    }

    fun calculateTotal(): Float {
        return bill+calculateTip()
    }

    fun calculatePerDiner(): Float {
        return calculateTotal()/diners
    }

    fun calculatePerDinerRounded(): Float {
        return round(calculatePerDiner())
    }

}