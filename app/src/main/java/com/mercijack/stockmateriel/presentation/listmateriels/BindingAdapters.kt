package com.mercijack.stockmateriel.presentation.listmateriels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.domain.Materiel

@BindingAdapter("item")
fun bindImage(imgView: ImageView, item: Materiel?) {
    Glide.with(imgView.context).load(R.drawable.bricologe_2).into(imgView)

    imgView.clipToOutline = true
}