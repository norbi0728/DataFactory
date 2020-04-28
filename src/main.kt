import datafactory.DataFactory

fun main() {
    DataFactory("HUN").let { dataFactory ->
        repeat(10){
            dataFactory.randomWord.let { email ->
                println(email)
            }
        }
    }
}