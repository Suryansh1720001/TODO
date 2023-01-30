package com.google.todo


import  androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class todoItems(

    @ColumnInfo(name="itemName")
    var itemName: String,

)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}