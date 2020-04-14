package net.syarihu.android.playground.activityresultapisample

import android.app.Activity
import android.os.Bundle
import net.syarihu.android.playground.activityresultapisample.databinding.ActivitySubBinding

class SubActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySubBinding.inflate(layoutInflater).apply {
            button.setOnClickListener {
                setResult(RESULT_OK)
                finish()
            }
        }
        setContentView(binding.root)
    }
}