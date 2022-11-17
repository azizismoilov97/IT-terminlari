package admiral.group.itterminlari.presentation.ui.home.adapter

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.ItemViewBinding
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.presentation.ui.home.BaseRecyclerAdapter
import android.util.Log


class MyAdapter(private val onClickListener: OnClickListener,
                private val onItemClickListener: OnItemClickListener

): BaseRecyclerAdapter<ItemViewBinding, TerminEntity>(ItemViewBinding::inflate) {


    override fun bindData(binding: ItemViewBinding, item: TerminEntity, position: Int) {
        binding.apply {
            txtWord.text = item.word

        }

        binding.item.setOnClickListener {
            onItemClickListener.onClick(item.id)
        }

        binding.bookmark.apply {
            if (item.bookmark == 0){
                this.setBackgroundResource(R.drawable.ic_outline_bookmark_border_24)
            }else if (item.bookmark == 1){
                this.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
            }
        }

        binding.bookmark.setOnClickListener{
            if (item.bookmark == 1){
                it.setBackgroundResource(R.drawable.ic_outline_bookmark_border_24)
                onClickListener.onClick(TerminEntity(item.word, item.description, 0, item.id))
            }else if (item.bookmark == 0){
                it.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                onClickListener.onClick(TerminEntity(item.word, item.description, 1, item.id))
            }

        }
    }

    class OnClickListener(val clickListener: (termin: TerminEntity) -> Unit) {

        fun onClick(termin: TerminEntity) = clickListener(termin)

    }

    class OnItemClickListener(val clickItemListener: (id:Int) -> Unit) {

        fun onClick(id: Int) = clickItemListener(id)

    }



}