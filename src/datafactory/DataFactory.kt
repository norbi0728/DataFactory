package datafactory

import datafactory.address.AddressDataValues
import datafactory.address.DefaultAddressDataValues
import datafactory.content.ContentDataValues
import datafactory.content.DefaultContentDataValues
import datafactory.name.DefaultNameDataValues
import datafactory.name.NameDataValues
import java.util.*


class DataFactory {
    private var lang = ""

    private lateinit var nameDataValues: NameDataValues
    private lateinit var addressDataValues: AddressDataValues
    private lateinit var contentDataValues: ContentDataValues

    constructor(lang: String) {
        if(!(lang == "ENG" || lang == "HUN"))
            this.lang = "ENG"
        else
            this.lang = lang

        this.nameDataValues = DefaultNameDataValues(this.lang)
        this.addressDataValues = DefaultAddressDataValues(this.lang)
        this.contentDataValues = DefaultContentDataValues(this.lang)
    }


    fun <T> getItem(items: List<T>): T? {
        return getItem(items, 100, null)
    }


    fun <T> getItem(items: List<T>, probability: Int): T? {
        return getItem(items, probability, null)
    }


    fun <T> getItem(items: List<T>?, probability: Int, defaultItem: T?): T? {
        requireNotNull(items) { "Item list cannot be null" }
        require(!items.isEmpty()) { "Item list cannot be empty" }
        return if (chance(probability)) items[random.nextInt(items.size)] else defaultItem
    }


    fun <T> getItem(items: Array<T>?): T? {
        return getItem(items, 100, null)
    }


    fun <T> getItem(items: Array<T>?, probability: Int): T? {
        return getItem(items, probability, null)
    }


    fun <T> getItem(items: Array<T>?, probability: Int, defaultItem: T?): T? {
        requireNotNull(items) { "Item array cannot be null" }
        require(items.size != 0) { "Item array cannot be empty" }
        return if (chance(probability)) items[random.nextInt(items.size)] else defaultItem
    }


    val firstName: String?
        get() = getItem(nameDataValues.getFirstNames())

//    return if (test < 50) {
//        "Apt #" + 100 + random.nextInt(1000)
//    } else {
//        "Suite #" + 100 + random.nextInt(1000)
//    }
    val name: String
        get() {
            return if (lang.equals("ENG")){
                (getItem(nameDataValues.getFirstNames()).toString() + " "
                        + getItem(nameDataValues.getLastNames()))
            } else {
                (getItem(nameDataValues.getLastNames()).toString() + " "
                        + getItem(nameDataValues.getFirstNames()))
            }

        }


    val lastName: String?
        get() = getItem(nameDataValues.getLastNames())


    val streetName: String?
        get() = getItem(addressDataValues.getStreetNames())


    val streetSuffix: String?
        get() = getItem(addressDataValues.getAddressSuffixes())


    val city: String?
        get() = getItem(addressDataValues.getCities())


    val address: String
        get() {
            val num = 404 + random.nextInt(1400)
            var address = ""
            if (lang.equals("ENG"))
                address = "$num $streetName $streetSuffix"
            else if(lang.equals("HUN"))
                address = "$streetName $streetSuffix $num"
            return address
        }


    fun getAddressLine2(probability: Int): String {
        return getAddressLine2(probability, null)
    }


    fun getAddressLine2(probability: Int, defaultValue: String?): String {
        return (if (chance(probability)) addressLine2 else defaultValue)!!
    }


    val addressLine2: String
        get() {
            val test = random.nextInt(100)
            return if (test < 50) {
                "Apt #" + 100 + random.nextInt(1000)
            } else {
                "Suite #" + 100 + random.nextInt(1000)
            }
        }


    val birthDate: Date
        get() {
            val base = Date(0)
            return getDate(base, -365 * 15, 365 * 15)
        }


    val number: Int
        get() = getNumberBetween(Int.MIN_VALUE, Int.MAX_VALUE)


    fun getNumberUpTo(max: Int): Int {
        return getNumberBetween(0, max)
    }

    private fun getNumberBetween(min: Int, max: Int): Int {
        require(max >= min) {
            String.format(
                "Minimum must be less than minimum (min=%d, max=%d)", min,
                max
            )
        }
        return min + random.nextInt(max - min)
    }

    fun getDate(year: Int, month: Int, day: Int): Date {
        val cal = Calendar.getInstance()
        cal.clear()
        cal[year, month - 1, day, 0, 0] = 0
        return cal.time
    }

