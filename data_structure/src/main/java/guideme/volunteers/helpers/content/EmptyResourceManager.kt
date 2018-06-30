package guideme.volunteers.helpers.content

class EmptyResourceManager : ResourceManager {
    override fun getStringArray(id: Resources.Array): Array<String> {
        return arrayOf()
    }

    override fun getString(id: Resources): String {
        return ""
    }
}