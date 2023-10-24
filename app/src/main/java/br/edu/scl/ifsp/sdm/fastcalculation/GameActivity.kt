package br.edu.scl.ifsp.sdm.fastcalculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), OnPlaygame {
    private val activityGameBinding:ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private lateinit var settings:Settings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGameBinding.root)

        setSupportActionBar(activityGameBinding.gameTbIn.gameTb)
        supportActionBar?.apply {
            title=getString(R.string.app_name)
            subtitle= getString(R.string.game)
        }

        settings = intent.getParcelableExtra<Settings>(EXTRA_SETTINGS)?: Settings()

        supportFragmentManager.beginTransaction()
            .replace(R.id.gameFl,WelcomeFragment.newInstance(settings)).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.restartGameMi -> {
                OnPlaygame()
                true
            }
            R.id.exitMi -> {
                finish()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun OnPlaygame() {
        supportFragmentManager.beginTransaction().replace(R.id.gameFl,GameFragment.newInstance(settings)).commit()
    }
}