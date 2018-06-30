package guideme.volunteers.ui.fragments.genericlist

interface GenericItem <out T>{
    val title : String
    val subTitle : String
    val imageUrl : String
    val data : T
}
class GenericItemImpl<T>(override val title: String, override val subTitle: String, override val imageUrl: String, override val data: T) : GenericItem<T>
