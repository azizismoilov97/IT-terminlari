package admiral.group.itterminlari.ui.home

import admiral.group.itterminlari.R
import admiral.group.itterminlari.data.repository.MyRepository
import admiral.group.itterminlari.databinding.ItemViewBinding
import admiral.group.itterminlari.domain.model.Termin
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class HomeAdapter(
    private val onClickListener: OnClickListener,
    private val onItemClickListener:OnItemClickListener
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var isPressed = false

    inner class ViewHolder( val viewBinding: ItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Termin>() {

        override fun areItemsTheSame(oldItem: Termin, newItem: Termin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Termin, newItem: Termin): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var myTerminList: List<Termin>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = myTerminList[position]

        holder.viewBinding.apply {
            txtWord.text=result.word

        }

        holder.viewBinding.item.setOnClickListener {
            onItemClickListener.onClick(result.id)
        }

        holder.viewBinding.bookmark.apply {
            if (result.bookmark==0){
                this.setBackgroundResource(R.drawable.ic_outline_bookmark_border_24)
            }else if (result.bookmark==1){
                this.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
            }
        }

        holder.viewBinding.bookmark.setOnClickListener{
           if (result.bookmark==1){
               it.setBackgroundResource(R.drawable.ic_outline_bookmark_border_24)
               onClickListener.onClick(Termin(result.word, result.description, 0, result.id))
           }else if (result.bookmark==0){
               it.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
               onClickListener.onClick(Termin(result.word, result.description, 1, result.id))
           }

        }
    }

    override fun getItemCount(): Int = myTerminList.size

    class OnClickListener(val clickListener: (termin:Termin) -> Unit) {

        fun onClick(termin: Termin) = clickListener(termin)

    }

    class OnItemClickListener(val clickItemListener: (id:Int) -> Unit) {

        fun onClick(id: Int) = clickItemListener(id)

    }


}