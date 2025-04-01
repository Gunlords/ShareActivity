package edu.temple.funwithintents

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This view contains the text to share
        val editText = findViewById<EditText>(R.id.editTextText)

        // When the user clicks this button, share the text if not empty
        findViewById<ImageButton>(R.id.shareImageButton).setOnClickListener {
            val textToShare = editText.text.toString()
            if (textToShare.isNotBlank()) {
                // Create an implicit intent with ACTION_SEND
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, textToShare)
                    type = "text/plain"
                }

                if (shareIntent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(shareIntent, "Share text using"))
                } else {
                    Toast.makeText(this, "No app available to share text.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter some text to share.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
