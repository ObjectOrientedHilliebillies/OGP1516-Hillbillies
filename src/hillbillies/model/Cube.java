package hillbillies.model;

import java.util.ArrayList;

public class Cube {
	private int compX;
	private int compY;
	private int compZ;
	
	public Cube(Vector vector){
		setXcoord((int) (vector.getXCoord()));
		setYcoord((int) (vector.getYCoord()));
		setZcoord((int) (vector.getZCoord()));
	}
	
	public void setCube(Vector vector){
		setXcoord((int) (vector.getXCoord()));
		setYcoord((int) (vector.getYCoord()));
		setZcoord((int) (vector.getZCoord()));
	}
	
	public ArrayList<Integer> getCubeArray() {
		ArrayList<Integer> cubeArray = new ArrayList<>();
		cubeArray.add(this.getXCoord());
		cubeArray.add(this.getYCoord());
		cubeArray.add(this.getZCoord());
		return cubeArray;
	}
	
	public int[] getIntCube() {
		int[] cubeArray = new int[3];
		cubeArray[0] = this.getXCoord();
		cubeArray[1] = this.getYCoord();
		cubeArray[2] = this.getZCoord();
		return cubeArray;
	}
	
	public void setXcoord(int coordX) {
		this.compX = coordX;
	}
	public void setYcoord(int coordY) {
		this.compY = coordY;
	}
	public void setZcoord(int coordZ) {
		this.compZ = coordZ;
	}
	
	public int getXCoord() {
		return this.compX;
	}
	public int getYCoord() {
		return this.compY;
	}
	public int getZCoord() {
		return this.compZ;
	}
	
	/**
	 * @invar  The weight of each unit must be a valid weight for any
	 *         unit.
	 *       | isValidWeight(getWeight())
	 */

/**
 * Initialize this new unit with given weight.
 * 
 * @param  weight
 *         The weight for this new unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight. Otherwise, the weight of this new unit is equal
 *         to defaultWeight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == defaultWeight
 */
public Cube(Float weight) {
	if (! isValidWeight(weight))
		weight = defaultWeight;
	setWeight(weight);
}

/**
 * Return the weight of this unit.
 */
@Basic @Raw
public Float getWeight() {
	return this.weight;
}

/**
 * Check whether the given weight is a valid weight for
 * any unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == !minWeight
*/
public static boolean isValidWeight(Float weight) {
	return false;
}

/**
 * Set the weight of this unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 */
@Raw
public void setWeight(Float weight) {
	if (isValidWeight(weight))
		this.weight = weight;
}

/**
 * Variable registering the weight of this unit.
 */
private Float weight;

}
