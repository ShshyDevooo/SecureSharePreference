package com.shshy.securesharepreference

import android.content.Context
import android.content.SharedPreferences
import com.shshy.keystorelib.KeyStoreRSA
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * @author  ShiShY
 * @Description:
 * @data  2020/4/9 14:09
 */
object SecureSharePreference {
    private val DEFAULT_FILE_NAME = "share_data"

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    fun put(context: Context, key: String, `object`: Any, fileName: String = DEFAULT_FILE_NAME, keyToHashCode: Boolean = true, encrypt: Boolean = true) {
        KeyStoreRSA.getInstance().generateKey(context)
        val sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sp.edit()
        val editKey = if (keyToHashCode) "${key.hashCode()}" else key
        val objectStr = if (encrypt) {
            Base64.encode(KeyStoreRSA.getInstance().encrypt("$`object`".toByteArray()))
        } else {
            "$`object`"
        }
        editor.putString(editKey, objectStr)
        SharedPreferencesCompat.apply(editor)
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    fun get(context: Context, key: String, defaultObject: Any, fileName: String = DEFAULT_FILE_NAME, keyToHashCode: Boolean = true, decrypt: Boolean = true): Any? {
        KeyStoreRSA.getInstance().generateKey(context)
        val sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val storeKey = if (keyToHashCode) "${key.hashCode()}" else key
        val valueStr = if (decrypt) {
            val dataInFile = sp.getString(storeKey, null)
            if (dataInFile != null) {
                val decryptByteArray = KeyStoreRSA.getInstance().decrypt(Base64.decode(String(dataInFile.toByteArray())))
                if (decryptByteArray != null) {
                    String(decryptByteArray)
                } else
                    null
            } else
                null
        } else {
            sp.getString(storeKey, null)
        }
        return if (valueStr == null)
            defaultObject
        else
            when (defaultObject) {
                is String -> valueStr
                is Int -> valueStr.toInt()
                is Boolean -> valueStr.toBoolean()
                is Float -> valueStr.toFloat()
                is Long -> valueStr.toLong()
                else -> null
            }
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private object SharedPreferencesCompat {
        private val sApplyMethod = findApplyMethod()

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        private fun findApplyMethod(): Method? {
            try {
                val clz = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
            }

            return null
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        fun apply(editor: SharedPreferences.Editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return
                }
            } catch (e: IllegalArgumentException) {
            } catch (e: IllegalAccessException) {
            } catch (e: InvocationTargetException) {
            }
            editor.commit()
        }
    }
}