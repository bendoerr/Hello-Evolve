import java.security.SecureRandom

@Singleton
class SimRandom {
    private SecureRandom rand

    private SimRandom() {
        rand = SecureRandom.getInstance("SHA1PRNG", "SUN")
    }

    String getString(String characters, Integer size) {
        (1..size).inject("") {s, i -> s + characters[rand.nextInt(characters.length())] }
    }

    Boolean getBoolean(BigDecimal chanceTrue = 0.5) {
        def i = rand.nextInt(100)
        return i < (100 * chanceTrue)
    }

    Integer getInt(Integer max) {
        rand.nextInt(max)
    }

    Float getFraction() {
        rand.nextFloat()
    }
}

