# Mandelbrot

**Computing the Edge of the Mandelbrot Set with a Turing Machine**

## Screenshots

### Running around the Edge of the Mandelbrot Set

![Running around the Edge of the Mandelbrot Set](img/screen01.png)

![Running around the Edge of the Mandelbrot Set](img/screen02.png)

### Computing the Area outside the Mandelbrot Set

![Computing the Area outside the Mandelbrot Set](img/screen03.png)

### Clicked somewhere on the Edge of Mandelbrot Set: The Julia Set

![Clicked somewhere on the Edge of Mandelbrot Set: The Julia Set](img/julia01.png)

![Clicked on another Point on the Edge of Mandelbrot Set: The Julia Set](img/julia02.png)

## The Mandelbrot Set

The Mandelbrot set is the set of values of c in the complex plane for which the orbit of 0 
under iteration of the complex quadratic polynomial z_(n+1)=z_n^2+c remains bounded.

That is, a complex number c is part of the Mandelbrot set if, when starting with z0 = 0 
and applying the iteration repeatedly, the absolute value of zn remains bounded 
however large n gets. 
* [https://en.wikipedia.org/wiki/Mandelbrot_set](https://en.wikipedia.org/wiki/Mandelbrot_set)

## The Turing Machine to Compute the Mandelbrot Set
The Complex Number Plane is divided into Cells for the two dimensional Tape of the Turing-Machine.
Starting with Complex Number of Cell right from the Mandelbrot Set the Turing Machine goes one Step to the left and computes, wether the Complex Number of the Cell is inside the Set. If not, it continues to go left.
The Turing-Machine then goes around the Set by computing wether the Complex Number of the Cell is inside the Set. If not it turns left, else it turns right. After that it goes one step forward.
After the Turing Machine finished to run around the Set, the Application paints the Inside of the Set Black und computes the Colors of the Outside.
* [https://en.wikipedia.org/wiki/Turing_machine](https://en.wikipedia.org/wiki/Turing_machine)
## Julia Set
The Mandelbrot Set is drawn into the Complex Number Plane and so clicking into it, it delivers the Complex Number to calculate a Julia Set for it.
The most beautiful Julia Sets are drawn by clicking into the Border of the Mandelbrot Set.
* More: [https://en.wikipedia.org/wiki/Julia_set](https://en.wikipedia.org/wiki/Julia_set)




