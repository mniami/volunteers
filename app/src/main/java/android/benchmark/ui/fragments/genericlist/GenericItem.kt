package android.benchmark.ui.fragments.genericlist

interface GenericItem {
    val title : String
    val subTitle : String
    val imageUrl : String
}
class GenericItemImpl(override val title: String, override val subTitle: String, override val imageUrl: String) : GenericItem
