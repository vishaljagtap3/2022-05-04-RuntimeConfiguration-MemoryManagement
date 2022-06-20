package com.bitcode.runtimeconfigchanges

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.bitcode.runtimeconfigchanges.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mt("onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(lastCustomNonConfigurationInstance != null ) {
            mt("Got the data back...")
            var data = (lastCustomNonConfigurationInstance as Data)
            binding.txtInfo.text = data.data + " " + data.backUptime
        }

        binding.imgInfo.setImageResource(R.drawable.flag)

        binding.btnSetInfo.setOnClickListener {
            binding.txtInfo.text = binding.edtInfo.text.toString()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mt("onConfigurationChanged")
    }

    class Data(var data : String, var backUptime : Long)

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        mt("onRetainCustomNonConfigurationInstance")
        return Data(
            binding.txtInfo.text.toString(),
            System.currentTimeMillis()
        )
    }

    override fun onDestroy() {
        mt("onDestroy")
        super.onDestroy()
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}