Number.metaClass.squared = { delegate.power(2) }

random = java.security.SecureRandom.getInstance("SHA1PRNG", "SUN")
POOLSIZE = 10
bases = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ !,0123456789'
target = "Hello, World!"

def genepool = (0..POOLSIZE).collect {
    def dna = (0..target.length()-1).collect { bases[random.nextInt(bases.length())] }.join('')
    [dna: dna, fitness: fitness(dna, target)]
}

def fitness(source, target) {
    (0..source.length()-1).inject(0) {fitval, i->
        fitval += (bases.indexOf(target[i]) - bases.indexOf(source[i])).abs()
    }
}

def mutate(source) {
    def charpos = random.nextInt(source.length())
    def parts = source.toList()
    parts[charpos] = random.nextInt(2) == 0 ? nextBase(parts[charpos]) : prevBase(parts[charpos])
    return random.nextInt(5) == 1 ? mutate(parts.join('')) : parts.join('')
}

def breed(p1, p2) {
    def childDna = p1.dna.toList()

    def start = random.nextInt(p2.dna.length()-1)
    def stop = random.nextInt(p2.dna.length()-1)
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
    genepool = genepool.sort {l,r-> l.fitness <=> r.fitness }
    
    if(genepool[0].fitness == 0) { break }
    
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

def nextBase(def base) {
    def i = bases.indexOf(base) + 1 
    bases[i >= bases.length() ? 0 : i]
}

def prevBase(def base) {
    def i = bases.indexOf(base) - 1
    bases[i < 0 ? bases.length() - 1 : i]
}
