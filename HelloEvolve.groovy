Number.metaClass.squared = { delegate.power(2) }

random = java.security.SecureRandom.getInstance("SHA1PRNG", "SUN")
POOLSIZE = 20
bases = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ !,0123456789'.toCharArray()
target = "Hello, World!"

def genepool = (0..POOLSIZE).collect {
    def dna = (0..target.length()-1).collect { bases[random.nextInt(bases.length)] }.join('')
    [dna: dna, fitness: fitness(dna, target)]
}

def fitness(source, target) {
    (0..source.length()-1).inject(0) {fitval, i->
        fitval += (target[i].toCharacter() - source[i].toCharacter()).squared()
    }
}

def mutate(source) {
    def charpos = random.nextInt(source.length())
    def parts = source.toList()
    parts[charpos] = parts[charpos].next()
    return parts.join('')
}

def breed(p1, p2) {
    def childDna = p1.dna.toList()

    def start = random.nextInt(p2.dna.length())
    def stop = random.nextInt(p2.dna.length())
    if(start > stop) (stop, start) = [start, stop]
    childDna[start..stop] = p2.dna[start..stop]
     
    childDna = childDna.join('')
    return [dna: mutate(childDna), fitness: fitness(childDna, target)]
}

def randomParent(genepool) {
    def n = (random.nextFloat() * random.nextFloat() * (POOLSIZE - 1)).toInteger()
    return genepool[n]
}

def generation = 0
while(true) {
    generation++
    genepool.sort { it.fitness }.reverse()
    
    if(genepool.first().fitness == 0) { break }

    def p1 = randomParent(genepool)
    def p2 = randomParent(genepool)
    def child = breed(p1, p2)
    
    if(child.fitness < genepool[-1].fitness) {
        genepool[-1] = child
    }

    genepool.each {
        println "${generation.toString().padRight(5)} ${it.fitness.toString().padRight(5)} ${it.dna}"
    }
    println "".padRight(15, '-')
}

