package hillbillies.model;

import java.nio.file.SecureDirectoryStream;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

// FIXME Als de strength ofzo veranderd kan het zijn dat de unit zijn weight niet meer legaal is.
// FIXME De speed is nog niet juist
// FIXME Er zijn bugs als je meerder units hebt
// FIXME Als je omhoog gaat veranderd alles in Nan !?
// FIXME Na 3 min gameTime crashed het


/**
 * @invar  The position of each unit must be a valid position for any
 *         unit.
 *       | isValidPosition(getPosition())
 * @invar  The weight of each unit must be a valid weight for any
 *         unit.
 *       | isValidWeight(getWeight())
 *       
 * @invar  The strength of each unit must be a valid strength for any
 *         unit.
 *       | isValidStrength(getStrength())
 *       
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 *       
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 *       
 * @invar  The stamina of each unit must be a valid stamina for any
 *         unit.
 *       | isValidStamina(getStamina())
 *  
 * @invar  The hitpoints of each Unit must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(getHitpoints())
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 *       
 * @version 1.0
 * @author  Jonas Vantrappen & Victor Van Eetvelt
 */

public class Unit {


private static final int cubesPerRib = 50;

/* Unit creation */
	/**
 * Initialize this new unit with given name, given initialPosition, given weight, given agility, 
 * given strength, given toughness, given enableDefaultBehavior, maximal hitpoints, maximal stamina,
 * default orientation.
 * 
 * @param  name
 *         The name for this new unit.
 * @param  initialPosition
 *         The initial position for this new unit.
 * @param  weight
 *         The weight for this new unit.
 * @param  agility
 *         The agility for this new unit.
 * @param  strength
 *         The strength for this new unit.
 * @param  toughness
 *         The toughness for this new unit.  
 * @param  enableDefaultBehavior
 *         Enables default behavior for this new unit.
 * @throws ModelException 
 * @pre    The maximal hitpoints must be a valid hitpoints for any Unit.
 *       | isValidHitpoints(this.getMaxHitpoints)
 * @pre    The given stamina must be a valid stamina for any unit.
 *       | isValidStamina(stamina)
 * @effect The position of this new unit is set to
 *         the given position.
 *       | this.setPosition(position)
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight. Otherwise, the weight of this new unit is equal
 *         to (strength+agility)/2.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == (strength+agility)/2
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility. Otherwise, the agility of this new unit is equal
 *         to 25.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == 25
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength. Otherwise, the strength of this new unit is equal
 *         to 25.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 25
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness. Otherwise, the toughness of this new unit is equal
 *         to 25.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == 25
 * @post   The hitpoints of this new Unit is equal to the maximal
 *         hitpoints.
 *       | new.getHitpoints() == this.getMaxHitpoints
 * @post   The stamina of this new unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 * @post  The orientation of this new unit is equal
 *         to PI/2.
 *       | if (isValidOrientation(orientation))
 *       | new.getOrientation() == PI/2
 */
public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
		boolean enableDefaultBehavior)
		throws IllegalArgumentException, ModelException {
	/*  given enableDefaultBehavior, maximal hitpoints,
	 *  maximal stamina,
	 */
	this.setName(name);
	
	try {
		double[] coordinates = new double[3];
		coordinates[0] = (double) initialPosition[0] + 0.5;
		coordinates[1] = (double) initialPosition[1] + 0.5;
		coordinates[2] = (double) initialPosition[2] + 0.5;
		this.setPosition(coordinates);
	} catch (ModelException e) {
		// FIXME Auto-generated catch block. EN GAAN WE DAN GEEN DEFAULT POSITIE SETTEN?
		e.printStackTrace();
	}
	
	
	if (isValidWeight(weight))
		this.weight = weight;
	else 
		this.weight = this.minWeight;
	if (! isValidAgility(agility))
		agility = 25;
	else
		this.setAgility(agility);	
	if (! isValidStrength(strength))
		strength = 25;
	else
		this.setStrength(strength);
	if (! isValidToughness(toughness))
		toughness = 25;
	else
		this.setToughness(toughness);
	
	// FIXME niet finaal
	setHitpoints(getMaxHitpoints()-5);
	setStamina(getMaxStamina()-5);
	
	this.orientation = (Math.PI/2);
}

