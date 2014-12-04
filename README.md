Snake Solver
============

An answer to Java Magazine 05 2014's "Snake" Code Challenge. Based on the [solver code snippet] (http://bit.ly/priemslang).

I suspect this problem may be NP-complete (although I haven't attempted to prove or disprove it).

This program uses a brute force method to compute a solution to the problem. The solution will be a local optimum and is not guaranteed to be a minimal score. It uses Java 8's parallel stream feature to optimally use all cores in a multi-core computer in this brute force computation. Note that the parallellism introduces nondetermistic behavior, so multiple runs of this program will not necessarily give the same result.

Classes of interest
-------------------

* [PrimeSnakeSolver] (https://github.com/TinusTinus/snakesolver/blob/master/src/main/java/nl/mvdr/snake/PrimeSnakeSolver.java): main class, run this to start the computation.
* [BruteForceSolver] (https://github.com/TinusTinus/snakesolver/blob/master/src/main/java/nl/mvdr/snake/solver/BruteForceSolver.java): contains the actual algorithm to find a solution.

Best result
-----------

Best result found using this program after a few runs (with BruteForceSolver.MAX_INTERMEDIATE_RESULTS = 2000):

Solution: LRLRLLRLLRLLLRRRRLLRRRLLLRRLRRLRLLRLLRRLRRLLRLLLRLLRRRLLRRLRLLLLRRRLLLRRLLRRLLRLRRLLRLLRRLRRRLRLRLLLRLLRRLLRLRLLRRRLRLLRLRLLRLRRLLRRRLRRLLRRLRRLLRRLLRLLRRRLLLLRRLLRLRRLLLRRRLLRRLRRLLRLLRRLLRLLRRLLRLRRLRRLLLLRRLRRLRLRRRRLLLRRLLRRRLLLRRRLRLLRLRRLRLLRRLLRRLRLLRRLLLRLLRRLLRRLLRRRLLRLLRLRRLRLRLLLRRRLLRLLRLLRRRLLRLRRLRLRLRRRLRRLLLRLRRLRLLRRLRRLRLLRLRRLLRRLRRRLLLLRRRLLLRLRRRLLRLLLLRLRRLLRRLRLLLRRLLRRRLLRRLRLLRLRLRLLRRLLRRRLLLRRRLLLRRLLLRRRLLRLRRRLLLLRLRLLRRLRRRRLLLLLRRRLLRRLLLRRLRRLLRLLRRLLRLRRRLLRLLRRLLRLLRRRLRLLRLRRLLRLLRRLLRLRRLLRLLRRRRLLLLRRLRRLLRLLRRLRRRRLLLRLRRRLLRRLLRRRLLLRLRLRRLLRRLLRRLLRRLRRLLLRRLRRLRLLLRRRLLLRRRLRLRLLRLLRRLRRLRRLLRLRLRRLLLRLLLRRLLRRLLRRLLRRL

Score: 178

Time taken: 367324 ms.
