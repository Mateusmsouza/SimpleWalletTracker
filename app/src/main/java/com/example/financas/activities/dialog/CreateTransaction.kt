package com.example.financas.activities.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.financas.R
import com.example.financas.activities.delegate.ITransactionDelegate
import com.example.financas.activities.extension.convertToCalendar
import com.example.financas.activities.extension.formatToBrazilianFormat
import com.example.financas.activities.model.TransacionType
import com.example.financas.activities.model.Transaction
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class CreateTransaction(private val viewGroup: ViewGroup,
                        private val context: Context){

        private val createdView = createLayout()

    fun configureDialog(transactionType: TransacionType, transactionDelegate: ITransactionDelegate){

        createCalendarInfo()
        createCategoryField(transactionType)
        createForm(transactionDelegate, transactionType)
    }

    private fun createForm(transactionDelegate: ITransactionDelegate, transactionType: TransacionType) {
        val title = if (transactionType == TransacionType.INCOME){
            R.string.adiciona_receita
        }else {
            R.string.adiciona_despesa
        }

        AlertDialog.Builder(context)
            .setTitle(title)
            .setView(createdView)
            .setPositiveButton("Adicionar"
            ) { _, _ ->
                val valueInString = createdView.form_transacao_valor.text.toString()
                val dateInString = createdView.form_transacao_data.text.toString()
                val categoryInString = createdView.form_transacao_categoria.selectedItem.toString()

                val valor = convertValueField(valueInString)
                val date = dateInString.convertToCalendar()

                val transaction = Transaction( type = transactionType, valor = valor, data = date, category = categoryInString)

                transactionDelegate.delegate(transaction)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun createCalendarInfo(){
        val currentDate = Calendar.getInstance()

        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        createdView.form_transacao_data
            .setText(currentDate.formatToBrazilianFormat())

        createdView.form_transacao_data
            .setOnClickListener{
                DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, dayOfMonth)
                        createdView.form_transacao_data
                            .setText(selectedDate.formatToBrazilianFormat())
                    }, year, month, dayOfMonth)
                    .show()
            }
    }

    private fun convertValueField(valueInString: String) : BigDecimal {
        return try {
            BigDecimal(valueInString)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context,
                "Falha na conversão do valor",
                Toast.LENGTH_LONG)
                .show()
            BigDecimal.ZERO
        }
    }
    private fun createCategoryField(transactionType: TransacionType){
        val color = if(transactionType == TransacionType.INCOME) {
            R.array.categorias_de_receita
        }else {
            R.array.categorias_de_despesa
        }

        val adapterCreateFromResource = ArrayAdapter.createFromResource(
            context,
            color,
            android.R.layout.simple_spinner_dropdown_item
        )

        createdView.form_transacao_categoria.adapter = adapterCreateFromResource
    }

    private fun createLayout(): View{
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao,
                viewGroup,
                false)
    }
}