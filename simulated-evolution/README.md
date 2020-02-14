# Simulated Evolution

**Artificial Life Simulation of Bacteria Motion depending on DNA**

## Abstract

Green food appears in a world with red moving cells. These cells eat the food if it is on their position.
Movement of the cells depends on random and their DNA. A fit cellConf moves around and eats enough to reproduce.
Reproduction is done by splitting the cellConf and randomly changing the DNA of the two new Cells.
If a cellConf doesn't eat enough, it will first stand still and after a while it dies.

## Blog Article 
* [http://thomas-woehlke.blogspot.de/2016/01/simulated-evolution-artificial-life-and.html](http://thomas-woehlke.blogspot.de/2016/01/simulated-evolution-artificial-life-and.html)

## Screenshots

### Early Screen 

![Early Screen](src/main/resources/img/screen1.png)

### Later Screen 

![Later Screen](src/main/resources/img/screen2.png)

### Explanation

| Color | Explanation |
|-------|-------------|
| ![](src/main/resources/img/black.png) | water           |
| ![](src/main/resources/img/green.png) | food            |
| ![](src/main/resources/img/blue.png) | cellConf is young   |
| ![](src/main/resources/img/yellow.png)  | cellConf is fat enough to reproduce*   |
| ![](src/main/resources/img/red.png)  | cellConf is old enough to reproduce*   |
| ![](src/main/resources/img/light_gray.png)  | cellConf is hungry and waiting for food or death   |
| ![](src/main/resources/img/dark_gray.png)  | cellConf is old and waiting for death   |
| &nbsp; | * (if cellConf is fat and old enough for reproduction it splits and changes the childrens DNA)   |


## UML Class Model

![UML Class Model](src/main/resources/img/Class_Model.jpg)

## Git Repository
* [https://github.com/thomaswoehlke/simulated-evolution](https://github.com/thomaswoehlke/simulated-evolution)

## Run the Desktop Application
```
git clone https://github.com/thomaswoehlke/simulated-evolution.git
cd simulated-evolution
./gradlw run
```

## Run the Applet Test
```
git clone https://github.com/thomaswoehlke/simulated-evolution.git
cd simulated-evolution
TODO: xxx
```


