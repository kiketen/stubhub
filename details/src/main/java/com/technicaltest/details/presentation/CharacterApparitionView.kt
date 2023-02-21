package com.technicaltest.details.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.technicaltest.details.R
import com.technicaltest.details.databinding.CharacterApparitionViewBinding
import com.technicaltest.details.domain.CharacterApparition
import com.technicaltest.stubhub.core.extensions.switchVisibility


@SuppressLint("Recycle")
class CharacterApparitionView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    styleDef: Int = 0
) : ConstraintLayout(context, attributeSet, styleDef) {

    private var adapter: CharacterApparitionAdapter
    private val binding = CharacterApparitionViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CharacterApparitionView)
        val title = typedArray.getString(R.styleable.CharacterApparitionView_title)
        binding.characterApparitionTitle.text = title

        adapter = CharacterApparitionAdapter(apparitions = mutableListOf())
        binding.characterApparitions.adapter = adapter
    }

    fun showLoading(visible: Boolean) {
        binding.apparitionLoading.switchVisibility(visible)
    }

    fun showApparitions(apparitions: List<CharacterApparition>) {
        adapter.update(apparitions)
    }

}