package com.saov.myqrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    var edtTexto : EditText? = null
    var btnGerar : Button? = null
    var btnShare : Button? = null
    var imgQR : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponentes()

        btnGerar!!.setOnClickListener{
            if(TextUtils.isEmpty(edtTexto!!.text.toString())){
                edtTexto!!.error = "*"
                edtTexto!!.requestFocus()
            }else{
                gerarQRCode(edtTexto!!.text.toString())
            }
        }

        btnShare!!.setOnClickListener {

        }
    }

    private fun gerarQRCode(conteudo: String) {
        val qrCode = QRCodeWriter()
        try {
            val bitMatrix = qrCode.encode(conteudo,BarcodeFormat.QR_CODE,200,200)
            val tamanho = bitMatrix.width
            val altura = bitMatrix.height

            val bitmap = Bitmap.createBitmap(tamanho,altura,Bitmap.Config.RGB_565)

            for(x in 0 until tamanho){
                for(y in 0 until altura){
                    bitmap.setPixel(x,y,if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }

            imgQR!!.setImageBitmap(bitmap)

        }catch (e:WriterException){

        }
    }

    private fun initComponentes() {
        edtTexto = findViewById(R.id.idEdtTexto)
        btnGerar = findViewById(R.id.idBtnGerar)
        btnShare = findViewById(R.id.idBtnShare)
        imgQR = findViewById(R.id.idImvQR)
    }
}