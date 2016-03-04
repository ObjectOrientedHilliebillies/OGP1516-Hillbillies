package hillbillies.model;




import java.awt.Component;
import java.sql.Time;
import java.util.Random;

import org.junit.experimental.theories.Theories;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;;

// TODO Als de strength ofzo veranderd kan het zijn dat de unit zijn weight niet meer legaal is.

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
		throws IllegalArgumentException {
	/*  given enableDefaultBehavior, maximal hitpoints,
	 *  maximal stamina,
	 */
	this.setName(name);
	
	try {
		double[] coordinates = new double[3];
		coordinates[0] = (double) initialPosition[0];
		coordinates[1] = (double) initialPosition[1];
		coordinates[2] = (double) initialPosition[2];
		this.setPosition(coordinates);
	} catch (IllegalPositionException e) {
		// TODO Auto-generated catch block. EN GAAN WE DAN GEEN DEFAULT POSITIE SETTEN?
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
	
	setHitpoints(getMaxHitpoints()-30);
	
	this.orientation = (float) (Math.PI/2);
	
}

/* Position */
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
 *       // TODO Deze check aanvullen.
*/
public static boolean isValidPosition(double[] position) {
	for (double comp : position){
		if ((comp < 0) || (comp > cubesPerRib))
			return false;
	}
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
		throws IllegalPositionException {
	if (! isValidPosition(position))
		throw new IllegalPositionException();
	this.position = position;
}

/**
 * Variable registering the position of this unit.
 */
private double[] position;

/**
 * Return the Cube of this Unit.
 */
@Basic @Raw
public int[] getCube() {
	int[] cubeArray = new int[3];
	cubeArray[0] = (int) position[0];
	cubeArray[1] = (int) position[1];
	cubeArray[2] = (int) position[2];
	return cubeArray;
}

/**
 * Check whether the given Cube is a valid Cube for
 * any Unit.
 *  
 * @param  Cube
 *         The Cube to check.
 * @return 
 *       | result == //TODO
*/
public static boolean isValidCube(Cube Cube) {
	return false;
}

/**
 * Variable registering the cube of this Unit.
 */
private Cube cube;


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
	System.out.println("val = " + unitName);
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
	return (0 < stamina && stamina < maxStamina);
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
	return maxStamina;	
}

/**
 * Variable registering the stamina of this unit.
 */
private int stamina;
private int maxStamina = this.getWeight()*this.getToughness()/50;

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
public void advanceTime(double tickTime) throws IllegalArgumentException {
	if (!isValidTickTime(tickTime)){
		System.out.println(tickTime);
		throw new IllegalArgumentException();
	}
	else
		this.setTime(this.currentTime + tickTime);
		System.out.println(activeActivity);
		if (getCurrentTime()-lastTimeRested >= 180){
			activeActivity = "rest";
			System.out.println("3 min zijn om");
		}
//		if (isUnderAttack()){
//			activeActivity = "default";
//			lastActivity = "default";
//			doDefend();
//		}
		else if (isWorking())
			doWork();
//		else if (isAttacking())
//			work();
		else if (isResting()){
			doRest();
		}
}

public boolean isValidTickTime(double tickTime) {
	return ((0 < tickTime) && (tickTime < maxTimeLapse));
}

public double getCurrentTime() {
	return this.currentTime;
}

public void setTime(double time) {
	this.currentTime = time;
}

private double currentTime;
private double maxTimeLapse = 0.2;
private String activeActivity;
private String lastActivity;
private double endTime;
private double activityStartTime;
private double lastTimeRested =0.2;

/* Basic movement */

/* Orientation */
/**
 * Return the orientation of this unit.
 */
@Basic @Raw
public Float getOrientation() {
	return this.orientation;
}

/**
 * Check whether the given orientation is a valid orientation for
 * any unit.
 *  
 * @param  orientation
 *         The orientation to check.
 * @return 
 *       | result == 
 *       // TODO Ik weet weer niet wat hier moet.
*/
public static boolean isValidOrientation(Float orientation) {
	return false;
}

/**
 * Set the orientation of this unit to the given orientation.
 * 
 * @param  orientation
 *         The new orientation for this unit.
 * @post   If the given orientation is a valid orientation for any unit,
 *         the orientation of this new unit is equal to the given
 *         orientation.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 */
@Raw
public void setOrientation(float orientation) {
	if (isValidOrientation(orientation))
		this.orientation = orientation;
}

/**
 * Variable registering the orientation of this unit.
 */
private float orientation;


/* Extended movement */

/* Working */
public void work(){
	if (isValidActivity("work"))
		this.activeActivity = "work";
}

public void doWork() {
	// Waarom was hier al het tijdwerk in float gedaan?
	if (!this.wasWorking()){
		this.endTime = this.getCurrentTime() + (double)500/this.getStrength();
		this.lastActivity = "work";
	}
	if (this.getCurrentTime() >= endTime)
		this.lastActivity = "default";
}

public boolean isWorking() {
	if (this.activeActivity == "work")
		return true;
	return false;
}

public boolean wasWorking() {
	if (this.lastActivity == "work")
		return true;
	return false;
}

/* Attacking */
public void attack(Unit unit) {
	if ((this.getCube() == unit.getCube()) 
			|| (this.getCube().isNeighbourBlock(unit.getCube())))
		if (!this.isAttacking())
			activityStartTime = (float)this.getCurrentTime();
		this.activityStatus = "attack";
		if ((float)this.getCurrentTime() >= activityStartTime + 1)
			this.activityStatus = "default";
		else 
			unit.defenseAgainst(this);
			unit.activityStatus = "defend";
}

public boolean isAttacking() {
	if (this.activityStatus == "attack")
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
	this.activityStatus = "defend";
	// First step: try to dodge
	double dodgeChance = 0.2*unit.getAgility()/this.getAgility();
	double blockChance = 0.25*(unit.getStrength() + unit.getAgility())/
			(this.getAgility() + this.getStrength());
	if (Math.random() <= dodgeChance)
		double xCoord = Vector.getXCoord(); // iets vinden om dat random te maken
	else if (Math.random() <= blockChance)
		// er gebeurt niets
		this.defenseAgainst(unit);
	else
		this.setHitpoints(this.getHitpoints() - unit.getStrength()/10);
}

/* Resting */

private double recoverdPoints = 0;

/**
 * Check whether the given activity is a valid activity for this unit.
 * @param  activity
 *         The activity to check.
 * @return 
 *       | result == 
 *       // TODO Deze check aanvullen.
*/
private boolean isValidActivity(String activity){
	System.out.println("go");
	if (activity == "defending")
		return true;
	System.out.println("nog aan het pitten?");
	if (this.isResting() && recoverdPoints<1)
		return false;
	
	System.out.println("Heeft het geen zin?");
	if (activity == "rest" && getCurrentTime()-lastTimeRested < 180 && 
			hitpoints == getMaxHitpoints() && stamina == getMaxStamina())
		return false;
	System.out.println("alles ok");
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
	if (!isValidActivity("rest"))
		throw new IllegalArgumentException();
	this.activeActivity = "rest";
	if (!wasResting()){
		// Time starts so we can detect if the user changes his mind in 0.2 sec and wants to change
		// (wish is not allowed)
		activityStartTime = this.getCurrentTime();
	}
}

public void doRest() {
	if (!this.wasResting()){
		System.out.println("begin te rusten");
		activityStartTime = this.getCurrentTime();
		recoverdPoints = 0;
		this.lastActivity = "rest";
	}
	double oldRecoverdPoints = recoverdPoints;
	recoverdPoints = (this.getCurrentTime()-activityStartTime)*this.getToughness()/200/0.2;
	if (recoverdPoints>1){
		lastTimeRested = getCurrentTime();
		if (hitpoints != getMaxHitpoints())
			hitpoints = hitpoints - (int) (oldRecoverdPoints) + (int) (recoverdPoints);
		else if (stamina != getMaxStamina())
			stamina = stamina - (int) (oldRecoverdPoints * 2) + (int) (recoverdPoints*2);
		if (hitpoints == getMaxHitpoints() && stamina == getMaxStamina())
			activeActivity = "default";
	}
}

public boolean isResting() {
	if (this.activeActivity == "rest")
		return true;
	else 
		return false;
}

public boolean wasResting() {
	if (this.lastActivity == "rest")
		return true;
	else
		return false;
}

/* Default behavior */
}