package com.shshy.securesharepreferencedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shshy.securesharepreference.SecureSharePreference

class MainActivity : AppCompatActivity() {
    private val str = "asdfasdfsadf我是阿斯顿发阿道夫{sa}fadsfdasfdsa=-|()*&^%$#@!~"
    private val float = 1.26156f
    private val int = 13234
    private val boolean = false
    private val long = 1214987561L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SecureSharePreference.put(this, "strKey", str)
        Log.e("AAA", "str ${str == (SecureSharePreference.get(this, "strKey", "") as String)}")

        SecureSharePreference.put(this, "floatKey", float)
        Log.e("AAA", "float ${float == (SecureSharePreference.get(this, "floatKey", 0f) as Float)}")

        SecureSharePreference.put(this, "intKey", int)
        Log.e("AAA", "int ${int == (SecureSharePreference.get(this, "intKey", 0) as Int)}")

        SecureSharePreference.put(this, "booleanKey", boolean)
        Log.e("AAA", "boolean ${boolean == (SecureSharePreference.get(this, "booleanKey", false) as Boolean)}")

        SecureSharePreference.put(this, "longKey", long)
        Log.e("AAA", "long ${long == (SecureSharePreference.get(this, "longKey", 0L) as Long)}")

        Log.e("AAA", "str ${(SecureSharePreference.get(this, "strKey1", "aaa") as String)}")
        Log.e("AAA", "float ${ (SecureSharePreference.get(this, "floatKey1", 131.2f) as Float)}")
        Log.e("AAA", "int ${ (SecureSharePreference.get(this, "intKey1", 3312) as Int)}")
        Log.e("AAA", "boolean ${(SecureSharePreference.get(this, "booleanKey1", false) as Boolean)}")
        Log.e("AAA", "long ${(SecureSharePreference.get(this, "longKey1", 257575135L) as Long)}")
    }
}
