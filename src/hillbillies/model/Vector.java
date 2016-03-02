package hillbillies.model;

import java.util.ArrayList;

public class Vector {
	private double compX;
	private double compY;
	private double compZ;

	public Vector(double coordX, double coordY, double coordZ){
		setXcoord(coordX);
		setYcoord(coordY);
		setZcoord(coordZ);
	}
	
	public void setVector(double coordX, double coordY, double coordZ) {
		setXcoord(coordX);
		setYcoord(coordY);
		setZcoord(coordZ);
	}
	
	public ArrayList<Double> getVectorArray() {
		ArrayList<Double> vectorArray = new ArrayList<>();
		vectorArray.add(this.getXCoord());
		vectorArray.add(this.getYCoord());
		vectorArray.add(this.getZCoord());
		return vectorArray;
	}
	
	// TODO Ik weet niet of dit helemaal mag.
	public Cube getCube(){
		return new Cube(this);
	}
	
	public void setXcoord(double coordX) {
		this.compX = coordX;
	}
	public void setYcoord(double coordY) {
		this.compY = coordY;
	}
	public void setZcoord(double coordZ) {
		this.compZ = coordZ;
	}
	
	public double getXCoord() {
		return this.compX;
	}
	public double getYCoord() {
		return this.compY;
	}
	public double getZCoord() {
		return this.compZ;
	}
	
}
