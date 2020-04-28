package datafactory.address

interface AddressDataValues {
    /**
     * @return Array of street address
     */
    fun getStreetNames(): Array<String>

    /**
     * @return Array of cities
     */
    fun getCities(): Array<String>

    /**
     * Returns a list of address suffixes such as "Lane", "Drive","Parkway"
     * @return Array of address suffixes
     */
    fun getAddressSuffixes(): Array<String>
}