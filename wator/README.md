# Wa-Tor

**A.K. Dewdney: Sharks and fish wage an ecological war on the toroidal planet Wa-Tor
(Scientific American, December 1984)**

* https://en.wikipedia.org/wiki/Wa-Tor
* https://github.com/Sledro/Wator
* https://github.com/galenscovell/Wa-Tor
* https://www.revolvy.com/folder/Cellular-automaton-rules/318788
* http://www.personal.psu.edu/tcr2/textbook/figures/wator_dewdney.pdf

## Wa-Tor is 
a population dynamics simulation devised by Alexander Keewatin Dewdney[1] and presented in the December 1984 issue of Scientific American in a five-page article entitled "Computer Recreations: Sharks and fish wage an ecological war on the toroidal planet Wa-Tor".

## The planet of Wa-Tor
(T)he planet Wa-Tor ... is shaped like a torus, or doughnut, and is entirely covered with water. The two dominant denizens of Wa-Tor are sharks and fish ...[1]

## Wa-Tor is implemented as 
a two-dimensional grid with three colours, one for fish, one for sharks and one for empty water. If a creature moves past the edge of the grid, it reappears on the opposite side. The sharks are predatory and eat the fish. Both sharks and fish live, move, reproduce and die in Wa-Tor according to the simple rules defined below. From these simple rules, complex emergent behavior can be seen to arise.

##Predators and prey
The balance of this ecosystem is very delicate: the populations of two species can follow hugely different cycles depending on the given parameters (such as reproduction cycles and the time period in which a shark must eat to avoid starvation) as well as starting positions of each being. It may go from both species being endangered to an abundance of one or both.

When the prey are numerous, predators can reproduce rapidly. But this increase in turn increases the number of prey hunted and the population of the prey decreases. When the prey becomes rarer, predators begin to starve and die of starvation, decreasing their population and easing the hunting pressure on the prey. The prey (and in time predator) can then go back to rapidly reproducing as the cycle repeats itself.

## Rules
"Time passes in discrete jumps, which I shall call chronons. During each chronon a fish or shark may move north, east, south or west to an adjacent point, provided the point is not already occupied by a member of its own species. A random-number generator makes the actual choice. For a fish the choice is simple: select one unoccupied adjacent point at random and move there. If all four adjacent points are occupied, the fish does not move. Since hunting for fish takes priority over mere movement, the rules for a shark are more complicated: from the adjacent points occupied by fish, select one at random, move there and devour the fish. If no fish are in the neighborhood, the shark moves just as a fish does, avoiding its fellow sharks."

### For the fish
1. At each chronon, a fish moves randomly to one of the adjacent unoccupied squares. If there are no free squares, no movement takes place.
1. Once a fish has survived a certain number of chronons it may reproduce. This is done as it moves to a neighbouring square, leaving behind a new fish in its old position. Its reproduction time is also reset to zero.

### For the sharks
1. At each chronon, a shark moves randomly to an adjacent square occupied by a fish. If there is none, the shark moves to a random adjacent unoccupied square. If there are no free squares, no movement takes place.
1. At each chronon, each shark is deprived of a unit of energy.
2. Upon reaching zero energy, a shark dies.
1. If a shark moves to a square occupied by a fish, it eats the fish and earns a certain amount of energy.
1. Once a shark has survived a certain number of chronons it may reproduce in exactly the same way as the fish.

## Possible results
In the long run there are three possible scenarios in Wa-Tor:

1. A perfect balance between fish and sharks, which increase and decrease but never become extinct.
1. Disappearance of sharks.
1. Extinction of both species.

The first scenario can be very difficult to obtain, where a kind of equilibrium is achieved in which the two populations fluctuate periodically. In most cases, the amount of fish is reduced to an almost endangered state, then the shark population rapidly falls due to shortage of food. This allows the fish population to grow again until the shark population can meet this growth.

The extinction of both animals occurs when sharks exceed in number to a point where they eat all the fish. As the fish were the only source of food for sharks they will inevitably die of starvation.

Conversely, if the initial number of fish is low, or the sharks have a very short period of starvation, the second scenario occurs. In this case the sharks will become extinct, leaving the field open to the fish.
