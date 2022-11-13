package admiral.group.itterminlari.presentation.ui.description

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentDescriptionBinding
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.presentation.ui.main.MainActivity
import admiral.group.itterminlari.presentation.util.Helper
import admiral.group.itterminlari.presentation.viewmodel.MainViewModel
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.abs


@AndroidEntryPoint
class DescriptionFragment : Fragment(R.layout.fragment_description), TextToSpeech.OnInitListener {

    private val viewBinding: FragmentDescriptionBinding by viewBinding(FragmentDescriptionBinding::bind)

    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    private val viewModel: MainViewModel by viewModels()

    private val args: DescriptionFragmentArgs by navArgs()

    private var wordTxt: String=" "

    private var wordDescription: String=" "

    private var bookmark: Int = 0

    private var dataId: Int = 0

    private var tts: TextToSpeech? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       tts = TextToSpeech(context, this)

        with(viewBinding) {
            shareWord.setOnClickListener {
                Helper.shareNote(requireContext(), "So'z: ${word.text.toString().lowercase(Locale.ROOT).
                replaceFirstChar { it.uppercase() }}\n\nTa'rif: ${contentLayout.
                txtInfo.text.toString().lowercase(Locale.ROOT).replaceFirstChar { it.uppercase() }
                }")
            }

            textToSpeech.setOnClickListener {
                speakOut()
            }

            requireActivity().setActionBar(toolbar)
            requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
            requireActivity().actionBar?.setDisplayShowHomeEnabled(true)

            viewModel.getTermin(args.id)

            viewModel.termin.observe(viewLifecycleOwner) { data ->

                if (data != null) {
                    wordTxt=data.word
                    word.text = data.word.uppercase(Locale.ROOT)
                    contentLayout.txtInfo.text=data.description.replaceFirstChar { it.uppercase() }
                }
                wordTxt=data.word
                wordDescription=data.description
                bookmark=data.bookmark
                dataId=data.id

                setBookmark(bookmark, savedWord)

            }

            savedWord.apply {

                setOnClickListener {
                    if (bookmark==1){
                        bookmark=0
                        this.setImageResource(R.drawable.ic_outline_bookmark_border_24)
                    }else if (bookmark==0){
                        this.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        bookmark=1
                    }

                    viewModel.updateTermin(TerminEntity(wordTxt, wordDescription, bookmark, dataId))
                }
            }

            appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

                if(abs(verticalOffset) - appBarLayout.totalScrollRange == 0){
                    toolbarLayout.title=wordTxt.uppercase()
                }else{
                    toolbarLayout.title=" "
                }

            }

            toolbar.setNavigationOnClickListener {
                navController.navigateUp()
            }

        }



    }

    private fun setBookmark(bookmark:Int, image:AppCompatImageView){
        if (bookmark==0){
            image.setImageResource(R.drawable.ic_outline_bookmark_border_24)
        }else if (bookmark==1){
            image.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                viewBinding.textToSpeech.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    private fun speakOut() {
        val text = viewBinding.word.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}