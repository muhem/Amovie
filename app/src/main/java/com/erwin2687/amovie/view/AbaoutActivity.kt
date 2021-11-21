package com.erwin2687.amovie.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import mehdi.sakout.aboutpage.AboutPage
import android.widget.Toast

import android.view.Gravity
import android.view.View
import com.erwin2687.amovie.R
import mehdi.sakout.aboutpage.Element

import java.lang.String
import java.util.*


class AbaoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adsElement = Element()
        adsElement.setTitle("Advertise with us")

        val aboutPage: View = AboutPage(this)
            .isRTL(false)
            .setImage(com.erwin2687.amovie.R.drawable.pict)
            .setDescription("Aplikasi ini merupakan project ujian tengah semester matakuliah Pemrograman Mobile")
            .addGroup("Nama : Muhammad Erwin Syahrul Alim")
            .addGroup("NIM : 19.11.2687")
            .addGroup("Connect with us")
            .addEmail("muhammad.2687@students.amikom.ac.id")
            .addWebsite("https://erwinsyahrul.me/")
            .addYoutube("#")
            .addGitHub("#")
            .addItem(getCopyRightsElement())
            .create()

        setContentView(aboutPage)
    }
    @SuppressLint("StringFormatInvalid")
    fun getCopyRightsElement(): Element? {
        val copyRightsElement = Element()
        val copyrights =
            String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR))
        copyRightsElement.setTitle("Muhammad Erwin")
        copyRightsElement.setIconDrawable(R.drawable.ic_baseline_copyright_24)
        copyRightsElement.setAutoApplyIconTint(true)
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color)
        copyRightsElement.setIconNightTint(android.R.color.white)
        copyRightsElement.setGravity(Gravity.CENTER)
        copyRightsElement.setOnClickListener { Toast.makeText(this, copyrights, Toast.LENGTH_SHORT).show() }
        return copyRightsElement
    }
}