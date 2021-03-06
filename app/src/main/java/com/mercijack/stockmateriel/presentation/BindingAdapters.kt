package com.mercijack.stockmateriel.presentation

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.domain.Materiel
import org.apache.commons.lang3.StringUtils

@BindingAdapter("item")
fun bindImage(imgView: ImageView, item: Materiel?) {
    Glide.with(imgView.context).load(R.drawable.materiel_de_bricolage).into(imgView)

    imgView.clipToOutline = true
}

@BindingAdapter("code", "name")
fun setVisibilityImgBtn(view: View, code: String, name: String) {
    if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(name)) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("number_materiels")
fun setContentTextView(view: MaterialTextView, numberMateriels: Int) {
    val content = when (numberMateriels) {
        0 -> "Vide"
        1 -> "1 matériel"
        else -> "$numberMateriels matériels"
    }
    view.text = content
}