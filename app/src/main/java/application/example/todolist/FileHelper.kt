package application.example.todolist

import android.content.Context
import java.io.*

class FileHelper {

    val FILENAME ="listinfo.dat"

    fun writeData(item:ArrayList<String>,context:Context ) {

        var fos : FileOutputStream=context.openFileOutput(FILENAME,Context.MODE_PRIVATE)//saving the data
        //this method will make a file in file memory
        //context_Mode_private is used so that other application cant use to access it

        var oas=ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }

    fun readData(context: Context) :ArrayList<String>
    {

        var itemList:ArrayList<String>

        try {
            var fis :FileInputStream =context.openFileInput(FILENAME)  //reading the data
            var ois=ObjectInputStream(fis)
            itemList= ois.readObject() as ArrayList<String>
        }catch (e:FileNotFoundException){
            itemList=ArrayList()
        }
        return itemList
    }
}