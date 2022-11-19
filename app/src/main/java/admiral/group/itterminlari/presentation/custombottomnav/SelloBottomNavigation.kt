package admiral.group.itterminlari.presentation.custombottomnav

import admiral.group.itterminlari.R
import admiral.group.itterminlari.presentation.custombottomnav.model.MenuParser
import admiral.group.itterminlari.presentation.custombottomnav.model.MenuStyle
import admiral.group.itterminlari.presentation.custombottomnav.util.getChildren
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.MenuRes
import androidx.constraintlayout.helper.widget.Flow
import com.google.android.material.bottomnavigation.BottomNavigationView
import admiral.group.itterminlari.presentation.custombottomnav.view.MenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import com.tune.core_resources.custom_view.custombottomnav.view.SelloMenuItemView


class SelloBottomNavigation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null
) : ConstraintLayout(context, attrs) {


    private var listener: OnItemSelectedListener? = null
    private val menuStyle: MenuStyle

    @MenuRes
    private var menuRes = -1


    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.SelloBottomNavigation)

        val menuResource = a.getResourceId(R.styleable.SelloBottomNavigation_cnb_menuResource, -1)
        menuStyle = MenuStyle(context, a)

        a.recycle()

        if (menuResource >= 0) {
            setMenuResource(menuResource)
        }
    }

    private fun setMenuResource(@MenuRes menuRes: Int) {
        this.menuRes = menuRes

        val menu = (MenuParser(context).parse(menuRes, menuStyle))
        val childListener: (View) -> Unit = { view -> setItemSelected(view.id) }

        menu.items.forEach {

            createMenuItem().apply {
                    bind(it)
                    setOnClickListener(childListener)
                }.also(::addView)
            if (it.order==1){
                isSelected = true
            }
        }

        getHorizontalFlow()

        getHorizontalFlow().apply { referencedIds = menu.items.map { it.id }.toIntArray() }
            .also(::addView)
    }
    

    private fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }

    fun setOnItemSelectedListener(block: (Int) -> Unit) {
        setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                block(id)
            }
        })
    }


     fun setItemSelected(id: Int, isSelected: Boolean=true) {
        val selectedItem = getSelectedItem()

        when {
            isSelected && selectedItem?.id != id -> {
                selectedItem?.isSelected = false
                getItemById(id)?.let {
                    it.isSelected = true
                        listener?.onItemSelected(id)
                }
            }
            !isSelected -> {
                getItemById(id)?.let {
                    it.isSelected = false
                }
            }
        }
    }

    private fun getSelectedItem() = getChildren().firstOrNull { it.isSelected }

    private fun getItemById(id: Int) = getChildren()
        .filterIsInstance<MenuItemView>()
        .firstOrNull { it.id == id }

    private fun createMenuItem(): MenuItemView {
        return SelloMenuItemView(context)
    }

    private fun getHorizontalFlow() = Flow(context).apply {
        setOrientation(Flow.HORIZONTAL)
        setHorizontalStyle(Flow.CHAIN_SPREAD)
        setHorizontalAlign(Flow.HORIZONTAL_ALIGN_START)
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    }

    interface OnItemSelectedListener {
        fun onItemSelected(id: Int)
    }

}