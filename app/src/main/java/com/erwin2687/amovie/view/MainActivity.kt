package com.erwin2687.amovie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.erwin2687.amovie.R
import com.erwin2687.amovie.view.fragments.FavoriteFragment
import com.erwin2687.amovie.view.fragments.MovieFragment
import com.erwin2687.amovie.view.fragments.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.selects.whileSelect

class MainActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_STATE = "movie"
        const val SUCCESS_STATE = "success"
        const val ERROR_MESSAGE_STATE = "error_message"
        const val DATA_EXTRA = "data"
        const val BUNDLE_EXTRA = "bundle"
        const val MOVIE = "movie"
        const val TVSHOW = "tvshow"
        const val FAVORITE = "favorite"
        const val INSTANCE = "instance"
        const val FAVORITE_STATE = "favorite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movies -> {
                    loadMovieFragment(MOVIE)
                }
                R.id.navigation_tvshows -> {
                    loadTvFragment(TVSHOW)
                }
                R.id.navigation_favorite -> {
                    loadFavFragment(FAVORITE)
                }
            }
            true
        }

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.navigation_movies
        } else {
            when (savedInstanceState.getString(INSTANCE)) {
                MovieFragment::class.java.simpleName -> {
                    bottom_navigation.selectedItemId = R.id.navigation_movies
                }
                TvShowFragment::class.java.simpleName -> {
                    bottom_navigation.selectedItemId = R.id.navigation_tvshows
                }
                FavoriteFragment::class.java.simpleName -> {
                    bottom_navigation.selectedItemId = R.id.navigation_favorite
                }
            }
        }

    }

    private fun loadTvFragment(type: String) {
        val tvShowFragment = TvShowFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_EXTRA, type)
        tvShowFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, tvShowFragment, TvShowFragment::class.java.simpleName)
            .commit()
    }

    private fun loadMovieFragment(type: String) {
        val movieFragment = MovieFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_EXTRA, type)
        movieFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, movieFragment, MovieFragment::class.java.simpleName)
            .commit()
    }

    private fun loadFavFragment(type: String) {
        val favoriteFragment = FavoriteFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_EXTRA, type)
        favoriteFragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, favoriteFragment, FavoriteFragment::class.java.simpleName)
            .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_localization -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        when(item.itemId) {
            R.id.aboutBtn ->{
                val aIntent = Intent(this,AbaoutActivity::class.java)
                startActivity(aIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