/* Position */
/**
 * Variable registering the position of this unit.
 */
private double[] position;

/**
 * Variable registering the cube of this Unit.
 */
private int[] cube;

/**
 * Variable registering the target cube of this Unit.
 */
private int[] targetCube;

private double[] targetPosition;


/**
 * Return the position of this unit.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position;
}

/**
 * Check whether the given position is a valid position for
 * any unit.
 *  
 * @param  position
 *         The position to check.
 * @return 
 *       | result == 
 *       // FIXME Deze check aanvullen.
*/
public static boolean isValidPosition(double[] position) {
	for (double comp : position){
		if (!isValidComponent(comp))
			return false;
	}
	return true;
}

public static boolean isValidComponent(double component){
	if ((component < 0) || (component > 50))
		return false;
	return true;
}

/**
 * Set the position of this unit to the given position.
 * 
 * @param  position
 *         The new position for this unit.
 * @post   The position of this new unit is equal to
 *         the given position.
 *       | new.getPosition() == position
 * @throws IllegalPositionException
 *         The given position is not a valid position for any
 *         unit.
 *       | ! isValidPosition(getPosition())
 */
@Raw
public void setPosition(double[] position) 
		throws ModelException {
	if (! isValidPosition(position))
		throw new ModelException();
	this.position = position;
}

public double[] getTargetPosition() {
	return this.targetPosition;
}

public void setTargetPosition(double[] targetPosition) throws ModelException {
	if (! isValidPosition(targetPosition))
		throw new ModelException();
	this.targetPosition = targetPosition;
}

/**
 * Return the cube of this unit.
 */
@Basic @Raw
public int[] getCube() {
	int[] cubeArray = new int[3];
	for (int i=0 ; i !=3 ; i++){
		cubeArray[i] = (int) position[i];
	}
	return cubeArray;
	
	// FIXME oud vs new nog eens vergelijken
//	return this.cube;
}

/**
 * Check whether the given Cube is a valid Cube for
 * any Unit.
 *  
 * @param  Cube
 *         The Cube to check.
 * @return 
 *       | result == //FIXME
*/
public static boolean isValidCube(int[] cube) {
	for (double comp : cube){
		if ((comp < 0) || (comp > cubesPerRib))
			return false;
	}
	return true;
}

//
///**
// * Set the cube of this unit to the given cube.
// * 
// * @param  cube
// *         The new cube for this unit.
// * @post   The cube of this new unit is equal to
// *         the given cube.
// *       | new.getPosition() == cube
// * @throws IllegalPositionException
// *         The given cube is not a valid position for any
// *         unit.
// *       | ! isValidPosition(getCube())
// */
//@Raw
//public void setCube(int[] cube) 
//		throws IllegalPositionException {
//	if (! isValidCube(cube))
//		throw new IllegalPositionException();
//	this.cube = cube;
//}
//
///**
// * Set the cube of this unit to the given cube.
// * 
// * @param  position
// *         The new position for this unit.
// * @post   The cube of this new unit is cube occupied
// *         by the given position.
// *       | new.getPosition() == position
// * @throws IllegalPositionException
// *         The given position is not a valid position for any
// *         unit.
// *       | ! isValidPosition(getPosition())
// */
//@Raw
//public void setCube(double[] position)
//		throws IllegalPositionException{
//	if (!isValidPosition(position))
//		throw new IllegalPositionException();
//	int[] cubeArray = new int[3];
//	cubeArray[0] = (int) position[0];
//	cubeArray[1] = (int) position[1];
//	cubeArray[2] = (int) position[2];
//	this.cube = cubeArray;
//}

/**
 * Return the cube of this unit.
 */
@Basic @Raw
public int[] getTargetCube() {
	return this.targetCube;
}

/**
 * Set the cube of this unit to the given cube.
 * 
 * @param  cube
 *         The new cube for this unit.
 * @post   The cube of this new unit is equal to
 *         the given cube.
 *       | new.getPosition() == cube
 * @throws IllegalPositionException
 *         The given cube is not a valid position for any
 *         unit.
 *       | ! isValidPosition(getCube())
 */
@Raw
public void setTargetCube(int[] cube) 
		throws ModelException {
	if (! isValidCube(cube))
		throw new ModelException();
	this.targetCube = cube;
}

