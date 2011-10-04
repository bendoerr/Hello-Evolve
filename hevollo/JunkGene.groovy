class JunkGene extends Gene {
    private Integer startIndex
    private Integer endIndex

    Integer getStartIndex() { startIndex }

    Integer getEndIndex() { endIndex }

    Object getEncodedTrait() { null }

    JunkGene(Genome owner, IntRange range) {
        startIndex = range.from
        endIndex = range.to
        initialize(owner)
    }

    JunkGene(Genome g) { super(g) }
}
