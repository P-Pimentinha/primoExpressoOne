package com.example.primoexpresso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import android.content.Intent
import android.net.Uri

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primoexpresso.Model.DrinkModel

class drinksOrder : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.button)


        var recyclerVIew = view.findViewById<RecyclerView>(R.id.recyclerVIew)
        recyclerVIew.layoutManager = LinearLayoutManager(activity)

        //
        var drinksList : ArrayList<DrinkModel> = ArrayList()
        dataGenerator(drinksList)

        //adapter
        val adapter = Adapter(drinksList)
        recyclerVIew.adapter = adapter

        btn.setOnClickListener(){
            composeEmail(emailBodyGenerator(drinksList))
        }

    }


    private fun dataGenerator(drinkList: ArrayList<DrinkModel>) {

        val d1 : DrinkModel = DrinkModel("Wasser", 0)
        val d2 : DrinkModel = DrinkModel("Wasser-Classic", 0)
        val d3 : DrinkModel = DrinkModel("Wasser-Sanft", 0)
        val d4 : DrinkModel = DrinkModel("Coca-Cola", 0)
        val d5 : DrinkModel = DrinkModel("Cola-Zero", 0)
        val d6 : DrinkModel = DrinkModel("Cola-Light", 0)
        val d7 : DrinkModel = DrinkModel("Apfelschorle", 0)
        val d8 : DrinkModel = DrinkModel("Johannisbeer", 0)
        val d9 : DrinkModel = DrinkModel("Mangoschorle", 0)
        val d10 : DrinkModel = DrinkModel("MultiVItamin", 0)
        val d11 : DrinkModel = DrinkModel("MitArbeiter \nwasser Naturel", 0);
        val d12 : DrinkModel = DrinkModel("MitArbeiter \nwasser Classic", 0);

        drinkList.add(d1)
        drinkList.add(d2)
        drinkList.add(d3)
        drinkList.add(d4)
        drinkList.add(d5)
        drinkList.add(d6)
        drinkList.add(d7)
        drinkList.add(d8)
        drinkList.add(d9)
        drinkList.add(d10)
        drinkList.add(d11)
        drinkList.add(d12)

    }

    fun composeEmail(emailBody: String) {

        //Array String to store all email recipient
        val recipientList = arrayOf("Fabrizio.Musco@primo.cafe")
        //stores the subject of the email
        val subject = "Getränkebestellung"

        // Implicit intent Action that composes the email
        var i = Intent(Intent.ACTION_SENDTO)
        i.setData(Uri.parse("mailto:"))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_EMAIL, recipientList)
        i.putExtra(Intent.EXTRA_TEXT, emailBody)

        startActivity(i)
    }

    fun emailBodyGenerator(drinkList: ArrayList<DrinkModel>) : String{
        // Email Body:

        //Salutation
        var body = "Hallo Zusammen \n Kundennummer: 16222 \n wir möchten bestellen: \n" +
                " "
        //Body
        for (i in drinkList) {
            if (i.getDrinkQuantity().toInt() > 0) {
                body += "\n ${i.getDrinkName()} : ${i.getDrinkQuantity()} "
            }
        }

        // Closing
        body += "\n \n VG \n Primo Expresso"

        return body
    }


}