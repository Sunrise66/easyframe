package com.sunrise.easyframe.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sunrise.easyframe.common.OnItemActionListener

/**
 *@author: JiangYu
 *Date: 2021/3/5
 *Time: 21:04
 *Email: jiangyu@haogroup.com
 */
abstract class ViewTypeHolder(
    val binding: ViewDataBinding,
    val viewType: Int,
    val onItemActionListener: OnItemActionListener?
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindItem(item: ViewType<*>)
    companion object{

//        fun create(viewType: Int):ViewTypeHolder{
//            return
//        }
    }
}