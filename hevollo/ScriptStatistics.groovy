// This script changes from time to time with things I want to test.
@groovy.lang.Grab('org.apache.commons:commons-math:2.2')
import java.security.SecureRandom
import org.apache.commons.math.stat.descriptive.SummaryStatistics

BASES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

rand = SecureRandom.getInstance("SHA1PRNG", "SUN")

string = {size->
    (1..size).inject("") {s, i -> s + BASES[rand.nextInt(BASES.length())] }
}

calculate = {seq ->
    seq.inject(0) {encoding, base ->
        encoding + BASES.indexOf(base) + 1
    }
}

SummaryStatistics stats = new SummaryStatistics()
(0..6200000).each {
    stats.addValue (calculate(string(10)) as Double)
}

println stats.toString()