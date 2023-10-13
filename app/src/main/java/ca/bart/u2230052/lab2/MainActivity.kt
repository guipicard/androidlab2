package ca.bart.u2230052.lab2

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import ca.bart.u2230052.lab2.databinding.ActivityMainBinding
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.core.text.set
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Parcelize
@Serializable
data class Model(var buttonPressed:Boolean) : Parcelable

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val KEY_STATE = "KEY_STATE"
        const val TEXT_STATE = "TEXT_STATE"
        const val EDIT_STATE = "EDIT_STATE"
        const val PREFS_NAME = "PREFS_NAME"
        var text_default = R.string.text_field
        var text_value = ""
        var edit_value = ""
    }

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var model = Model(false)

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate($savedInstanceState)")
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            model.buttonPressed = true
            text_value = binding.editText.text.toString()
            edit_value = ""
            refresh()
        }
    }

    fun refresh() {
        if (model.buttonPressed)
            binding.textView.text = text_value
            binding.editText.setText(edit_value)
            return
        binding.textView.setText(text_default)
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart()")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(TAG, "onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState($outState)")
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, model)
        outState.putString(TEXT_STATE, binding.textView.text.toString())
        outState.putString(EDIT_STATE, binding.editText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState($savedInstanceState)")
        super.onRestoreInstanceState(savedInstanceState)

        val model =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                savedInstanceState.getParcelable(KEY_STATE, Model::class.java)
            else
                savedInstanceState.getParcelable(KEY_STATE, Model::class.java)
        text_value = savedInstanceState.getString(TEXT_STATE).toString()
        edit_value = savedInstanceState.getString(EDIT_STATE).toString()
        model?.let {
            this.model = it
            refresh()
        }
    }
}