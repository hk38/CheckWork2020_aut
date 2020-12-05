package ucl.hk69.test

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private var count: Float = 0f
    private val buttonList:MutableList<Button> = mutableListOf()
    private val valueList:MutableList<Float> = mutableListOf()
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Viewの宣言
        textView = findViewById(R.id.text_view)
        buttonList.add(findViewById(R.id.buttonP05))
        valueList.add(0.5f)
        buttonList.add(findViewById(R.id.buttonP1))
        valueList.add(1f)
        buttonList.add(findViewById(R.id.buttonP10))
        valueList.add(10f)
        buttonList.add(findViewById(R.id.buttonM05))
        valueList.add(-0.5f)
        buttonList.add(findViewById(R.id.buttonM1))
        valueList.add(-1f)
        buttonList.add(findViewById(R.id.buttonM10))
        valueList.add(-10f)

        // 保存した値の取り出し
        setCount(getSharedPreferences("store", MODE_PRIVATE).getFloat("count", 0f), null)

        // ボタンを押したときの動作を記述
        for(i in 0 until buttonList.size){
            buttonList[i].setOnClickListener {
                setCount(valueList[i], buttonList[i])
            }
        }
    }

    // アプリ終了時
    override fun onDestroy() {
        // カウント値を保存
        getSharedPreferences("store", MODE_PRIVATE).edit {
            putFloat("count", count)
            apply()
        }
        super.onDestroy()
    }

    private fun setCount(value: Float, button: View?){
        // 数値増加
        count += value
        // 数値表示要TextViewが無ければ取得
        if(textView == null) textView = findViewById(R.id.text_view)
        // 増加した数値を代入
        textView?.text = "$count"

        // 全ボタンに背景色設定
        for(item in buttonList) item.setBackgroundColor(Color.parseColor("#9079ad"))
        // 押したボタンのみ背景色を設定
        button?.setBackgroundColor(Color.parseColor("#47885e"))

        // 値に応じて色変更
        when(count % 10){
            0.0f -> textView?.setTextColor(Color.BLACK)
            5.0f -> textView?.setTextColor(Color.BLACK)
            1.0f -> textView?.setTextColor(Color.RED)
            6.0f -> textView?.setTextColor(Color.RED)
            2.0f -> textView?.setTextColor(Color.BLUE)
            7.0f -> textView?.setTextColor(Color.BLUE)
            3.0f -> textView?.setTextColor(Color.GREEN)
            8.0f -> textView?.setTextColor(Color.GREEN)
            4.0f -> textView?.setTextColor(Color.GRAY)
            9.0f -> textView?.setTextColor(Color.GRAY)
        }

        // 100以上ならIntent
        if(count > 100) {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }
    }
}