package generator

import model.ItemPrice

object ItemPriceGenerator {
    fun getItems(): ArrayList<ItemPrice> {
        return arrayListOf<ItemPrice>().apply {
            add(ItemPrice("Xiaomi Mi 11", 9999000))
            add(ItemPrice("TV Xiaomi Mi 4A", 2100000))
            add(ItemPrice("Samsung S21", 18000000))
            add(ItemPrice("Samsung A52", 5500000))
            add(ItemPrice("Powerbank Anker 10000 mAh", 300000))
        }
    }
}