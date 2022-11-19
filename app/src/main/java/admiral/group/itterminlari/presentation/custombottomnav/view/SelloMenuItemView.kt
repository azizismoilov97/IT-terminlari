package com.tune.core_resources.custom_view.custombottomnav.view

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.SelloBottomNavItemBinding
import admiral.group.itterminlari.presentation.custombottomnav.model.MenuItem
import admiral.group.itterminlari.presentation.custombottomnav.util.setColorStateListAnimator
import admiral.group.itterminlari.presentation.custombottomnav.view.MenuItemView
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.sello_bottom_nav_item.view.*


internal class SelloMenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MenuItemView(context, attrs) {


    private var binding : SelloBottomNavItemBinding = SelloBottomNavItemBinding.
       inflate(LayoutInflater.from(context),this)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun bind(item: MenuItem) {

        with(binding){


        id = item.id


        isEnabled = item.enabled
        containerMenuItem.isEnabled=false
        menuName.text = item.title
        menuName.visibility = View.VISIBLE
        menuName.setTextColor(item.textColor)
            menuName.textSize = item.iconTextSize
        menuName.setColorStateListAnimator(
            color = item.textColor,
            unselectedColor = item.menuStyle.unselectedColor
        )

        menuIcon.layoutParams.width = item.iconSize.toInt()
        menuIcon.layoutParams.height = item.iconSize.toInt()
        menuIcon.setImageResource(item.iconImage)
        menuIcon.setColorStateListAnimator(
            color = item.iconColor,
            unselectedColor = item.menuStyle.unselectedColor,
            mode = item.tintMode
        )
        }
    }

}