package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.drawings.*;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Drawing> shapes = new ArrayList<Drawing>();
        private SquiggleLine squiggleLine;
        private PolyLine polyLine;
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Drawing> undoList = new ArrayList<>();
        private int index = -1;
        private boolean outlineFlag;
        private Eraser eraser;
        private Bezier bezier;
        private ArrayList<Point> pointsSquiggle=new ArrayList<>();
        private ArrayList<Point> pointsEraser = new ArrayList<>();

        public void addBezier(Bezier bezier) {
                this.shapes.add(bezier);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void startSquiggle(SquiggleLine line) {
                this.squiggleLine = line;
                this.setChanged();
                this.notifyObservers();
        }
        public void addPointSquiggle(Point p){
                if (this.squiggleLine!=null){
                        this.pointsSquiggle.add(p);
                        this.squiggleLine.addPoint(p);
                        //this.points.add(p);
                        this.setChanged();
                        this.notifyObservers();
                }
        }
        public SquiggleLine finishSquiggle(){
                this.pointsSquiggle.clear();
                this.setChanged();
                this.notifyObservers();
                return this.squiggleLine;
        }

        public SquiggleLine getSquiggleLine() {
                return this.squiggleLine;
        }

        public void startEraser(Eraser e) {
                this.eraser = e;
                this.setChanged();
                this.notifyObservers();
        }

        public void addPointEraser(Point p){
                if (this.eraser!=null){
                    this.pointsEraser.add(p);
                    this.eraser.addPoint(p);
                    this.setChanged();
                    this.notifyObservers();
                }
        }

        public Eraser finishEraser(){
                this.pointsEraser.clear();
                this.setChanged();
                this.notifyObservers();
                return this.eraser;
        }

        public Eraser getEraser() {
                return this.eraser;
        }

        public ArrayList<Point> getPointsSquiggle(){return pointsSquiggle;}

        public void startPolyline(PolyLine line) {
                this.polyLine = line;
                this.setChanged();
                this.notifyObservers();
        }

        public void addPolyPoint(Point p){
                if (this.polyLine!=null){
                        this.points.add(p);
                        this.polyLine.addPoint(p);
                        this.setChanged();
                        this.notifyObservers();
                }
        }

        public PolyLine finishPolyline(){

                this.points.clear();
                this.setChanged();
                this.notifyObservers();
                return this.polyLine;
        }
        public PolyLine getPolyLine() {
                return this.polyLine;
        }

        public ArrayList<Point> getPoints(){return points;}

        public void addCircle(Circle c){
                this.shapes.add(c);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }
        public void addTriangle(Triangle t){
                this.shapes.add(t);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }


        public void removePrevious(Drawing d) {
                if (!this.shapes.isEmpty()){
                        if (this.shapes.getLast() == d){
                                this.shapes.removeLast();
                        }
                }
        }

        public void addRectangle(Rectangle r){
                this.shapes.add(r);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void addSquare(Square r){
                this.shapes.add(r);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void addOval(Oval r) {
                this.shapes.add(r);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void addSquiggle(SquiggleLine line){
                this.shapes.add(line);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void addPolyline(PolyLine line){
                this.shapes.add(line);
                this.setChanged();
                this.notifyObservers();
                this.index++;
        }

        public void undo() {
                if (this.index>=0 && !this.shapes.isEmpty()) {
                        this.undoList.add(this.shapes.removeLast());
                        this.index--;
                        this.setChanged();
                        this.notifyObservers();
                }
        }

        public void redo() {
                if (!this.undoList.isEmpty()) {
                        this.shapes.add(this.undoList.removeLast());
                        this.index++;
                        this.setChanged();
                        this.notifyObservers();
                }
        }

        public void addEraser(Eraser e){
                this.shapes.add(e);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Drawing> getShapes(){ return shapes;}

        public void setShapes(ArrayList<Drawing> shapes) {
                this.shapes = new ArrayList<>(shapes);
                this.setChanged();
                this.notifyObservers();
        }

        public void resetUndoList() {
                this.undoList.clear();
        }

}
