package admiral.group.itterminlari.presentation.custombottomnav.util

import android.view.View
import android.view.ViewGroup


/**
 * Returns a [Sequence] over the child views in this view group.
 *
 * @return child view [Sequence]
 */
internal fun ViewGroup.getChildren(): Sequence<View> = object : Sequence<View> {
    override fun iterator() = this@getChildren.iterator()
}

/**
 * Create a [Iterator] over the views in this view group.
 *
 * @return child view [Iterator]
 */
private operator fun ViewGroup.iterator(): Iterator<View> = object : MutableIterator<View> {
    private var index = 0
    override fun hasNext() = index < childCount
    override fun next() = getChildAt(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeViewAt(--index)
}