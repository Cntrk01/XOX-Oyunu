package com.example.xoxoyunu

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.xoxoyunu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding

    var player1=0
    var player2=1
    var activePlayer=player1
    lateinit var filledPos:IntArray
    var gameActive=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        filledPos= intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        buttonClicked()
    }
    private fun buttonClicked(){
        binding.b0.setOnClickListener(this)
        binding.b1.setOnClickListener(this)
        binding.b2.setOnClickListener(this)
        binding.b3.setOnClickListener(this)
        binding.b4.setOnClickListener(this)
        binding.b5.setOnClickListener(this)
        binding.b6.setOnClickListener(this)
        binding.b7.setOnClickListener(this)
        binding.b8.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
//        if(!gameActive){ //false oldugu surece calısacak
//            return
//        }
        var clickedItem=findViewById<Button>(v!!.id)
        var clickedTag=Integer.parseInt(clickedItem.tag.toString())//taglari aldım

        if(filledPos[clickedTag]!=-1){//tag -1 değilse devam et
            return
        }else{
            filledPos[clickedTag]=activePlayer//kullanıcı hangi tag'a tıklarsa filledPos içerisinde oraya jkullanıcı yerleşiyor
        }
        if(activePlayer==player1){
            clickedItem.setText("0")
            activePlayer=player2
            binding.textView.setText("Player 2")
            clickedItem.setTextColor(Color.BLACK)
            clickedItem.backgroundTintList=getColorStateList(R.color.teal_200)
        }else{
            clickedItem.setText("X")
            activePlayer=player1
            binding.textView.setText("Player 1")
            clickedItem.setTextColor(Color.WHITE)
            clickedItem.backgroundTintList=getColorStateList(R.color.green)
        }

        checkForWin()
    }

    private fun checkForWin(){
        var winPos= arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),
            intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))

        for (i in 0 until winPos.size){
            var val0=winPos[i][0]
            var val1=winPos[i][1]
            var val2=winPos[i][2]

            if(filledPos[val0]==filledPos[val1] && filledPos[val1]==filledPos[val2]){
               if(filledPos[val0] != -1){ //eğer -1 yerine player degeri atanmışsa kontrolunu sagladım
                   gameActive=false
                   if(filledPos[val0]==player1){
                       showMessage("PLAYER 1 WIN")
//                       binding.textView.setText("Player 1 WIN !!")
                   }else{
                       showMessage("PLAYER 2 WIN !!")
                   }
                   return
               }
            }
        }
        var count=0
        for (i in 0 until filledPos.size){
            if(filledPos[i] ==-1){ //tıklanan buton -1 e eşitse 1 arttırarak 0 a eşitledi
                count++
            }
        }
        if(count==0){ //eğer hepsi 0 sa berabere bitti
            showMessage("It is Draw")
            return
        }
    }

    private fun showMessage(s:String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart Game", DialogInterface.OnClickListener { dialog, which ->
                restartGame()
            }).setCancelable(false) // alerte tıklamak zorunda bırakıyor.Pencere dışında biryere tıklayamazsın !
            .show()
    }

    private fun restartGame() {
        filledPos= intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer=player1
        gameActive=true
        binding.textView.setText("Player 1")
        binding.b0.setText("")
        binding.b1.setText("")
        binding.b2.setText("")
        binding.b3.setText("")
        binding.b4.setText("")
        binding.b5.setText("")
        binding.b6.setText("")
        binding.b7.setText("")
        binding.b8.setText("")

        binding.b0.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b1.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b2.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b3.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b4.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b5.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b6.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b7.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        binding.b8.backgroundTintList=getColorStateList(com.google.android.material.R.color.design_default_color_primary)

    }
}