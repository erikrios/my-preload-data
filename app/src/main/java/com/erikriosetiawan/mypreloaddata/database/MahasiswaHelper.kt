package com.erikriosetiawan.mypreloaddata.database

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.erikriosetiawan.mypreloaddata.database.DatabaseContract.MahasiswaColumns.Companion.NAMA
import com.erikriosetiawan.mypreloaddata.database.DatabaseContract.MahasiswaColumns.Companion.NIM
import com.erikriosetiawan.mypreloaddata.database.DatabaseContract.TABLE_NAME
import com.erikriosetiawan.mypreloaddata.model.MahasiswaModel

class MahasiswaHelper(context: Context) {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private var INSTANCE: MahasiswaHelper? = null

        fun getInstance(context: Context): MahasiswaHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = MahasiswaHelper(context)
                    }
                }
            }
            return INSTANCE as MahasiswaHelper
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun getAllData(): ArrayList<MahasiswaModel> {
        val cursor = database.query(TABLE_NAME, null, null, null, null, null, "$_ID ASC", null)
        cursor.moveToFirst()
        val arrayList = ArrayList<MahasiswaModel>()
        var mahasiswaModel: MahasiswaModel
        if (cursor.count > 0) {
            do {
                mahasiswaModel = MahasiswaModel().apply {
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                    name = cursor.getString(cursor.getColumnIndexOrThrow(NAMA))
                    nim = cursor.getString(cursor.getColumnIndexOrThrow(NIM))
                }

                arrayList.add(mahasiswaModel)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun insert(mahasiswaModel: MahasiswaModel): Long {
        val initialValues = ContentValues().apply {
            put(NAMA, mahasiswaModel.name)
            put(NIM, mahasiswaModel.nim)
        }
        return database.insert(TABLE_NAME, null, initialValues)
    }

    fun getDataByName(name: String): ArrayList<MahasiswaModel> {
        val cursor =
            database.query(
                TABLE_NAME,
                null,
                "$NAMA LIKE ?",
                arrayOf(name),
                null,
                null,
                "$_ID ASC",
                null
            )
        cursor.moveToFirst()
        val arrayList = ArrayList<MahasiswaModel>()
        var mahasiswaModel: MahasiswaModel
        if (cursor.count > 0) {
            do {
                mahasiswaModel = MahasiswaModel().apply {
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                    this.name = cursor.getString(cursor.getColumnIndexOrThrow(NAMA))
                    nim = cursor.getString(cursor.getColumnIndexOrThrow(NIM))
                }

                arrayList.add(mahasiswaModel)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }
}