Number.metaClass.squared = { delegate.power(2) }
random = java.security.SecureRandom.getInstance("SHA1PRNG", "SUN")

def source = "T0&st 1% N1ce"
def target = "Hello, World!"
def fitval = fitness(source, target)

def fitness(source, target) {
    (0..source.length()-1).inject(0) {fitval, i->
        fitval += (target[i].toCharacter() - source[i].toCharacter()).squared()
    }
}

def mutate(source) {
    def charpos = random.nextInt(source.length())
    def parts = source.toList()
    parts[charpos] = random.nextInt(2) == 1 ? parts[charpos].next() : parts[charpos].previous()
    return parts.join('')
}

def generation = 0
while(true) {
    generation++
    def mutation = mutate(source)
    mFitval = fitness(mutation, target)
    if(mFitval < fitval) {
        fitval = mFitval
        source = mutation
        println "${generation.toString().padRight(5)} ${fitval.toString().padRight(5)} ${mutation}"
    }
    if(fitval == 0) { break }
}

