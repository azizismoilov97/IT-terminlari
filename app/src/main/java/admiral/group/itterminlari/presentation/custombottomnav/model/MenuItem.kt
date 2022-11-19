package admiral.group.itterminlari.presentation.custombottomnav.model

import android.graphics.PorterDuff
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes

internal class MenuItem(
    val id: Int,
    val order: Int,
    val title: CharSequence,
    @DrawableRes val iconImage: Int,
    val enabled: Boolean,
    val tintMode: PorterDuff.Mode?,
    val iconTextSize:Float,
    @ColorInt val iconColor: Int,
    @ColorInt val textColor: Int,
    val menuStyle: MenuStyle
)