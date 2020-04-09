package com.shshy.securesharepreferencedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shshy.securesharepreference.SecureSharePreference

class MainActivity : AppCompatActivity() {
    private val str = """/**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */"""
    private val float = 1.26156f
    private val int = 13234
    private val boolean = false
    private val long = 1214987561L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SecureSharePreference.put(this, "strKey", str)
        Log.e("AAA", "str ${str == (SecureSharePreference.get(this, "strKey", ""))}")

        SecureSharePreference.put(this, "floatKey", float)
        Log.e("AAA", "float ${float == (SecureSharePreference.get(this, "floatKey", 0f))}")

        SecureSharePreference.put(this, "intKey", int)
        Log.e("AAA", "int ${int == (SecureSharePreference.get(this, "intKey", 0))}")

        SecureSharePreference.put(this, "booleanKey", boolean)
        Log.e("AAA", "boolean ${boolean == (SecureSharePreference.get(this, "booleanKey", false))}")

        SecureSharePreference.put(this, "longKey", long)
        Log.e("AAA", "long ${long == (SecureSharePreference.get(this, "longKey", 0L))}")

        Log.e("AAA", "str ${(SecureSharePreference.get(this, "strKey1", "aaa") as String)}")
        Log.e("AAA", "float ${(SecureSharePreference.get(this, "floatKey1", 131.2f))}")
        Log.e("AAA", "int ${(SecureSharePreference.get(this, "intKey1", 3312))}")
        Log.e("AAA", "boolean ${(SecureSharePreference.get(this, "booleanKey1", false))}")
        Log.e("AAA", "long ${(SecureSharePreference.get(this, "longKey1", 257575135L))}")
    }
}