private boolean isNeighbourCube(int[] otherCube){
	for (int i = 0; i != 3; i++) {
	    if (Math.abs(this.getCube()[i] - otherCube[i]) == 1)
	    	return true;
	}
	return false;
}

/* Name */
/**
 * Return the name of this unit.
 */
@Basic @Raw
public String getName() {
	return this.unitName;
}

/**
 * Check whether the given name is a valid name for
 * any unit.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == Character.isUpperCase(name.charAt(0)) && name.length() >= 2 
				&& name.matches("[a-zA-Z ']")
*/
public static boolean isValidName(String unitName) {
	return Character.isUpperCase(unitName.charAt(0)) && unitName.length() >= 2 
			&& unitName.matches("[a-zA-Z ']+");
}

/**
 * Set the name of this unit to the given name.
 * 
 * @param  unitName
 *         The new name for this unit.
 * @post   The name of this new unit is equal to
 *         the given name.
 *       | new.getName() == unitName
 * @throws IllegalArgumentException
 *         The given name is not a valid name for any
 *         unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String unitName) 
		throws IllegalArgumentException {
	if (! isValidName(unitName))
		throw new IllegalArgumentException();
	this.unitName = unitName;
}

/**
 * Variable registering the name of this unit.
 */
private String unitName;

/* Attributes */
/**
 * Return the weight of this unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Check whether the given weight is a valid weight for
 * any unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return 
 *       | result == maxWeight > weight >= (strength+agility)/2 
*/

public boolean isValidWeight(int weight) {
	return (weight >= minWeight 
			&& weight <= maxWeight);
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
 *       | else
 *       |	 new.getWeight() == minWeight
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
	else 
		this.weight = minWeight;
}

/**
 * Variable registering the weight of this unit.
 */
private int weight;
private static int maxWeight = 200;
private int minWeight = (this.getStrength() + this.getAgility())/2;

/**
 * Return the strength of this unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Check whether the given strength is a valid strength for
 * any unit.
 *  
 * @param  strength
 *         The strength to check.
 * @return 
 *       | result == 0 < unitStrength <= maxStrength
*/
public static boolean isValidStrength(int strength) {
	return (0 < strength && strength <= maxStrength);
}

/**
 * Set the strength of this unit to the given strength.
 * 
 * @param  strength
 *         The new strength for this unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
public void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}


/**
 * Variable registering the strength of this unit.
 */
private int strength;
private static int maxStrength = 200;

/**
 * Return the agility of this unit.
 */
@Basic @Raw
public int getAgility() {
	return this.agility;
}

/**
 * Check whether the given agility is a valid agility for
 * any unit.
 *  
 * @param  agility
 *         The agility to check.
 * @return 
 *       | result == 0 < agility <= maxAgility
*/
public static boolean isValidAgility(int agility) {
	return (0 < agility && agility <= maxAgility);
}

/**
 * Set the agility of this unit to the given agility.
 * 
 * @param  agility
 *         The new agility for this unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 */
@Raw
public void setAgility(int agility) {
	if (isValidAgility(agility))
		this.agility = agility;
}

/**
 * Variable registering the agility of this unit.
 */
private int agility;
private static int maxAgility = 200;

/**
 * Return the toughness of this unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Check whether the given toughness is a valid toughness for
 * any unit.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == 0 < toughness <= maxToughness
*/
public static boolean isValidToughness(int toughness) {
	return (0 < toughness && toughness <= maxToughness);
}

/**
 * Set the toughness of this unit to the given toughness.
 * 
 * @param  toughness
 *         The new toughness for this unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 */
@Raw
public void setToughness(int toughness) {
	if (isValidToughness(toughness))
		this.toughness = toughness;
}

/**
 * Variable registering the toughness of this unit.
 */
private int toughness;
private static int maxToughness = 200;

/* Points */
/**
 * Return the stamina of this unit.
 */
@Basic @Raw
public int getStamina() {
	return this.stamina;
}

/**
 * Check whether the given stamina is a valid stamina for
 * any unit.
 *  
 * @param  stamina
 *         The stamina to check.
 * @return 
 *       | result == 0 < stamina < maxStamina
*/
public boolean isValidStamina(int stamina) {
	return (0 < stamina && stamina < this.getMaxStamina());
}

