package com.example.minami.movebox

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var placeHolder = arrayOf("1","2","3","4","5","6","7","8","")
    private val checker  = arrayOf("1","2","3","4","5","6","7","8","")
    private var count:  Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        count = puzzle.childCount

        start()
    }

    private fun shuffle() {
        val rand = Random()
        for (i in 0 until count) {
            var id = rand.nextInt(count)
            var temp = placeHolder[i]
            placeHolder[i] = placeHolder[id]
            placeHolder[id] = temp
        }
    }

    override fun onClick(v: View) {
        val panel: Button = v as Button
        val position = panel.tag as Int
        slidePanels(position, getPositionOfZero())
        if (resultCheck()){
            Toast.makeText(this, "Congrats!!", Toast.LENGTH_SHORT).show()
            //start()
        }
    }

    private fun getPositionOfZero(): Int {
        var position = 0
        for (i in 0 until count) {
            position = if (placeHolder[i] == "") i else continue
        }
        return position
    }

    private fun isUpMovable(tag: Int): Boolean {
        return ((tag in 3..8) && (placeHolder[tag - 3] == ""))
    }

    private fun isDownMovable(tag: Int): Boolean {
        return ((tag in 0..5) && (placeHolder[tag + 3] == ""))
    }

    private fun isRightMovable(tag: Int): Boolean {
        return ((tag != 2 && tag != 5 && tag != 8) && (placeHolder[tag + 1] == ""))
    }

    private fun isLeftMovable(tag: Int): Boolean {
        return ((tag != 0 && tag != 3 && tag != 6) && (placeHolder[tag - 1] == ""))
    }

    private fun isMovable(tag: Int): Boolean {
        return (isUpMovable(tag) || isDownMovable(tag) || isRightMovable(tag) || isLeftMovable(tag))
    }

    private fun slidePanels(tag: Int, zero: Int) {
        if (isMovable(tag)) {
            // swap array
            var temp = placeHolder[tag]
            placeHolder[tag] = placeHolder[zero]
            placeHolder[zero] = temp
            // swap text
            var btn1: Button = puzzle.getChildAt(tag) as Button
            var btn2: Button = puzzle.getChildAt(zero) as Button
            btn1.text = placeHolder[tag]
            btn2.text = placeHolder[zero]
        }
    }
    private fun resultCheck(): Boolean {
        for (i in 0 until count) {
            if (placeHolder[i] != checker[i]) {
                return false
            }
        }
        return true
    }

    private fun start() {
        shuffle()
        for (i in 0 until count) {
            var child = puzzle.getChildAt(i) as Button
            child.text = placeHolder[i]
            child.tag = i
            child.setOnClickListener(this)
        }
    }
}
