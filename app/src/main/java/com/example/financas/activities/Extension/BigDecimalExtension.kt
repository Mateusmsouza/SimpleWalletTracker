package com.example.financas.activities.Extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatBrazilianCurrency() : String {
    return DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
        .format(this)
        .replace("R$", "R$ ")
}