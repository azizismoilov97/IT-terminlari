package admiral.group.itterminlari.presentation.custombottomnav.view

import admiral.group.itterminlari.presentation.custombottomnav.model.MenuItem
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


internal abstract class MenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    abstract fun bind(item: MenuItem)

}