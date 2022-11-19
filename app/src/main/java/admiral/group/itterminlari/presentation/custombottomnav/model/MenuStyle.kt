package admiral.group.itterminlari.presentation.custombottomnav.model


import admiral.group.itterminlari.R
import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat


class MenuStyle(context: Context, attr: TypedArray) {


    @ColorInt val unselectedColor: Int
    @IdRes val textAppearance: Int?
    val radius: Float
    val iconSize: Int

    init {
        textAppearance = attr.getResourceId(R.styleable.SelloBottomNavigation_cnb_textAppearance, -1)
            .takeIf { it > 0 }

        radius = attr.getDimension(R.styleable.SelloBottomNavigation_cnb_radius, Float.MAX_VALUE)

        unselectedColor = attr.getColor(
            R.styleable.SelloBottomNavigation_cnb_unselectedColor,
            ContextCompat.getColor(context, R.color.cnb_default_unselected_color)
        )
        iconSize = attr.getDimension(
            R.styleable.SelloBottomNavigation_cnb_iconSize,
            context.resources.getDimension(R.dimen.cnb_icon_size)
        ).toInt()
    }
}