/**
 * Set the stamina of this unit to the given stamina.
 * 
 * @param  stamina
 *         The new stamina for this unit.
 * @pre    The given stamina must be a valid stamina for any
 *         unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 */
@Raw
public void setStamina(int stamina) {
	assert isValidStamina(stamina);
	this.stamina = stamina;
}

/**
 * Return the maximal stamina of this unit.
 */
public int getMaxStamina() {
	int maxStamina = this.getWeight()*this.getToughness()/50;
	if ((this.getWeight()*this.getToughness())%50 == 0)
		return maxStamina;
	return maxStamina+1;
}

/**
 * Variable registering the stamina of this unit.
 */
private int stamina;

/**
 * Return the hitpoints of this Unit.
 */
@Basic @Raw
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Check whether the given hitpoints is a valid hitpoints for
 * any Unit.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return 
 *       | result == 0 < hitpoints <= getMaxHitpoints()
*/
public boolean isValidHitpoints(int hitpoints) {
	return ((0 < hitpoints) && (hitpoints <= this.getMaxHitpoints()));
}

/**
 * Set the hitpoints of this Unit to the given hitpoints.
 * 
 * @param  hitpoints
 *         The new hitpoints for this Unit.
 * @pre    The given hitpoints must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this Unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
@Raw
public void setHitpoints(int hitpoints) {
	assert isValidHitpoints(hitpoints);
	this.hitpoints = hitpoints;
}

/**
 * Variable registering the hitpoints of this Unit.
 */
private int hitpoints;
public int getMaxHitpoints() {
	int maxHitpoint = this.getWeight()*this.getToughness()/50;
	if ((this.getWeight()*this.getToughness())%50 == 0)
		return maxHitpoint;
	return maxHitpoint+1;
}

