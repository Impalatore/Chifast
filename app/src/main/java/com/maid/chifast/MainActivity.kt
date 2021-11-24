package com.maid.chifast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class MainActivity : AppCompatActivity() {

    val recyclerCategories = findViewById<RecyclerView>(R.id.recycler_categories)
    val recyclerItems = findViewById<RecyclerView>(R.id.recycler_food)
    val emailTextView = findViewById<TextView>(R.id.emailTextView)
    val providerTextView = findViewById<TextView>(R.id.providerTextView)
    val btnlogOut = findViewById<Button>(R.id.btnlogOut)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setCategoria();
        setFoodItem(0);

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String){
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        btnlogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    private fun setCategoria() {
        val data: MutableList<FoodCategory> = ArrayList()

        val foodCategory = FoodCategory("Sopas", R.drawable.ic_burger)
        val foodCategory2 = FoodCategory("Chaufas", R.drawable.ic_chicken)
        val foodCategory3 = FoodCategory("Tallarines", R.drawable.ic_pizza)
        val foodCategory4 = FoodCategory("Adicionales", R.drawable.ic_chicken)

        data.add(foodCategory)
        data.add(foodCategory2)
        data.add(foodCategory3)
        data.add(foodCategory4)

        val foodCategoryAdapter = FoodCategoryAdapter(
            data, this@MainActivity
        ) { pos -> setFoodItem(pos) }

        recyclerCategories.setLayoutManager(
            LinearLayoutManager(
                this@MainActivity,
                RecyclerView.HORIZONTAL,
                false
            )
        )
        recyclerCategories.setAdapter(foodCategoryAdapter)
        foodCategoryAdapter.notifyDataSetChanged()
    }

    private fun setFoodItem(pos: Int) {
        val foodItems: MutableList<FoodItem> = ArrayList()
        when (pos) {
            2 -> {
                val foodItem = FoodItem("Pizza", 4.5f, 14, R.drawable.pizza_1)
                val foodItem2 = FoodItem("Pizza one ", 5f, 14, R.drawable.pizza_2)
                val foodItem3 = FoodItem("Pizza two", 4f, 14, R.drawable.pizza_3)
                val foodItem4 = FoodItem("Pizza", 3.5f, 14, R.drawable.pizza_4)
                foodItems.add(foodItem)
                foodItems.add(foodItem2)
                foodItems.add(foodItem3)
                foodItems.add(foodItem4)
            }
            1 -> {
                val foodItem5 = FoodItem("Chicken", 4.5f, 14, R.drawable.grill_chicken_1)
                val foodItem6 = FoodItem("Chicken one ", 5f, 14, R.drawable.grill_chicken_2)
                val foodItem7 = FoodItem("Chicken two", 4f, 14, R.drawable.grill_chicken_3)
                val foodItem8 = FoodItem("Chicken", 3.5f, 14, R.drawable.grill_chicken_2)
                foodItems.add(foodItem5)
                foodItems.add(foodItem6)
                foodItems.add(foodItem7)
                foodItems.add(foodItem8)
            }
            0 -> {
                val foodItem9 = FoodItem("Burger", 4.5f, 14, R.drawable.burger_two)
                val foodItem10 = FoodItem("Burger one ", 5f, 14, R.drawable.burger)
                val foodItem11 = FoodItem("Burger two", 4f, 14, R.drawable.burger_two)
                val foodItem12 = FoodItem("Burger", 3.5f, 14, R.drawable.burger)
                foodItems.add(foodItem9)
                foodItems.add(foodItem10)
                foodItems.add(foodItem11)
                foodItems.add(foodItem12)
            }
        }

        val foodAdapter = FoodAdapter(foodItems)
        recyclerItems.setLayoutManager(
            LinearLayoutManager(
                this@MainActivity,
                RecyclerView.HORIZONTAL,
                false
            )
        )

        recyclerItems.setAdapter(foodAdapter)
    }
}