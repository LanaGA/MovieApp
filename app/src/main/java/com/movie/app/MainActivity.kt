package com.movie.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.movie.app.di.MOVIES_QUALIFIER
import com.movie.app.ui.HolderFragment
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router

class MainActivity : AppCompatActivity() {
    private val router: Router by inject(named(MOVIES_QUALIFIER))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, HolderFragment.newInstance())
            .commit()
    }
    override fun onBackPressed() {
        router.exit()
    }
}