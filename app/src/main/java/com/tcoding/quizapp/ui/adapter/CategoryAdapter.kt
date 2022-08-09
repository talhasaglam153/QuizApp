package com.tcoding.quizapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.method.Touch
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.quizapp.R
import com.tcoding.quizapp.model.Category
import kotlinx.coroutines.selects.select

class CategoryAdapter(var categoryList: ArrayList<Category>, var itemClick:((position: Int)->Unit)?): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private var selectedItemPosition: Int = 0

    class CategoryViewHolder(itemView: View, itemClick: ((position: Int) -> Unit)?, categoriList:ArrayList<Category>): RecyclerView.ViewHolder(itemView) {

        var tvCategory: TextView
        var cardView: CardView
        var constrain: ConstraintLayout
        var selectPosition = 0


        init {
            tvCategory = itemView.findViewById(R.id.tvCategory)
            cardView = itemView.findViewById(R.id.cardView)
            constrain = itemView.findViewById(R.id.constrain)

        }

        fun bindData(category: Category) {
            tvCategory.text = category.ad

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_row, parent, false)
        return CategoryViewHolder(view, itemClick,categoryList)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.bindData(categoryList.get(position))


        holder.constrain.setOnClickListener {
            categoryList[position].isSelected = !categoryList[position].isSelected
            notifyDataSetChanged()
        }

       if(categoryList[position].isSelected) {
           holder.constrain.background = holder.constrain.context.getDrawable(R.drawable.question_false)
       }else {
           holder.constrain.background = holder.constrain.context.getDrawable(R.drawable.question_true)
       }

    }

    override fun getItemCount(): Int {
       return 10
    }

}