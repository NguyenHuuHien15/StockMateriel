package com.mercijack.stockmateriel.presentation.listmateriels

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.domain.Materiel
import org.apache.commons.lang3.StringUtils

@BindingAdapter("item")
fun bindImage(imgView: ImageView, item: Materiel?) {
    Glide.with(imgView.context).load(R.drawable.bricologe_2).into(imgView)

    imgView.clipToOutline = true
}

@BindingAdapter("code", "name")
fun setVisibilityImgBtn(view: View, code: String, name: String) {
    if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(name)) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}