package app.andika.dindinassignment.model

data class Food(
    val id: Long,
    val name: String,
    val description: String,
    val imageURL: String,
    val sizeInfo: String,
    val price: Int,
    val type: String
)