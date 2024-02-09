package com.example.filmapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.libraryapp.model.Film
import com.example.filmapp.model.Customer
import com.example.libraryapp.model.CustomerWithRentals

@Dao
interface CustomerDao {

    @Query("select * from customer")
    fun list(): List<Customer>

    @Insert
    fun save(customer: Customer)

    @Transaction
    @Query("select * from customer where id = :customerId")
    fun listCustomerRentals(customerId: Int): List<CustomerWithRentals>

}