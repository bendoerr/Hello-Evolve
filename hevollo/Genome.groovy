/**
 *           1         2         3         4         5
 * 0123456789012345678901234567890123456789012345678901234 
 * V5ivlMHA4Wv6NaDZh6iCkrubV96703Akm0lFhPVXUg4kXBKqyNW09cR
 * \_/\________/\_/\________/\_/\________/\_/\________/\_/
 * Jnk          Jnk          Jnk          Jnk          Jnk
 *     Base         Strength     Color        Color
 *     Longevity                              Perference
 */
@Mixin(HashMap)
class Genome {
    private static final MAX_LENGTH = 55
    private static final BASES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    String dna

    Genome() {
        dna = SimRandom.instance.getString(BASES, MAX_LENGTH)
        put('baseLongevity', new BaseLongevityGene(this))
        put('strength', new StrengthGene(this))
        put('color', new ColorGene(this))
        put('colorPref', new ColorPrefGene(this))
    }
}
