package net.syarihu.android.playground.activityresultapisample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import net.syarihu.android.playground.activityresultapisample.model.AppPackage

private const val EXTRA_ID = "id"
private const val EXTRA_NAME = "name"
private const val EXTRA_PACKAGE = "package"

class AppDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_detail)
        val appPackage = AppPackage(1, "Sample App", "com.sample.app")
        val intent = Intent().apply {
            putExtra(EXTRA_ID, appPackage.id)
            putExtra(EXTRA_NAME, appPackage.appName)
            putExtra(EXTRA_PACKAGE, appPackage.packageName)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}

class AppDetailActivityResultContract : ActivityResultContract<Int, AppPackage>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        // 引数を渡せるIntentを作成
        return Intent(context, AppDetailActivity::class.java).apply {
            putExtra("id", input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): AppPackage? {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            // 結果のIntentからdata classを生成
            val id = intent.getIntExtra(EXTRA_ID, 0)
            val name = intent.getStringExtra(EXTRA_NAME)
            val packageName = intent.getStringExtra(EXTRA_PACKAGE)
            return AppPackage(id, name, packageName)
        }
        return null
    }
}

