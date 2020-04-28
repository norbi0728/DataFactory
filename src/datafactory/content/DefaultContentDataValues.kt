package datafactory.content

class DefaultContentDataValues : ContentDataValues {
    private var lang = "ENG"

    constructor(lang: String) {
        this.lang = lang
    }

    override fun getWords(): Array<String> {
        var toReturn = arrayOf("")
        if (lang.equals("HUN"))
            toReturn = hunWords
        else if(lang.equals("ENG"))
            toReturn = engWords
        return toReturn
    }

    override fun getBusinessTypes(): Array<String> {
        var toReturn = arrayOf("")
        if (lang.equals("HUN"))
            toReturn = hunBusinessTypes
        else if(lang.equals("ENG"))
            toReturn = engBusinessTypes
        return toReturn
    }

    override fun getEmailHosts(): Array<String> {
        return emailHosts
    }

    override fun getTlds(): Array<String> {
        return tlds
    }

    var hunWords = arrayOf("rács", "dob", "labda", "kanapé", "szó", "sebesség",
            "szabadság", "teniszütő", "jó", "semmi", "valami", "lenni", "talán", "kéne",
            "te", "én", "számok", "írott", "jött", "is", "sok", "kevés", "húz", "tétlen",
            "álom", "kívánság", "megment", "szörny", "amíg", "épít", "híd", "kérdés",
            "válasz", "napok", "sziget", "hajó", "király", "keresztül", "mások", "ember",
            "nő", "mágnes", "belépés", "nagyon", "monitor", "lándzsa", "van")

    var hunBusinessTypes = arrayOf("Szabászat", "Pékség", "Programozás", "Könyvelés",
            "Ingatlan", "Tájépítészet", "Utazás", "Kézművesség", "Papír írószer",
            "Kávézó", "Fejlesztés", "Villanyszerelés", "Asztalos", "Autó-motor")

    var engWords = arrayOf("throw", "ball", "hat", "red", "worn",
            "list", "words", "computer", "in", "out", "hot", "cold", "warp",
            "speed", "captain", "assert", "hold", "room", "ship", "lost", "is",
            "television", "show", "about", "plane", "crash", "island",
            "monster", "trees", "banging", "smoke", "where", "are", "we",
            "was", "asked", "no", "rescue", "came", "build", "fire", "waited",
            "days", "moved", "to", "caves", "found", "with", "ghost", "dad",
            "in", "white", "rabbit", "lock", "discovered", "hatch", "with",
            "boon", "secretly", "hid", "it", "while", "trying", "to", "open",
            "it", "until", "sidekick", "died", "as", "sacrifice", "island",
            "demanded", "many", "had", "dreams", "or", "visions", "others",
            "came", "took", "people", "who", "are", "they", "what", "do",
            "they", "want", "light", "came", "on", "through", "window",
            "leader", "is", "a", "good", "man", "numbers", "in", "room",
            "enter", "keys", "computer", "end", "of", "world", "wicket",
            "magnetic", "pull", "shepherd", "always", "wrong", "much",
            "suspense", "what", "to", "do", "when", "it", "ends", "I", "will",
            "have", "to", "find", "something", "else", "to", "pique", "my",
            "interest", "or maybe", "write", "lots", "of", "code", "probably",
            "should", "have", "generated", "this", "text", "automatically",
            "so", "will", "from", "the", "web", "ending", "badly", "library",
            "handled", "books", "constantly", "headphones", "of", "ill", "on",
            "it's", "sill", "sits", "sofa")
    private val engBusinessTypes = arrayOf("Furnishings", "Bakery",
            "Accounting", "Textiles", "Manufacturing", "Industries",
            "Pro Services", "Landscaping", "Realty", "Travel",
            "Medical supplies", "Office supplies", "Insurance", "Software",
            "Motors", "Cafe", "Services", "Gymnasium", "Motor Services",
            "Signs", "Development", "Studios", "Engineering", "Development")
    private val emailHosts = arrayOf("gma1l", "hotma1l", "yah00",
            "somema1l", "everyma1l", "ma1lbox", "b1zmail", "ma1l2u")
    private val tlds = arrayOf("org", "net", "com", "biz", "us", "co.uk")
}