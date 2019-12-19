package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.MandelbrotSet;
import org.woehlke.simulation.mandelbrot.conf.Direction;
import org.woehlke.simulation.mandelbrot.conf.Phase;
import org.woehlke.simulation.mandelbrot.conf.Status;

import java.util.Stack;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 28.08.13
 * Time: 12:39
 */
public class MandelbrotTuringMachine implements MandelbrotSet {

    static final long serialVersionUID = mySerialVersionUID;

    public final static int MAX_ITERATIONS = 32;
    public final static int YET_UNCOMPUTED = -1;

    private Point turingPosition,worldDimensions,firstSetPosition;

    private int[][] lattice;
    private Phase turingPhase = Phase.GO_TO_SET;
    private Direction direction = Direction.LEFT;
    private Status status = Status.MANDELBROT;
    private int steps = 0;

    public MandelbrotTuringMachine(Point worldDimensions) {
        this.worldDimensions = worldDimensions;
        lattice = new int[worldDimensions.getX()][worldDimensions.getY()];
        init();
    }

    private void init(){
        direction = Direction.LEFT;
        turingPhase = Phase.GO_TO_SET;
        steps = 0;
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                lattice[x][y] = YET_UNCOMPUTED;
            }
        }
        turingPosition = new Point((worldDimensions.getX()-2),(worldDimensions.getY()/2+11));
    }

    public void step(){
        switch(turingPhase){
            case GO_TO_SET: stepGoToSet(); break;
            case WALK_AROUND: stepWalkAround(); break;
            case FILL_THE_INSIDE: fillTheInside(); break;
            case COLOR_THE_OUTSIDE: colorTheOutside(); break;
            case ALL_DONE: break;
            default: break;
        }
    }

    private void stepGoToSet(){
        ComplexNumber position = getComplexNumberFromLatticeCoordsForMandelbrot(turingPosition);
        int iterations = position.computeMandelbrotIterations(MAX_ITERATIONS);
        //System.out.println(iterations+","+position.toString());
        boolean isInMandelbrotSet = (iterations==MAX_ITERATIONS);
        if(isInMandelbrotSet){
            lattice[turingPosition.getX()][turingPosition.getY()]=0;
            turingPhase=Phase.WALK_AROUND;
            firstSetPosition = new Point(turingPosition);
        } else {
            lattice[turingPosition.getX()][turingPosition.getY()]=iterations;
            turingPosition.setX(turingPosition.getX() - 1);
        }
    }

    private void stepWalkAround(){
        ComplexNumber position = getComplexNumberFromLatticeCoordsForMandelbrot(turingPosition);
        int iterations = position.computeMandelbrotIterations(MAX_ITERATIONS);
        boolean isInMandelbrotSet = iterations==MAX_ITERATIONS;
        //System.out.println(iterations+","+position.toString()+","+steps);
        if(isInMandelbrotSet){
            lattice[turingPosition.getX()][turingPosition.getY()]=0;
            turnRight();
        } else {
            lattice[turingPosition.getX()][turingPosition.getY()]=iterations;
            turnLeft();
        }
        goForward();
        steps++;
        if(turingPosition.equals(firstSetPosition) && (steps>100)){
            turingPhase = Phase.FILL_THE_INSIDE;
            //System.out.println("####");
        }
    }

    private void goForward() {
        switch (direction){
            case UP:
                turingPosition.setY(turingPosition.getY()-1);
                break;
            case RIGHT:
                turingPosition.setX(turingPosition.getX()+1);
                break;
            case DOWN:
                turingPosition.setY(turingPosition.getY()+1);
                break;
            case LEFT:
                turingPosition.setX(turingPosition.getX()-1);
                break;
            default:
                break;
        }
    }

    private void turnRight() {
        Direction newDirection;
        switch (direction){
            case UP: newDirection=Direction.RIGHT; break;
            case RIGHT: newDirection=Direction.DOWN; break;
            case DOWN:newDirection=Direction.LEFT; break;
            case LEFT:newDirection=Direction.UP; break;
            default:newDirection=direction; break;
        }
        direction=newDirection;
    }

    private void turnLeft() {
        Direction newDirection;
        switch (direction){
            case UP: newDirection=Direction.LEFT; break;
            case RIGHT: newDirection=Direction.UP; break;
            case DOWN:newDirection=Direction.RIGHT; break;
            case LEFT:newDirection=Direction.DOWN; break;
            default:newDirection=direction; break;
        }
        direction=newDirection;
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(Point turingPosition) {
        float realX = -2.2f + (3.2f*turingPosition.getX())/worldDimensions.getX();
        float imgY = -1.17f + (2.34f*turingPosition.getY())/worldDimensions.getY();
        return new ComplexNumber(realX,imgY);
    }

    public int getCellStatusFor(int x,int y){
        return lattice[x][y]<0?0:lattice[x][y];
    }

    private void fillTheInside(){
        Point start = new Point(firstSetPosition);
        start.setX(start.getX() - 10);
        Stack<Point> pointStack = new Stack<Point>();
        pointStack.push(start);
        while(!pointStack.empty()){
            Point p = pointStack.pop();
            if(lattice[p.getX()][p.getY()]==YET_UNCOMPUTED){
                lattice[p.getX()][p.getY()]=0;
                pointStack.push(new Point(p.getX()-1,p.getY()));
                pointStack.push(new Point(p.getX()+1,p.getY()));
                pointStack.push(new Point(p.getX(),p.getY()-1));
                pointStack.push(new Point(p.getX(),p.getY()+1));
            }
        }
        //System.out.println("*****");
        turingPhase=Phase.COLOR_THE_OUTSIDE;
    }

    private void colorTheOutside(){
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                if(lattice[x][y] == YET_UNCOMPUTED){
                    ComplexNumber position = getComplexNumberFromLatticeCoordsForMandelbrot(new Point(x, y));
                    int iterations = position.computeMandelbrotIterations(MAX_ITERATIONS);
                    boolean isInMandelbrotSet = iterations==MAX_ITERATIONS;
                    if(isInMandelbrotSet){
                        lattice[x][y]=0;
                    } else {
                        lattice[x][y]=iterations;
                    }
                }
            }
        }
        //System.out.println("------");
        turingPhase=Phase.ALL_DONE;
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForJulia(Point turingPosition) {
        float realX = -1.6f + (3.2f*turingPosition.getX())/worldDimensions.getX();
        float imgY = -1.17f + (2.34f*turingPosition.getY())/worldDimensions.getY();
        return new ComplexNumber(realX,imgY);
    }

    public void click(int xClick, int yClick) {
        if(status==Status.MANDELBROT){
            ComplexNumber c = getComplexNumberFromLatticeCoordsForMandelbrot(new Point(xClick, yClick));
            for(int y=0;y<worldDimensions.getY();y++) {
                for (int x = 0; x < worldDimensions.getX(); x++) {
                    ComplexNumber z = getComplexNumberFromLatticeCoordsForJulia(new Point(x, y));
                    int iterations = z.computeJuliaIterations(MAX_ITERATIONS-1,c);
                    lattice[x][y]=iterations;
                }
            }
            status = Status.JULIA_SET;
        } else {
            status = Status.MANDELBROT;
            init();
        }
    }

}
