import generator.ItemPriceGenerator
import model.ItemPrice
import kotlin.math.absoluteValue
import kotlin.system.exitProcess

class App {
    private val items = ItemPriceGenerator.getItems()
    private lateinit var selectedItemPrice : ItemPrice
    private var inputPlayerOne = 0
    private var inputPlayerTwo = 0

    companion object {
        const val PRICE_EQUAL = 0
        const val PRICE_MORE_THAN = 1
        const val PRICE_LESS_THAN = 2

        @JvmStatic
        fun main(args: Array<String>) {
            App().runGame()
        }
    }

    fun runGame() {
        println("=========================================")
        println("Game Tebak Harga")
        println("=========================================")
        println("Start Game ? (Y/N)")
        if (readLine().equals("Y", ignoreCase = true)) {
            startGame()
        } else {
            println("Game Closed")
            exitProcess(0)
        }
    }

    private fun startGame() {
        //mengambil salah satu item dari list items dengan index
        // 0 in items.size , contoh items.size = 5
        // 0,1,2,3,4
        selectedItemPrice = items[(0 until items.size).random()]
        //print info name item
        printInfoItem(selectedItemPrice)
        //input user price
        inputPriceUser()
        //melakukan pengecekan nilai price
        checkWinner(
            checkPrice(inputPlayerOne),
            checkPrice(inputPlayerTwo)
        )
    }

    private fun inputPriceUser() {
        println("Masukkan Harga Permain Pertama = ")
        readLine()?.toInt()?.let {
            inputPlayerOne = it
        }
        println("Masukkan Harga Permain Kedua = ")
        readLine()?.toInt()?.let {
            inputPlayerTwo = it
        }
    }

    private fun printInfoItem(selectedItemPrice: ItemPrice) {
        println("******************************")
        println("Tebaklah harga dari = ${selectedItemPrice.itemName}")
        println("******************************")
    }

    private fun checkPrice(userInput: Int): Int {
        return when {
            userInput == selectedItemPrice.price -> {
                PRICE_EQUAL
            }
            userInput > selectedItemPrice.price -> {
                PRICE_MORE_THAN
            }
            else -> {
                PRICE_LESS_THAN
            }
        }
    }
    /*
    * inputPlayerOne -> checkPrice(inputPlayerOne) -> integer value of PRICE_EQUAL,PRICE_MORE_THAN,PRICE_LESS_THAN
    * inputPlayerTwo -> checkPrice(inputPlayerTwo) -> integer value of PRICE_EQUAL,PRICE_MORE_THAN,PRICE_LESS_THAN
    * */
    private fun checkWinner(resultPlayerOne: Int, resultPlayerTwo: Int) {
        println("=====================================")
        println("Harga untuk barang ${selectedItemPrice.itemName}, adalah ${selectedItemPrice.price}")
        println("Hasilnya adalah....")
        println("=====================================")
        if (resultPlayerOne == PRICE_EQUAL) {
            if (resultPlayerTwo == PRICE_EQUAL) {
                //both result is PRICE_EQUAL , means gak ada yang menang
                println("Keduanya Benar, Tidak ada yang Menang")
            } else {
                //player two, PRICE_MORE_THAN atau PRICE_LESS_THAN
                println("Player 1 Menang")
            }
        } else {
            //result player one , PRICE_MORE_THAN atau PRICE_LESS_THAN
            if (resultPlayerTwo == PRICE_EQUAL) {
                println("Player 2 Menang")
            } else {
                // 3000 - 5000 = -2000.absoluteValue ->> 2000
                val diffOne = (inputPlayerOne - selectedItemPrice.price).absoluteValue
                val diffTwo = (inputPlayerTwo - selectedItemPrice.price).absoluteValue
                when {
                    (diffOne < diffTwo) -> {
                        println("Player 1 Mendekati Benar, Player 1 Menang")
                    }
                    (diffOne > diffTwo) -> {
                        println("Player 2 Mendekati Benar, Player 2 Menang")
                    }
                    else -> {
                        //diffOne == diffTwo
                        println("Keduanya hampir benar, Tidak ada yang menang")
                    }
                }
            }
        }
    }

}