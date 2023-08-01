package com.example.primoexpresso

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class wocheUmsatz : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_woche_umsatz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Access the view using the resource ID
        val btnSend: Button = view.findViewById(R.id.btnSend)
        val etMonday = view.findViewById<EditText>(R.id.etMonday)
        val etTuesday = view.findViewById<EditText>(R.id.etTuesday)
        val etWednesday = view.findViewById<EditText>(R.id.etWednesday)
        val etThursday = view.findViewById<EditText>(R.id.etThursday)
        val etFriday = view.findViewById<EditText>(R.id.etFriday)

        //
        btnSend.setOnClickListener() {
            composeEmail(etMonday, etTuesday, etWednesday, etThursday, etFriday)
        }

    }


    /*
        composeEmail takes the user input and creates an email
        to: Fabrizio.Musco@primo.cafe
        subject: Woche Umsatz"
        content: t
        params: m (monday) t(tuesday) w(wednesday) th(thursday) f(friday)
    */
    fun composeEmail(m: EditText, t: EditText, w: EditText, th: EditText, f: EditText) {

        // takes the user input and converts it from double to string
        var monday = m.text.toString()
        var tuesday = t.text.toString()
        var wednesday = w.text.toString()
        var thursday = th.text.toString()
        var friday = f.text.toString()

        //Array String to store all email recipient
        val recipientList = arrayOf("Fabrizio.Musco@primo.cafe")
        //stores the subject of the email
        val subject = "Woche Umsatz"
        // stores the body of the email
        var emailBOdy = """
            Montag: $monday
            Dienstag: $tuesday
            Mitwoch: $wednesday
            Donnerstag: $thursday
            Freitag: $friday
            """.trimIndent()

        // Implicit intent Action that composes the email
        var i = Intent(Intent.ACTION_SENDTO)
        i.setData(Uri.parse("mailto:"))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_EMAIL, recipientList)
        i.putExtra(Intent.EXTRA_TEXT, emailBOdy)

        startActivity(i)
    }

    }

