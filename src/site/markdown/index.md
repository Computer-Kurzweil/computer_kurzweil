# Computer Kurzweil

[![Java CI with Maven](https://github.com/Computer-Kurzweil/computer_kurzweil/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/Computer-Kurzweil/computer_kurzweil/actions)
[![Build Status](https://travis-ci.com/Computer-Kurzweil/computer_kurzweil.svg?branch=master)](https://travis-ci.com/Computer-Kurzweil/computer_kurzweil)
[![Maven Project Reports](src/site/resources/img/maven-feather.png)](https://java.woehlke.org/kurzweil/computer_kurzweil)

## Computer Science Infotainment.

A Tribute to the "Computer Kurzweil" named Article Series in Spektrum der Wissenschaft which is the german release of Scientific American. The Article Series "Computer Kurzweil" were also published as Books.

Content: Cellular Automata, Fractal Geometry, Artficial Life, Simulation, Automata and Turing Machines, Systems Theory.

## Table of Content:
* Mandelbrot: Computing the Edge of the Mandelbrot Set with a Turing Machine
* Simulated Evolution
* Diffusion-limited aggregation (DLA)
* Cyclic Cellular Automaton (CCA).
* More to follow

## Computing the Edge of the Mandelbrot Set with a Turing Machine
![Computing the Area outside the Mandelbrot Set](src/site/resources/img/mandelbrot/screen03.png)

The Mandelbrot set is the set of values of c in the complex plane for which the orbit of 0
under iteration of the complex quadratic polynomial z_(n+1)=z_n^2+c remains bounded.

That is, a complex number c is part of the Mandelbrot set if, when starting with z0 = 0
and applying the iteration repeatedly, the absolute value of zn remains bounded
however large n gets. **[More...](Tab_Mandelbrot.md)**

## Simulated Evolution
![Early Screen](src/site/resources/img/simulatedevolution/screen1.png)

Green food appears in a world with red moving cells. These cells eat the food if it is on their position.
Movement of the cells depends on random and their DNA. A fit cellConf moves around and eats enough to reproduce.
Reproduction is done by splitting the cellConf and randomly changing the DNA of the two new Cells.
If a cellConf doesn't eat enough, it will first stand still and after a while it dies. **[More...](Tab_SimulatedEvolution.md)**

## Diffusion-limited aggregation (DLA)
![The Dendrite after a while](src/site/resources/img/dla/screen2.png)

Diffusion-limited aggregation (DLA) is the process whereby particles undergoing a random walk due to Brownian motion cluster together to form aggregates of such particles.

This theory, proposed by T.A. Witten Jr. (not to be confused with Edward Witten) and L.M. Sander in 1981, is applicable to aggregation
in any system where diffusion is the primary means of transport in the system. DLA can be observed in many systems such as electrodeposition,
Hele-Shaw flow, mineral deposits, and dielectric breakdown.

The clusters formed in DLA processes are referred to as Brownian trees. These clusters are an example of a fractal. **[More...](Tab_DiffusionLimitedAggregation.md)**

## Cyclic Cellular Automaton (CCA)
![Later Screen](src/site/resources/img/cca/screen2.png)

The cyclic cellular automaton is a cellular automaton rule developed by David Griffeath and studied by several other cellular automaton researchers. In this system, each cellConf remains unchanged until some neighboring cellConf has a modular value exactly one unit larger than that of the cellConf itself, at which point it copies its neighbor's value.
One-dimensional cyclic cellular automata can be interpreted as systems of interacting particles, while cyclic cellular automata in higher dimensions exhibit complex spiraling behavior.

A random filled 2d lattice of cells with a cyclic rule to change state of a cell depending on the direct neighbour cells. After a while spirals occour in the lattice. Rules are only defined for the behaviour of a the cells, so these Spirals ahow a Meta-Behaviour of the System. This may be a very simplified computational Model for Crystallization Processes in Nature and  
Concentration in Business and Social inequality in free market situations.

**[More...](Tab_CyclicCellularAutomaton.md)**

## Koch Snowflake
**[More...](Tab_KochSnowflake.md)**

## Same Game
**[More...](Tab_SameGame.md)**

## Sierpinski Triangle
**[More...](Tab_SierpinskiTriangle.md)**

## Tetris
**[More...](Tab_Tetris.md)**

## Turmite
**[More...](Tab_Turmite.md)**

## Wa-Tor
**[More...](Tab_WaTor.md)**


## Tabs

| Java Package and Subdirectory                                     | Title                             | Java Class Prefix                                                                                                                                                                       |
|-------------------------------------------------------------------|-----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [cca](Tab_CyclicCellularAutomaton.md)           | Cyclic Cellular Automaton CCA     | [CyclicCellularAutomaton](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/cca/CyclicCellularAutomaton.java)         | 
| [dla](Tab_DiffusionLimitedAggregation.md)       | Diffusion-limited aggregation DLA | [DiffusionLimitedAggregation](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/dla/DiffusionLimitedAggregation.java) | 
| [gameoflive](Tab_GameOfLife.md)                 | Conways Game of Life              | [ConwaysGameOfLife](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/gameoflive/ConwaysGameOfLife.java)              |
| [kochsnowflake](Tab_KochSnowflake.md)           | Koch Snowflake                    | [KochSnowflake](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/kochsnowflake/KochSnowflake.java)                   | 
| [mandelbrot](Tab_Mandelbrot.md)                 | Mandelbrot                        | [Mandelbrot](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/mandelbrot/Mandelbrot.java)                            |  
| [randomwalk](Tab_RandomWalk)                    | Random Walk                       | [RandomWalk](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/randomwalk/RandomWalk.java)                            | 
| [samegame](Tab_SameGame.md)                     | Same Game                         | [SameGame](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/samegame/SameGame.java)                                  | 
| [sierpinskitriangle](Tab_SierpinskiTriangle.md) | Sierpinski Triangle               | [SierpinskiTriangle](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/sierpinskitriangle/SierpinskiTriangle.java)    |
| [simulatedevolution](Tab_SimulatedEvolution.md) | Simulated Evolution               | [SimulatedEvolution](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/simulatedevolution/SimulatedEvolution.java)    | 
| [tetris](Tab_Tetris.md)                         | Tetris                            | [Tetris](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/tetris/Tetris.java)                                        | 
| [turmite](Tab_Turmite.md)                       | Turmite                           | [Turmite](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/turmite/Turmite.java)                                     |
| [wator](Tab_WaTor.md)                           | Wa-Tor                            | [WaTor](https://github.com/Computer-Kurzweil/computer_kurzweil/blob/master/src/main/java/org/woehlke/computer/kurzweil/tabs/wator/WaTor.java)                                           |

## Projects for whole Application

|  Status   | Project                                                                                            |
|-----------|----------------------------------------------------------------------------------------------------|
| OK        | [Development](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/9)                   | 
| BUG       | [DevOps](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/10)                       |
| OK        | [Gui Layout](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/5)                    |
| BUG       | [Better Thread Model - with Threadpool, Fork, Join](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/8) |

## Projects for Tabs

| Status   | Tab                                                                                                   |
|----------|-------------------------------------------------------------------------------------------------------|
| OK       | [CyclicCellularAutomaton](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/2)          | 
| OK       | [Random Walk](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                      |
| BLOCKED  | [DiffusionLimitedAggregation](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/1)      | 
| BUG      | [SimulatedEvolution](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/3)               | 
| BLOCKED  | [Mandelbrot Set](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/6)                   | 
| PLANNING | [Koch Snowflake](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                   | 
| PLANNING | [Sierpinski Triangle](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)              | 
| PLANNING | [Same Game](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                        | 
| PLANNING | [Tetris](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                           | 
| PLANNING | [Turmite](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                          |
| PLANNING | [Wator](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                            | 
| PLANNING | [Conways Game of Life](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)             | 

## Projects for Single Apps

| Status   | Tab                                                                                                   |
|----------|-------------------------------------------------------------------------------------------------------|
| OK       | [CyclicCellularAutomaton](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/2)          | 
| OK       | [Random Walk](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                      |
| BLOCKED  | [DiffusionLimitedAggregation](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/1)      | 
| BUG      | [SimulatedEvolution](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/3)               | 
| BLOCKED  | [Mandelbrot Set](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/6)                   | 
| PLANNING | [Koch Snowflake](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                   | 
| PLANNING | [Sierpinski Triangle](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)              | 
| PLANNING | [Same Game](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                        | 
| PLANNING | [Tetris](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                           | 
| PLANNING | [Turmite](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                          |
| PLANNING | [Wator](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)                            | 
| PLANNING | [Conways Game of Life](https://github.com/Computer-Kurzweil/computer_kurzweil/projects/7)             | 

## Table Legend

| Status    | Description                    |
|-----------|--------------------------------|
| OK        | Tab is running without Errors  |
| BUG       | Tab is running with Errors     |
| BLOCKED   | Tab started but is not running |
| CRASHED   | Tab is not starting            |
| INACTIVE  | Tab is not in TabPlane         |
| PLANNING  | Research and Preparation       |

## Goto

| Work in Progress              | Github                                                                                 |
|-------------------------------|----------------------------------------------------------------------------------------|
| *[Tabs](Tabs.md)*             | &nbsp;                                                                                 |
| &nbsp;                        | &nbsp;                                                                                 |
| [Projects](Projects.md)       | [Github-Projects](https://github.com/Computer-Kurzweil/computer_kurzweil/projects)     |
| [Milestones](Milestones.md)   | [Github-Milestones](https://github.com/Computer-Kurzweil/computer_kurzweil/milestones) |
| [Issues](Issues.md)           | [Github-Issues](https://github.com/Computer-Kurzweil/computer_kurzweil/issues)         |
| [Releases](Releases.md)       | [Github-Releases](https://github.com/Computer-Kurzweil/computer_kurzweil/releases)     |
| [Engineering](Engineering.md) | &nbsp;                                                                                 |
| &nbsp;                        | &nbsp;                                                                                 |
| *[back...](index.md)*         | *[computer_kurzweil](https://github.com/Computer-Kurzweil/computer_kurzweil)*          |

## Copyright
[&copy; 2020 Thomas W&ouml;hlke](LICENSE.code.md)
