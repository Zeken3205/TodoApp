package application.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var item:EditText
    lateinit var add:Button
    lateinit var listview :ListView

    var itemList = ArrayList<String>()

    var fileHelper= FileHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item=findViewById(R.id.editText)
        add=findViewById(R.id.button)
        listview=findViewById(R.id.list)

        itemList=fileHelper.readData(this)

        var arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)

        listview.adapter=arrayAdapter

        add.setOnClickListener {
            var itemname:String=item.text.toString()
            if(itemname!="") {
                itemList.add(itemname)
                item.setText("")
                fileHelper.writeData(itemList, applicationContext)
                arrayAdapter.notifyDataSetChanged()
            }
        }

        listview.setOnItemClickListener { adapterView, view, position, l ->

            var alertDialog= AlertDialog.Builder(this@MainActivity)
            alertDialog.setTitle("Delete")
                .setMessage("Do You want to delete this Task?")
                .setCancelable(false)
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialogInterface, which ->
                    dialogInterface.cancel()
                })
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, which ->
                    itemList.removeAt(position)
                    arrayAdapter.notifyDataSetChanged()
                    fileHelper.writeData(itemList,applicationContext)
                })
            alertDialog.create().show()
        }
    }
}