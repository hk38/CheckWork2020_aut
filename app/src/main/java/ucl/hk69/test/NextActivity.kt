package ucl.hk69.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        findViewById<Button>(R.id.button).setOnClickListener { finish() }
    }
}