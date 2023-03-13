package com.example.androidlab2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlab2.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    private fun discriminant(a: Double, b: Double, c: Double) = b * b - 4 * a * c

    private fun quadraticEquation(a: Double, b: Double, c: Double): String {
        val discriminant = discriminant(a, b, c)
        if (discriminant < 0) {
            return "Корней нет!"
        } else if (discriminant == 0.0 && a != 0.0 && b != 0.0) {
            return "Т.к. D = 0\nX1 = X2 = ${-b / 2 * a}"
        } else {
            return if (a == 0.0 && b != 0.0) {
                "Корень один, т.к. уравнение линейное\nX1 = ${-c / b}"
            } else if (a == 0.0 && c == 0.0) {
                "Уравнение не квадратное, корни могут принимать любое значение"
            } else if (a == 0.0) {
                "Выражение не является уравнением"
            } else {
                "X1 = ${(-b + sqrt(discriminant)) / (2 * a)} \n" +
                        "X2 = ${(-b - sqrt(discriminant)) / (2 * a)}"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.button.setOnClickListener {
            try {
                val a = bindingClass.text1.text.toString().toDouble()
                val b = bindingClass.text2.text.toString().toDouble()
                val c = bindingClass.text3.text.toString().toDouble()
                val decision = quadraticEquation(a, b, c)
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Решение")
                builder.setMessage(decision)
                builder.setPositiveButton("Ок") { _, _ ->
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }
                builder.show()
            } catch (e: Exception) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Ошибка")
                builder.setMessage("Проверьте правильность ввода")
                builder.setPositiveButton("Ок") { _, _ ->
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }
                builder.show()
            }
        }
    }
}