    private fun getDate(baseDate: Date?, minDaysFromDate: Int, maxDaysFromDate: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = baseDate
        val diff = (minDaysFromDate
                + random.nextInt(maxDaysFromDate - minDaysFromDate))
        cal.add(Calendar.DATE, diff)
        return cal.time
    }

    fun getDateBetween(minDate: Date, maxDate: Date): Date {
        // this can break if seconds is an int
        var seconds = (maxDate.time - minDate.time) / 1000
        seconds = (random.nextDouble() * seconds).toLong()
        val result = Date()
        result.time = minDate.time + seconds * 1000
        return result
    }


    fun getRandomText(length: Int): String {
        return getRandomText(length, length)
    }

    private fun getRandomText(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)
        val sb = StringBuilder(maxLength)
        var length = minLength
        if (maxLength != minLength) {
            length += random.nextInt(maxLength - minLength)
        }
        while (length > 0) {
            if (sb.isNotEmpty()) {
                sb.append(" ")
                length--
            }
            val word = getRandomWord(length)
            sb.append(word)
            length -= word.length
        }
        return sb.toString()
    }

    private fun validateMinMaxParams(minLength: Int, maxLength: Int) {
        require(minLength >= 0) { "Minimum length must be a non-negative number" }
        require(maxLength >= 0) { "Maximum length must be a non-negative number" }
        require(maxLength >= minLength) {
            String.format(
                "Minimum length must be less than maximum length (min=%d, max=%d)",
                minLength, maxLength
            )
        }
    }

    val randomChar: Char
        get() = (random.nextInt(26) + 'a'.toInt()).toChar()

    fun getRandomChars(length: Int): String {
        return getRandomChars(length, length)
    }

    fun getRandomChars(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)
        val sb = StringBuilder(maxLength)
        var length = minLength
        if (maxLength != minLength) {
            length += random.nextInt(maxLength - minLength)
        }
        while (length > 0) {
            sb.append(randomChar)
            length--
        }
        return sb.toString()
    }

    val randomWord: String?
        get() = getItem(contentDataValues.getWords())


    fun getRandomWord(length: Int): String {
        return getRandomWord(length, length)
    }

    fun getRandomWord(length: Int, exactLength: Boolean): String {
        return if (exactLength) {
            getRandomWord(length, length)
        } else getRandomWord(0, length)
    }

    fun getRandomWord(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)

        // special case if we need a single char
        if (maxLength == 1) {
            return if (chance(50)) {
                "a"
            } else "I"
        }
        val value: String? = null

        // start from random pos and find the first word of the right size
        val words = contentDataValues.getWords()
        val pos = random.nextInt(words.size)
        for (i in words.indices) {
            val idx = (i + pos) % words.size
            val test = words[idx]
            if (test.length >= minLength && test.length <= maxLength) {
                return test
            }
        }
        // we haven't a word for this length so generate one
        return getRandomChars(minLength, maxLength)
    }

    fun getSuffix(chance: Int): String? {
        return getItem(nameDataValues.getSuffixes(), chance)
    }


    fun getPrefix(chance: Int): String? {
        return getItem(nameDataValues.getPrefixes(), chance)
    }

    fun getNumberText(digits: Int): String {
        var result = ""
        for (i in 0 until digits) {
            result = result + random.nextInt(10)
        }
        return result
    }

    val businessName: String
        get() = city + " " + getItem(contentDataValues.getBusinessTypes())// 2 words// name and initial


    val emailAddress: String
        get() {
            val test = random.nextInt(100)
            var email = ""
            email = if (test < 50) {
                // name and initial
                (firstName?.get(0)?.toString() ?: "") + lastName
            } else {
                // 2 words
                (getItem(contentDataValues.getWords())
                        + getItem(contentDataValues.getWords()))
            }
            if (random.nextInt(100) > 80) {
                email = email + random.nextInt(100)
            }
            email = (email + "@" + getItem(contentDataValues.getEmailHosts()) + "."
                    + getItem(contentDataValues.getTlds()))
            return email.toLowerCase()
        }

    fun chance(chance: Int): Boolean {
        return random.nextInt(100) < chance
    }


    fun randomize(seed: Int) {
        random = Random(seed.toLong())
    }

    fun setAddressDataValues(addressDataValues: AddressDataValues) {
        this.addressDataValues = addressDataValues
    }

    fun setContentDataValues(contentDataValues: ContentDataValues) {
        this.contentDataValues = contentDataValues
    }

    companion object {
        private var random = Random(93285)
    }
}