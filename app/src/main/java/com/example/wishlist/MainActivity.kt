package com.example.wishlist

import WishlistItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var list: List<WishlistItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemNameInput = findViewById<EditText>(R.id.itemNameInput)
        val priceInput = findViewById<EditText>(R.id.priceInput)
        val urlInput = findViewById<EditText>(R.id.urlInput)
        val coolButton = findViewById<Button>(R.id.inputButton)

        // Lookup the RecyclerView in activity layout
        val listRv = findViewById<RecyclerView>(R.id.listRv)
        // Fetch the list of wishlist items
        list = WishlistFetcher.getItems()
        // Create adapter passing in the list of wishlist items
        val adapter = WishlistAdapter(list)
        // Attach the adapter to the RecyclerView to populate items
        listRv.adapter = adapter
        // Set layout manager to position the items
        listRv.layoutManager = LinearLayoutManager(this)

        coolButton.setOnClickListener{
            (list as MutableList<WishlistItem>).add(WishlistItem(itemNameInput.text.toString(), priceInput.text.toString(),urlInput.text.toString()))
            adapter.notifyDataSetChanged()
        }

        fun getList() : MutableList<WishlistItem>{
            return list as MutableList<WishlistItem>
        }
    }
}