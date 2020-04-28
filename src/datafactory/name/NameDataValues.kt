package datafactory.name

interface NameDataValues {
    fun getFirstNames(): Array<String>
    fun getLastNames(): Array<String>
    fun getPrefixes(): Array<String>
    fun getSuffixes(): Array<String>?
}