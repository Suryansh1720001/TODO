package com.google.todo


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() , todoRVAdapter.todoItemClickInterface{

    lateinit var itemsRV : RecyclerView
    lateinit var addFAB : FloatingActionButton
    lateinit var list: List<todoItems>
    lateinit var todoRVAdapter: todoRVAdapter
    lateinit var TodoViewModel: todoViewModel
    private var tvSelectedDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV = findViewById(R.id.idRVItem)
        addFAB = findViewById(R.id.idFABAdd)

        list = ArrayList<todoItems>()
        todoRVAdapter = todoRVAdapter(list,this)
        itemsRV.layoutManager= LinearLayoutManager(this)
        itemsRV.adapter = todoRVAdapter
        val todoRepository = todoRepository(todoDatabase(this))
        val factory = todoViewModelFactory(todoRepository)
        TodoViewModel = ViewModelProvider(this,factory).get(todoViewModel::class.java)

        TodoViewModel.getAlltodoItems().observe(this,Observer{
            todoRVAdapter.list = it
            todoRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener{
            openDialog()
        }

        // current date show on selected date
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        currentDate?.let {
            tvSelectedDate?.text = currentDate.toString()
        }



    }

    fun openDialog() {

        val gdialog = Dialog(this)
        gdialog.setContentView(R.layout.todo_add_dialog)

        val cancelBtn = gdialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn = gdialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt = gdialog.findViewById<EditText>(R.id.idEdItemName)

        cancelBtn.setOnClickListener {
            gdialog.dismiss()

        }

        addBtn.setOnClickListener {
            val itemName: String = itemEdt.text.toString()
            if ((itemName.isNotEmpty())) {
                val items = todoItems(itemName)
                TodoViewModel.insert(items)
                Toast.makeText(applicationContext, "Item inserted", Toast.LENGTH_LONG).show()
                todoRVAdapter.notifyDataSetChanged()
                gdialog.dismiss()
            }else {
                Toast.makeText(applicationContext, "Please enter the data", Toast.LENGTH_LONG).show()
            }

        }

        gdialog.show()
    }


    override fun onItemClick(todoItems: todoItems) {
        TodoViewModel.delete(todoItems)
        todoRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_LONG).show()
    }
}