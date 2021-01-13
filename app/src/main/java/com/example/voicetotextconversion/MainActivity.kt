package com.example.voicetotextconversion

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView;
    lateinit var button: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView=findViewById(R.id.tv);
        button=findViewById(R.id.btn);

        button.setOnClickListener(View.OnClickListener {

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            );
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");

            startActivityForResult(intent, 0)



        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            0 -> {
                if (resultCode === RESULT_OK && null != attr.data) {
                    val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result != null) {
                        Log.i("srt", result.size.toString())
                    };
                    textView.setText(result?.get(0))
                }
            }
        }

    }
}