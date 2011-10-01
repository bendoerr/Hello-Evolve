import java.security.SecureRandom

@Singleton
class SimRandom {
    private SecureRandom rand

    private SimRandom() {
        rand = SecureRandom.getInstance("SHA1PRNG", "SUN")  
    }

    String getString(String characters, Integer size) {
        (1..size).inject("") {s,i-> s + characters[rand.nextInt(characters.length())] }
    }

}