/* Time */
public void advanceTime(double tickTime) throws IllegalArgumentException, ModelException {
	if (!isValidTickTime(tickTime)){
		System.out.println(tickTime);
		throw new IllegalArgumentException();
	}
	else{
		System.out.println(activeActivity);
		this.setTime(this.currentTime + tickTime);
		if (getCurrentTime()-lastTimeRested >= 180 && this.isValidActivity("rest")){
			this.rest();
			System.out.println("3 min zijn om");
		}
	else if (this.activeActivity == null && (this.targetCube != null) && 
				!Arrays.equals(this.getCube(), this.targetCube)){
		System.out.println(Arrays.toString(this.getCube()));
		System.out.println(Arrays.toString(this.targetCube));
		System.out.println(Arrays.equals(this.getCube() , this.targetCube));
		doMoveTo();
	}
			
		else if (isWorking())
			doWork();
		else if (isResting()){
			doRest();
		}
		else if (this.isMoving()){
			try {
				doMove(tickTime, this.getSpeed());
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (this.isAttacking())
			this.doAttack();
		}
}

public boolean isValidTickTime(double tickTime) {
	return ((0 < tickTime) && (Util.fuzzyGreaterThanOrEqualTo( maxTimeLapse, tickTime)));
}

public double getCurrentTime() {
	return this.currentTime;
}

public void setTime(double time) {
	this.currentTime = time;
}

public void startNextActivity(){
	if (nextActivity == null)
		activeActivity = null;
	else if (nextActivity == "work")
		this.work();
	else if (nextActivity == "rest")
		this.rest();
	
	nextActivity = null;
}

private double currentTime;
private double maxTimeLapse = 0.2;
private String activeActivity;
private String nextActivity;
private double endTime;
private double activityStartTime;
private double lastTimeRested =0.2;

/* Basic movement */
/**
 * Variable registering the base speed of this unit.
 */
private double baseSpeed;

private double walkingSpeed;

/**
 * Return the base speed of this unit.
 */
@Basic @Raw
public double getBaseSpeed() {
	return this.baseSpeed;
}

public void setBaseSpeed(){
	this.baseSpeed = 3*(this.getStrength() + this.getAgility())/(double) (4*this.getWeight());
}

public double getWalkingSpeed() {
	return this.walkingSpeed;
}

public double getSpeed(){
	if (this.activeActivity == "move")
		return this.walkingSpeed;
	return 0;
}

public void setWalkingSpeed(double[] targetPosition) {
	if (activeActivity != "move"){
		this.walkingSpeed = 0;
	}
	else{
	double zDifference = (this.getPosition()[2] - targetPosition[2]);
	if (zDifference == -1)
		this.walkingSpeed = this.getBaseSpeed()/2;
	else if (zDifference == 1)
		this.walkingSpeed = this.getBaseSpeed()*1.2;
	else{
		this.walkingSpeed = this.getBaseSpeed();
	}
	}
}

public void moveToAdjacent(int dx, int dy, int dz)
		throws IllegalArgumentException, ModelException{
	double[] targetPosition = new double[3];
	targetPosition[0] = this.getCube()[0] + dx + .5;
	targetPosition[1] = this.getCube()[1] + dy + .5;
	targetPosition[2] = this.getCube()[2] + dz + .5;
	if (!isValidActivity("move") || !isValidPosition(targetPosition)){
		this.nextActivity = "move";
		throw new IllegalArgumentException();
	}
	if (activeActivity != "move"){
		activeActivity = "move";
		this.setTargetPosition(targetPosition);
		this.setBaseSpeed();
		this.setWalkingSpeed(targetPosition);
	}
	System.out.println("dees dan toch");
	System.out.println(this.targetPosition);
}

public void doMove(double tickTime, double speed) throws ModelException {
	System.out.println(this.getTargetPosition());
	double d = Math.sqrt(Math.pow(this.getTargetPosition()[0]-this.getPosition()[0],2)
						+Math.pow(this.getTargetPosition()[1]-this.getPosition()[1],2)
						+Math.pow(this.getTargetPosition()[2]-this.getPosition()[2],2));
	
	double movingDistance = tickTime*speed/d;
	if (Util.fuzzyGreaterThanOrEqualTo(movingDistance, 1)){
		this.setPosition(this.targetPosition);
		this.startNextActivity();
	}
	else{
	double[] newPosition = new double[3];
	for (int i=0; i != 3; i++){
		newPosition[i] = this.getPosition()[i]
				+(movingDistance*(this.getTargetPosition()[i]-this.getPosition()[i]));
	}	
	if (!Util.fuzzyEquals(this.getTargetPosition()[1], this.getPosition()[1])
			|| !Util.fuzzyEquals(this.getTargetPosition()[0], this.getPosition()[0])){
	this.orientation = Math.atan2(this.getTargetPosition()[1]-this.getPosition()[1], 
			this.getTargetPosition()[0]-this.getPosition()[0]);
	}
	this.setPosition(newPosition);
	
	}
}

public boolean isMoving(){
	if (activeActivity == "move")
		return true;
	return false;
}

/* Orientation */
/**
 * Return the orientation of this unit.
 */
@Basic @Raw
public double getOrientation() {
	return this.orientation;
}

public void faceOpponent(Unit opponent){
	this.orientation = Math.atan2(opponent.getPosition()[1] - this.getPosition()[1]
									, opponent.getPosition()[0] - this.getPosition()[0]);
}

/**
 * Variable registering the orientation of this unit.
 */
private double orientation;


/* Extended movement */
public void moveTo(int[] cube) throws ModelException{
	this.setTargetCube(cube);
	System.out.println("target set");
//	if (this.isValidActivity("move"))
//		this.activeActivity = "move";
}

public void doMoveTo() throws IllegalArgumentException, ModelException{
	
	int[] difference = new int[3];
	System.out.println("before crash");
	for (int i=0; i != 3; i++){
		if (this.getCube()[i] == this.getTargetCube()[i])
			difference[i] = 0;
		else if (this.getCube()[i] < this.getTargetCube()[i])
			difference[i] = 1;
		else {
			difference[i] = -1;
		
		}
	}
	System.out.println("starting move to adjacent");
	this.moveToAdjacent(difference[0], difference[1], difference[2]);
	
}

/* Working */
public void work(){
	if (!isValidActivity("work")){
		this.nextActivity = "work";
		throw new IllegalArgumentException();
	}
	if (activeActivity != "work"){
		activeActivity = "work";
		this.endTime = this.getCurrentTime() + 500/(double)(this.getStrength());
	}
}

public void doWork() {
	if (Util.fuzzyGreaterThanOrEqualTo(this.getCurrentTime(), endTime))
		this.startNextActivity();
}

public boolean isWorking() {
	if (this.activeActivity == "work")
		return true;
	return false;
}

/* Attacking */
public void attack(Unit unit){
	if ((unit != this) && 
		((this.getCube() == unit.getCube()) || (this.isNeighbourCube(unit.getCube()))) && 
		(!this.isAttacking())){
			
		System.out.println("attack");
		
		this.activityStartTime = this.getCurrentTime();
		this.activeActivity = "attack";
		this.faceOpponent(unit);
		unit.faceOpponent(this);
		unit.defenseAgainst(this);
		// FIXME een methode setActivieActivity maken!
	}
}

public void doAttack(){
	if (this.getCurrentTime() >= activityStartTime + 1){
		this.startNextActivity();
	}
}

public boolean isAttacking() {
	if (this.activeActivity == "attack")
		return true;
	else
		return false;
}

public boolean isUnderAttack() {
	if (this.activeActivity == "defend")
		return true;
	else
		return false;
}

public void defenseAgainst(Unit unit) {	
	System.out.println("defend");
	this.activeActivity = "defend";
	double blockChance = 0.25*(unit.getStrength() + unit.getAgility())/
						(this.getAgility() + this.getStrength());
	double dodgeChance = 0.2*unit.getAgility()/(double) this.getAgility();
	
	if (Math.random() <  dodgeChance){
		double[] newPosition = new double[3];
		int[] random = new int[3];
		do {
		for (int i=0; i != 3; i++){
			do {
				random[i] = (int) (Math.random() * 3) - 1;
				newPosition[i] = this.getPosition()[i] + random[i];
			} while (!isValidComponent(newPosition[i]));
		}
		} while (random[0] == 0 && random[1] == 0 && random[2] == 0);
		try {
			this.setPosition(newPosition);
		} catch (ModelException e) {
			System.out.println("If this happend you broke the matrix");
		}
		this.faceOpponent(unit);
		unit.faceOpponent(this);
	}
	else if (!(Math.random() < blockChance))
		this.setHitpoints(this.getHitpoints() - unit.getStrength()/10);
}

/* Resting */

private double recoverdPoints;

/**
 * Check whether the given activity is a valid activity for this unit.
 * @param  activity
 *         The activity to check.
 * @return 
 *       | result == !(this.isResting() && recoverdPoints<1)
 *       // FIXME Deze check aanvullen.
*/
private boolean isValidActivity(String activity){
	if (this.isResting() && recoverdPoints<1)
		return false;
	if (this.activeActivity == "move")
		return false;
	if (this.activeActivity == "attack")
		return false;
	return true;
}

/**
 * Set the activity of this unit to resting.
 * @post   The activity of this new unit is equal to
 *         resting.
 *       | new.isResting == True
 * @throws IllegalArgumentException
 *         Resting is not a valid activity for this unit.
 *       | ! isValitActivity("rest")
 */
public void rest() throws IllegalArgumentException{
	if (!isValidActivity("rest")){
		this.nextActivity = "rest";
		throw new IllegalArgumentException();
	}
	if (activeActivity != "rest"){
		recoverdPoints = 0;
		activityStartTime = this.getCurrentTime();
		this.activeActivity = "rest";
	}
}

private void doRest() {
	double oldRecoverdPoints = recoverdPoints;
	recoverdPoints = (this.getCurrentTime()-activityStartTime)*this.getToughness()/200/0.2;
	if (Util.fuzzyGreaterThanOrEqualTo(recoverdPoints,1)){
		lastTimeRested = getCurrentTime();
		if (hitpoints != getMaxHitpoints()){
			hitpoints = hitpoints - (int) (oldRecoverdPoints) + (int) (recoverdPoints);
			if (hitpoints > getMaxHitpoints())
				hitpoints = getMaxHitpoints();
		}
		else if (stamina != getMaxStamina()){
			stamina = stamina - (int) (oldRecoverdPoints * 2) + (int) (recoverdPoints*2);
			if (stamina > getMaxStamina())
				stamina = getMaxStamina();
		}
		if (hitpoints == getMaxHitpoints() && stamina == getMaxStamina()){
			this.startNextActivity();
		}
			
	}
}

public boolean isResting() {
	if (this.activeActivity == "rest")
		return true;
	else 
		return false;
}

/* Default behavior */

}