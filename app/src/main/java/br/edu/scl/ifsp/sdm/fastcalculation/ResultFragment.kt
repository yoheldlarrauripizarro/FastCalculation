package br.edu.scl.ifsp.sdm.fastcalculation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_POINTS
import br.edu.scl.ifsp.sdm.fastcalculation.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var fragmentResultBinding: FragmentResultBinding
    private lateinit var settings: Settings

    private var points: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            settings = it.getParcelable(EXTRA_SETTINGS) ?: Settings()
            points = it.getFloat(EXTRA_POINTS, 0.0f)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding
            .inflate(inflater,container,false)

        fragmentResultBinding.apply {
            resultTv.text = points.toString()

            buttonRestartGame.setOnClickListener {
                val gameActivityIntent = Intent(requireContext(), GameActivity::class.java)
                gameActivityIntent.putExtra(EXTRA_SETTINGS, settings)
                startActivity(gameActivityIntent)
                requireActivity().finish()
            }
        }
        return fragmentResultBinding.root

    }

    companion object {

        @JvmStatic
        fun newInstance(settings: Settings, points: Float) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_SETTINGS, settings)
                    putFloat(EXTRA_POINTS, points)
                }
            }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible=false
    }
}