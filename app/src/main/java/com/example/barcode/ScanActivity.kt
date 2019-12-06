package com.example.barcode

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScanActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {

    private var scannerView: ZBarScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZBarScannerView(this)
        setContentView(scannerView)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        scannerView?.setResultHandler(this)
        scannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView?.stopCamera()
    }

    override fun handleResult(p0: Result?) {

        Log.d("XXX","${p0?.contents}, ${p0?.barcodeFormat?.name}")
        val text = p0?.contents.toString()
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent.putExtra("TEXT",text))
        finish()
    }
}
