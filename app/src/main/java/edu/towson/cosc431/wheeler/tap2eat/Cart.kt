package edu.towson.cosc431.wheeler.tap2eat

class Cart {
    companion object : ICart{
        private var cart: MutableList<menuItem> = mutableListOf()

        override fun putInCart(item: menuItem): List<menuItem> {
            cart.add(item)
            return cart.toList()
        }

        override fun deleteFromCart(position: Int): List<menuItem>  {
            cart.removeAt(position)
            return cart.toList()
        }

        override fun getCartItmes(): List<menuItem> {
            return cart.toList()
        }

        override fun getNumberOfItems(): Int {
            return cart.size
        }

        override fun getItem(position: Int): menuItem {
            return cart[position].copy()
        }
    }
}


interface ICart {
    fun putInCart(item: menuItem): List<menuItem>
    fun deleteFromCart(position: Int): List<menuItem>
    fun getCartItmes(): List<menuItem>
    fun getNumberOfItems() : Int
    fun getItem(position: Int): menuItem
}