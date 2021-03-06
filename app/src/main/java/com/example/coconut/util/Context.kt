package com.example.coconut.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import com.example.coconut.Constant
import com.example.coconut.R
import com.example.coconut.model.request.chat.ChatRoomExitRequest
import com.example.coconut.model.request.chat.ChatRoomNameChangeRequest
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.custom_dialog_default.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    toast(msg, length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    try {
        Handler(Looper.getMainLooper()).post {
            doToast(this, msg, length)
        }
    } catch (e: Exception) {
    }
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun ObservableField<String>.makeString(): String = this.get().toString()

fun String.toArray(): Array<String> = this.split(",").toTypedArray()

fun String.toCleanString(): String = this
    .replace("\"", "")
    .replace("\\n\\n", "")
    .replace("\\r\\n|\\r|\\n|\\n\\r".toRegex(), "")
    .replace("[", "")
    .replace("]", "")
    .replace(" ", "")

fun String.toArrayList(): ArrayList<String> = ArrayList(this.split(",").toList())

fun String.toHTTPString(): String =
    if (this.startsWith("http")) this
    else Constant.SPRING_BOOT_IMAGE_URL + this

fun ArrayList<String>.addIfNotInclude(string: String) {
    if (!this.contains(string))
        this.add(string)
}

fun <T> Collection<T>.filterNotIn(collection: Collection<T>): Collection<T> {
    val set = collection.toSet()
    return filterNot { set.contains(it) }
}

fun ArrayList<String>.showElements(): String {
    var str = ""
    for (a: String in this)
        str += "$a "
    return str
}

fun Array<String>.showElements(): String {
    var str = ""
    for (a: String in this)
        str += "$a "
    return str
}

fun Any.toJSONObject(): JSONObject? {
    return try {
        JSONObject(Gson().toJson(this))
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Any.toJSONString(): String? {
    return try {
        Gson().toJson(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun <T : Any> JSONObject.toObject(obj: Class<T>): T {
    return Gson().fromJson("$this", obj) as T
}

fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        true
    }
}

private fun doToast(context: Context, message: String, length: Int) {
    if (context is Activity) {
        if (!context.isFinishing && !context.isDestroyed) {
            Toast.makeText(context, message, length).show()
        }
    } else {
        Toast.makeText(context, message, length).show()
    }
}

fun Context.log(log: Any) {
    Log.e(this::class.java.simpleName, "$log")
}
