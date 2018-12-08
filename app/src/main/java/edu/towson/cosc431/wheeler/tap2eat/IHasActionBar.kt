package edu.towson.cosc431.wheeler.tap2eat

import android.app.Activity
import android.view.Menu
import android.view.MenuItem

interface IHasActionBar{
    fun onCreateOptionsMenu(menu: Menu): Boolean
    fun onOptionsItemSelected(item: MenuItem): Boolean
    fun launchProfile()
    fun launchHome()
    fun launchCart()
}