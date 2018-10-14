package edu.towson.cosc431.wheeler.tap2eat

import android.content.ComponentName
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class activity_home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        populateView()

        order_button.setOnClickListener{ launchMenu() }
    }

    private fun launchMenu() {
        intent = Intent()
        intent.component = ComponentName(this, activity_menu::class.java)
        this.startActivity(intent)
    }

    private fun populateView() {
        val restntTags = ArrayList<String>()
        restntTags.add("Italian")
        restntTags.add("Pasta")
        restntTags.add("Spaghetti")
        restntTags.add("Fettuccine")
        restntTags.add("Linguine")
        val restnt = Restaurant("Italian Spaghetti", "123 Great Drive, Towson, MD",
                "The best Italian restaurant in town. Offers the best spaghetti withh meatballs.", restntTags)

        name.text = restnt.name
        address.text = restnt.address
        description.text = restnt.description
        var tags:String = ""
        val rIter = restntTags.iterator()
        while(rIter.hasNext()) {
            tags += rIter.next()+ ", "
        }
        type_tags.text = tags

    }
}
