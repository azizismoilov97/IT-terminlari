package admiral.group.itterminlari.presentation.custombottomnav.model


import admiral.group.itterminlari.R
import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat


class MenuStyle(context: Context, attr: TypedArray) {


    @ColorInt val unselectedColor: Int

    init {

        unselectedColor = attr.getColor(
            R.styleable.SelloBottomNavigation_cnb_unselectedColor,
            ContextCompat.getColor(context, R.color.cnb_default_unselected_color)
        )

    }
}