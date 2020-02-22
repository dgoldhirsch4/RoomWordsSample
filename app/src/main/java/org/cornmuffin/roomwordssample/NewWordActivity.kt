package org.cornmuffin.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        edit_word?.let {
            button_save.setOnClickListener { _ ->
                val replyIntent = Intent()
                if (it.text.isEmpty()) {
                    setResult(RESULT_CANCELED, replyIntent)
                } else {
                    val word = it.text.toString()
                    replyIntent.putExtra(EXTRA_REPLY, word)
                    setResult(RESULT_OK, replyIntent)
                }
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
