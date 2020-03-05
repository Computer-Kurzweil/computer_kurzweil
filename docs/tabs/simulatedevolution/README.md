# Simulated Evolution

**Artificial Life Simulation of Bacteria Motion depending on DNA**

## Abstract

Green food appears in a world with red moving cells. These cells eat the food if it is on their position.
Movement of the cells depends on random and their DNA. A fit cell moves around and eats enough to reproduce.
Reproduction is done by splitting the cell and randomly changing the DNA of the two new Cells.
If a cell doesn't eat enough, it will first stand still and after a while it dies.

## Screenshots

### Early Screen 

![Early Screen](img/screen1.png)

### Later Screen 

![Later Screen](img/screen2.png)

### Explanation

| Color | Explanation |
|-------|-------------|
| ![](img/black.png) | water           |
| ![](img/green.png) | food            |
| ![](img/blue.png) | cell is young   |
| ![](img/yellow.png)  | cell is fat enough to reproduce*   |
| ![](img/red.png)  | cell is old enough to reproduce*   |
| ![](img/light_gray.png)  | cell is hungry and waiting for food or death   |
| ![](img/dark_gray.png)  | cell is old and waiting for death   |
| &nbsp; | * (if cell is fat and old enough for reproduction it splits and changes the childrens DNA)   |

## UML Class Model

![UML Class Model](img/Class_Model.jpg)

## Blog Article 
* [Blog Article](http://thomas-woehlke.blogspot.de/2016/01/simulated-evolution-artificial-life-and.html)


##
[&copy; 2020 Thomas W&ouml;hlke](../../LICENSE.code.md)



