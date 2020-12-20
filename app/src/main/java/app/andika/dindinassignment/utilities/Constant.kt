package app.andika.dindinassignment.utilities

import java.util.concurrent.atomic.AtomicInteger

public val PIZZA = "Pizza"
public val SUSHI = "Sushi"
public val DRINKS = "Drinks"
public val CART = "Cart"
public val ORDER = "Order"
public val INFORMATION = "Info"
public val USD = " usd"
public var ID_GENERATOR: AtomicInteger = AtomicInteger((0..10000).random())

public const val SLIDER_START_DELAY: Long = 1000 //delay time first run slider
public const val SLIDER_DURATION: Long = 3000 //Period for slider