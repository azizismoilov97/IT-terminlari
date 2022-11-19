package admiral.group.itterminlari.presentation.custombottomnav.model


import admiral.group.itterminlari.R
import admiral.group.itterminlari.presentation.custombottomnav.util.getValueFromAttr
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.content.res.XmlResourceParser
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Xml
import androidx.annotation.MenuRes
import org.xmlpull.v1.XmlPullParser.*

internal class MenuParser(private val context: Context) {

    private companion object {
        const val XML_MENU_TAG = "menu"
        const val XML_MENU_ITEM_TAG = "item"
    }

    fun parse(@MenuRes menuRes: Int, menuStyle: MenuStyle): Menu {
        @SuppressLint("ResourceType")
        val parser = context.resources.getLayout(menuRes)
        val attrs = Xml.asAttributeSet(parser)

        skipMenuTagStart(parser)

        return parseMenu(parser, attrs, menuStyle)
    }

    private fun skipMenuTagStart(parser: XmlResourceParser) {
        var currentEvent = parser.eventType
        do {
            if (currentEvent == START_TAG) {
                val name = parser.name
                require(name == XML_MENU_TAG) { "Expecting menu, got $name" }
                break
            }
            currentEvent = parser.next()
        } while (currentEvent != END_DOCUMENT)
    }

    private fun parseMenu(
        parser: XmlResourceParser,
        attrs: AttributeSet,
        menuStyle: MenuStyle
    ): Menu {
        val items = mutableListOf<MenuItem>()
        var eventType = parser.eventType
        var isEndOfMenu = false

        while (!isEndOfMenu) {
            val name = parser.name
            when {
                eventType == START_TAG && name == XML_MENU_ITEM_TAG -> {
                    val item = parseMenuItem(attrs, menuStyle)
                    items.add(item)
                }
                eventType == END_TAG && name == XML_MENU_TAG -> isEndOfMenu = true
                eventType == END_DOCUMENT -> throw RuntimeException("Unexpected end of document")

            }
            eventType = parser.next()
        }
        return Menu(items = items)
    }

    private fun parseMenuItem(attrs: AttributeSet, menuStyle: MenuStyle): MenuItem {
        val sAttr = context.obtainStyledAttributes(attrs, R.styleable.SelloMenuItem)

        val item = MenuItem(
            id = sAttr.getResourceId(R.styleable.SelloMenuItem_android_id, 0),
            order = sAttr.getInt(R.styleable.SelloMenuItem_order, 0),
            title = sAttr.getText(R.styleable.SelloMenuItem_android_title),
            iconImage = sAttr.getResourceId(R.styleable.SelloMenuItem_android_icon, 0),
            enabled = sAttr.getBoolean(R.styleable.SelloMenuItem_android_enabled, true),
            iconColor = readIconActiveColor(sAttr),
            iconTextSize = sAttr.getDimension(R.styleable.SelloMenuItem_iconTextSize,
            context.resources.getDimension(R.dimen.cnb_iconText_size)),
            iconSize = sAttr.getDimension(R.styleable.SelloMenuItem_iconSize,
                context.resources.getDimension(R.dimen.cnb_icon_size)),
            tintMode = readIconTintMode(sAttr),
            textColor = readTextActiveColor(sAttr),
            menuStyle = menuStyle
        )

        sAttr.recycle()
        return item
    }

    private fun readIconTintMode(sAttr: TypedArray): PorterDuff.Mode? =
        when (sAttr.getInt(R.styleable.SelloMenuItem_cnb_iconTintMode, -1)) {
            3 -> PorterDuff.Mode.SRC_OVER
            5 -> PorterDuff.Mode.SRC_IN
            9 -> PorterDuff.Mode.SRC_ATOP
            14 -> PorterDuff.Mode.MULTIPLY
            15 -> PorterDuff.Mode.SCREEN
            16 -> PorterDuff.Mode.ADD
            else -> null
        }

    private fun readIconActiveColor(sAttr: TypedArray): Int = sAttr.getColor(
        R.styleable.SelloMenuItem_cnb_iconColor,
        context.getValueFromAttr(R.attr.colorAccent)
    )

    private fun readTextActiveColor(sAttr: TypedArray): Int =
        sAttr.getColor(
            R.styleable.SelloMenuItem_cnb_textColor,
            readIconActiveColor(sAttr)
        )


}