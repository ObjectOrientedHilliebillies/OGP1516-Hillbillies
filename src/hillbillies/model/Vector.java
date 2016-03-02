package hillbillies.model;

import java.util.ArrayList;

public class Vector {
	private double coordX;
	private double coordZ;
	private double coordY;

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
	
	public void setXcoord(double coordX) {
		this.coordX = coordX;
	}
	public void setYcoord(double coordY) {
		this.coordY = coordY;
	}
	public void setZcoord(double coordZ) {
		this.coordZ = coordZ;
	}
	
	public double getXCoord() {
		return this.getXCoord();
	}
	public double getYCoord() {
		return this.getYCoord();
	}
	public double getZCoord() {
		return this.getZCoord();
	}
	
}
