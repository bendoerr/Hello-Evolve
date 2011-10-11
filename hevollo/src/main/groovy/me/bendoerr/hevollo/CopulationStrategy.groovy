package me.bendoerr.hevollo

abstract class CopulationStrategy {
    private MutationAlgorithm mutationAlgorithm

    abstract List getChildren(Mates mates)

    protected getMutationAlgorithm() {
        if (mutationAlgorithm == null) {
            switch (options['mutationAlgorithm', 'triple']) {
                case 'triple':
                    mutationAlgorithm = new TripleMutationAlgorithm()
                    break
                case 'simle':
                default:
                    mutationAlgorithm = new SimpleMutationAlgorithm()
            }
        }
        return mutationAlgorithm
    }

    String crossover(Gene p1, Gene p2) {
        Boolean p1even = (p1.encodingNumber % 2) == 0
        Boolean p2even = (p2.encodingNumber % 2) == 0

        if (p1 && p2) {
            return p1.dnaSequance
        } else if (!p1 && !p2) {
            return p2.dnaSequance
        }
        return random.getBoolean() ? p1.dnaSequance : p2.dnaSequance
    }
}
