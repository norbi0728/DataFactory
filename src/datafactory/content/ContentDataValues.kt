package datafactory.content

interface ContentDataValues {
    fun getWords(): Array<String>
    fun getBusinessTypes(): Array<String>
    fun getEmailHosts(): Array<String>
    fun getTlds(): Array<String>
}