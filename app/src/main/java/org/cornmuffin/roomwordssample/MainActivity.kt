package org.cornmuffin.roomwordssample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.cornmuffin.roomwordssample.NewWordActivity.Companion.EXTRA_REPLY

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter(this)

        recyclerview?.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        setSupportActionBar(toolbar)

        fab?.let {
            it.setOnClickListener {
                startActivityForResult(
                    Intent(this@MainActivity, NewWordActivity::class.java),
                    NEW_WORD_ACTIVITY_REQUEST_CODE
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.getStringExtra(EXTRA_REPLY)?.let {
                wordViewModel.insert(Word(it))
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }
}
