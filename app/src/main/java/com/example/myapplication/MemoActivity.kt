package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.DB.DBLoader
import com.example.myapplication.model.Memo

class MemoActivity : AppCompatActivity() {

    private lateinit var edit_title: EditText
    private lateinit var edit_memo: EditText
    private var item: Memo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        supportActionBar!!.setTitle("메모")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        edit_title = findViewById(R.id.edit_title)
        edit_memo = findViewById(R.id.edit_memo)

        val intent = intent.extras
        if (intent != null) {
            item = intent.getSerializable("item") as Memo
            edit_memo.setText(item?.memo)
            edit_title.setText(item?.title)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_memo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_save -> {
                val title = edit_title.text.toString()
                val memo = edit_memo.text.toString()
                if (!memo.equals("")) {
                    if (this.item != null) {

                    } else {
                        DBLoader(applicationContext).save(title, memo)
                        finish()
                    }

                    }



            }
            }

            return super.onOptionsItemSelected(item)
        }
    }


