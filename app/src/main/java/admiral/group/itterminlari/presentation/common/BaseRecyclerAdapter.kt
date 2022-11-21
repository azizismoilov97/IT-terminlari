package admiral.group.itterminlari.presentation.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding



typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T


abstract class BaseRecyclerAdapter<VB : ViewBinding, M:Any> (
    private val inflate: Inflate<VB>):
    RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: VB

    private val differCallback=BaseDiffUtil<M>()

    private var myList=AsyncListDiffer(this, differCallback)


    fun loadData(list: List<M>){
        myList.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = inflate.invoke( LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        bindData(binding, myList.currentList[position],position)
    }

    override fun getItemCount(): Int = myList.currentList.size


    abstract fun bindData(binding: VB, item: M,position: Int)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

// DuffUtil

class BaseDiffUtil<T : Any> : DiffUtil.ItemCallback<T>() {

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}