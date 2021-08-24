package com.myweb.ass7dialogrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.myweb.ass7dialogrv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val employeeList  = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root )

        testEmployeeData()
        binding.recyclerView.adapter = EmployeeAdapter(this.employeeList,applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        binding.recyclerView.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecor)
    }


    fun addEmployee(v: View){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        //AlertDialogBuilder
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)


///Save Button
        myBuilder.setPositiveButton("Add Employee" ) { dialog, which ->
            val name = mDialogView.findViewById(R.id.edt_name) as TextInputEditText
            val email = mDialogView.findViewById(R.id.edt_email) as TextInputEditText
            val salary = mDialogView.findViewById(R.id.edt_salary) as TextInputEditText

            var  radioGroup: RadioGroup = mDialogView.findViewById(R.id.radioGroup)

            var  selectedId : Int = radioGroup.checkedRadioButtonId

            var  radioButton : RadioButton = mDialogView.findViewById(selectedId)

            employeeList.add( Employee( name.text.toString(), radioButton.text.toString(),
                email.text.toString(),salary.text.toString().toInt()))

            binding.recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The employee is added successfully",
                Toast.LENGTH_LONG
            ).show()
        }
        ///Cancel Button
        myBuilder.setNegativeButton("Cancel",) { dialog, which ->
            dialog.dismiss()
        }
        //show dialog
        myBuilder.show()
    }
    fun testEmployeeData(){
        employeeList.add(Employee("Danny", "Male", "danny@kku.ac.th", 30000))
        employeeList.add(Employee("Sara", "Female", "sara@kku.ac.th", 34000))
    }
}