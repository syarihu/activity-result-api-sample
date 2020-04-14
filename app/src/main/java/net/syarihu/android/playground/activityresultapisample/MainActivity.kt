package net.syarihu.android.playground.activityresultapisample

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import net.syarihu.android.playground.activityresultapisample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 新しく追加された、prepareCallに引数としてStartActivityForResultを渡す
    private val launcher =
        prepareCall(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            // コールバックとしてActivityResultを受け取ることができる
            // ここが呼び出し先のActivityが閉じたときに呼び出される
            if (activityResult.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "ok", Toast.LENGTH_LONG).show()
            }
        }
    private val requestPermissionLauncher =
        prepareCall(ActivityResultContracts.RequestPermission()) {
            if (it) {
                takePictureLauncher.launch(null)
                // 権限が付与されたらtrue
                Toast.makeText(this, "granted", Toast.LENGTH_LONG).show()
            }
        }
    private val takePictureLauncher = prepareCall(ActivityResultContracts.TakePicture()) {
        Toast.makeText(this, "Bitmap: ${it != null}", Toast.LENGTH_LONG).show()
    }
    private val getContentLauncher =
        prepareCall(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Toast.makeText(this, "uri: ${uri != null}", Toast.LENGTH_LONG).show()
        }
    private val getAppPackageLauncher = prepareCall(AppDetailActivityResultContract()) {
        Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            button.setOnClickListener {
                launcher.launch(Intent(this@MainActivity, SubActivity::class.java))
            }
            permission.setOnClickListener {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            getContent.setOnClickListener {
                getContentLauncher.launch("image/*")
            }
            appPackage.setOnClickListener {
                getAppPackageLauncher.launch(1)
            }
        }
        setContentView(binding.root)
    }
}
