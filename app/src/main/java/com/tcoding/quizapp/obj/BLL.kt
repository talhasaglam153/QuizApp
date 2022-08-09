package com.tcoding.quizapp.obj

import com.tcoding.quizapp.model.Category

object BLL {

    private var allCategories = ArrayList<Category>()


    fun createCategory() {
        allCategories.add(Category(1, "Arts & Literature", "arts_and_literature"))
        allCategories.add(Category(2, "Film & TV","film_and_tv"))
        allCategories.add(Category(3, "Food & Drink","food_and_drink"))
        allCategories.add(Category(4, "General Knowledge","general_knowledge"))
        allCategories.add(Category(5, "Geograpy","geography"))
        allCategories.add(Category(6, "History","history"))
        allCategories.add(Category(7, "Music","music"))
        allCategories.add(Category(8, "Science","science"))
        allCategories.add(Category(9, "Society & Culture","society_and_culture"))
        allCategories.add(Category(10, "Sport & Leisure","sport_and_leisure"))
    }

    fun getAllCategory(): ArrayList<Category> {
        return allCategories
    }

    fun selectList(): ArrayList<Category> {
        val selectCategoryList = ArrayList<Category>()
        for(category in allCategories) {
            if(category.isSelected) {
                selectCategoryList.add(category)
            }
        }
        return selectCategoryList
    }




}