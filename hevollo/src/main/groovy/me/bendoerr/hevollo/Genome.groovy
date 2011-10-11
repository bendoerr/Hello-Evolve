package me.bendoerr.hevollo

/**
 *           1         2         3         4         5
 * 0123456789012345678901234567890123456789012345678901234 
 * V5ivlMHA4Wv6NaDZh6iCkrubV96703Akm0lFhPVXUg4kXBKqyNW09cR
 * \_/\________/\_/\________/\_/\________/\_/\________/\_/
 * Jnk          Jnk          Jnk          Jnk          Jnk
 *     Base         Strength     Color        Color
 *     Longevity                              Preference
 */
@Mixin(HashMap)
class Genome {
    private static final MAX_LENGTH = 55
    static final BASES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    String dna

    Genome() {
        dna = SimRandom.instance.getString(BASES, MAX_LENGTH)
        initialize()
    }

    Genome(String dna) {
        this.dna = dna
        initialize()
    }

    protected void initialize() {
        // TODO: This is backwards, the name should be part of the gene, not defined here.
        put('baseLongevity', new BaseLongevityGene(this))
        put('strength', new StrengthGene(this))
        put('color', new ColorGene(this))
        put('colorPref', new ColorPrefGene(this))

        // Junk genes
        [0..2, 13..15, 26..28, 39..41, 52..54].eachWithIndex {range, i ->
            put("junk$i", new JunkGene(this, range))
        }
    }

    List<Gene> ordered() {
        values().sort { it.getStartIndex() }
    }
}

