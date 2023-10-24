package br.edu.scl.ifsp.sdm.fastcalculation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private val activitySettingsBinding:ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySettingsBinding.root)

        setSupportActionBar(activitySettingsBinding.gameTbIn.gameTb)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.subtitle = getString(R.string.settings)

        activitySettingsBinding.apply {
            letsGoBt.setOnClickListener {
                val settings = Settings(
                    playerNameEt.text.toString(),
                    (roundsSp.selectedView as TextView).text.toString().toInt(),
                    roundIntervalRg.run{
                        findViewById<RadioButton>(checkedRadioButtonId).text.toString().toLong()*1000L
                    }
                )
                val gameActivityIntent = Intent(this@SettingsActivity,GameActivity::class.java)
                gameActivityIntent.putExtra(EXTRA_SETTINGS,settings)
                startActivity(gameActivityIntent)
                finish()
            }
        }
    }
}