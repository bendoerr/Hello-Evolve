# Usage

    gradle start
    
Sometimes a population will die out pretty quickly. Sometimes they get some good mutations that are able to keep the population alive for hundreds and thousands of geneations. There are presuares that keep a populations size around 1000 as the amount of food past that point becomes scarse. I find it quite fun to inspect what specific encodings for traits are becomming successful and will one day add some more details on that sort of stuff. 

# Details

*This may not be up to date as it recorded more of the original idea.*

## DNA

* String a-Z0-9
* Each 5-10 characters forms a gene. Possible genes: Base longevity, color, color preference, ability to eat.
* Each character is numberd a=1, b=2, A=27, 0=53, 9=62. The characters in a gene are added up to produce a number that can fall in a trait range. 10 being the minimum and 620 the max. 305 being the middle and numbers around it the most common. 

## Genes & Traits
**Base Longevity**

* *10-200* 3 generations
* *201-419* 4 generations
* *420-620* 5 generations

The idea is that when the organism is born, it gets this base life before it dies. Things like not eating during a generation can reduce this. 

**Strength**

* *10-200* 60% chance of eating
* *201-419* 75% chance of eating
* *420-620* 90% chance of eating

During each generation the organism needs to eat. If it doesn't eat it losses one generation from it's life.

**Color**

* *10-200* Red
* *201-249* Orange
* *250-299* Yellow
* *300-310* Green
* *311-360* Blue
* *361-419* Indigo
* *420-620* Violet

Organisms can only reproduce with other same color organisms or organisms of a color adjacent on the list. 

*Color codes are tuned to my implementation of the Solarized color scheme on Windows.*

**Color Preference**
Chance Organisms will choose the same color even if there is an organism with an adjacent color with a higher fitness

## Fitness

Fitness is generally figured as Base Longevity + Strength. During breeding, the organism with the best fitness may not always be choosen from the pool of available organisms due to preference.

## Mutations

* Substitution
* Insertion
* Deletion

## Cross-over

Given a gene from p1 and one from p2. If both genes add up to be an even number then p1 is selected, if both add up to be odd then p2 is selected. If one is even and the other is odd there is a 50% chance either will be selected.
