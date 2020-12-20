package app.andika.dindinassignment.model

data class BoughtItem(
    val id: Long,
    val foodId: Long,
    val foodName: String,
    val foodImageURL: String,
    val foodPrice: Int,
    var total: Int,
    val type: String,
    val isDeleted: Boolean
)