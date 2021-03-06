package hillbillies.model;




import java.util.Random;

// unit has a position, occupied block (defensive), 
//			  name (defensive), 
//			  weight, strength, agility, toughness (total)
//			  stamina, hitpoints (nominal)		
//			  orientation (total)
//			  interaction with game world (defensive)

// COMMENTS
// 	TODO Ik denk dat we omslachtig zijn geweest door altijd setVariable te gebruiken in plaats van gewoon this.variable = variable. 

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;;

/**
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
 *       
 * @version 1.0
 * @author  Jonas Vantrappen & Victor Van Eetvelt
 */

public class Unit {


	/**
 * Initialize this new unit with given weight. 
 * 
 * @param  weight
 *         The weight for this new unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight. Otherwise, the weight of this new unit is equal
 *         to (strength+agility)/2.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == (strength+agility)/2
 *       
 * Initialize this new unit with given strength.
 * 
 * @param  strength
 *         The strength for this new unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength. Otherwise, the strength of this new unit is equal
 *         to 25.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 25
 *       
 * Initialize this new unit with given agility.
 * 
 * @param  agility
 *         The agility for this new unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility. Otherwise, the agility of this new unit is equal
 *         to 25.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == 25
 *       
 * Initialize this new unit with given toughness.
 * 
 * @param  toughness
 *         The toughness for this new unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness. Otherwise, the toughness of this new unit is equal
 *         to 25.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == 25
 *       
 * Initialize this new unit with given stamina.
 * 
 * @param  stamina
 *         The stamina for this new unit.
 * @pre    The given stamina must be a valid stamina for any unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this new unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 *       
 * 
 * Initialize this new Unit with given hitpoints.
 * 
 * @param  hitpoints
 *         The hitpoints for this new Unit.
 * @pre    The given hitpoints must be a valid hitpoints for any Unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this new Unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
public Unit(int weight, int strength, int agility, int toughness, String Name, 
		int stamina, int hitpoints)
		throws IllegalArgumentException {
//	if (! isValidWeight(weight))
//		weight = (strength+agility)/2;
//	else
		this.setWeight(weight);
//	if (! isValidStrength(strength))
//		strength = 25;
//	else
		this.setStrength(weight);
//	if (! isValidAgility(agility))
//		agility = 25;
//	else
		this.setAgility(agility);
//	if (! isValidToughness(toughness))
//		toughness = 25;
//	else
		this.setToughness(toughness);
	
	this.setName(Name);
	this.setStamina(stamina);
	this.setHitpoints(hitpoints);
}

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
	return (weight >=(this.getStrength() + this.getAgility())/2 
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
 *       |	 new.getWeight() == defaultWeight
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
	else 
		this.weight = defaultWeight;
}

/**
 * Variable registering the weight of this unit.
 */
private int weight;
private static int maxWeight = 200;
private int defaultWeight = (this.getStrength() + this.getAgility())/2;

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
 * Variable registering the stamina of this unit.
 */
private int stamina;
public int getMaxStamina() {
	return this.getWeight()*this.getToughness()/50;
}

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
	return this.getWeight()*this.getToughness()/50;
}


public void advanceTime(double timeLapse) throws IllegalArgumentException {
	if (!isValidTimeLapse(timeLapse))
		throw new IllegalArgumentException();
	else
		this.setTime(this.currentTime + timeLapse);
		if (isSprinting())
		else if (isMoving())
		else if (isWorking())
		else if (isAttacking())
		else if (isResting())
}

public boolean isValidTimeLapse(double timeLapse) {
	return ((0 < timeLapse) && (timeLapse < maxTimeLapse));
}

public double getCurrentTime() {
	return this.currentTime;
}

public void setTime(double time) {
	this.currentTime = time;
}

private double currentTime;
private double maxTimeLapse;

public void work() {
	if (!this.isWorking())
		startTime = (float)this.getCurrentTime();
	this.activityStatus = "work";
	float endTime = startTime + (float)500/this.getStrength();
	if ((float)this.getCurrentTime() >= endTime)
		this.activityStatus = "default";
	else
		work();
}

public boolean isWorking() {
	if (this.activityStatus == "work")
		return true;
	else
		return false;
}

public void attack(Unit unit) {
	if ((this.getCube() == unit.getCube()) 
			|| (this.getCube().isNeighbourBlock(unit.getCube())))
		if (!this.isAttacking())
			startTime = (float)this.getCurrentTime();
		this.activityStatus = "attack";
		if ((float)this.getCurrentTime() >= startTime + 1)
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
	if (this.activityStatus == "defend")
		return true;
	else
		return false;
}

public void rest() {
	this.activityStatus = "rest";
	if (!this.isResting())
		startTime = (float)this.getCurrentTime();
	if (this.getCurrentTime() >= startTime + 0.2)
		this.activityStatus = "default";
		if (this.getHitpoints() < this.getMaxHitpoints() && (!this.isUnderAttack()) )
			this.setHitpoints(this.getHitpoints() + this.getToughness()/200);
		else if (this.getStamina() < this.getMaxStamina() && (!this.isUnderAttack()))
			this.setStamina(this.getStamina() + this.getToughness()/100);
	else
		rest();
	
}

public boolean isResting() {
	if (this.activityStatus == "rest")
		return true;
	else
		return false;
}

public String activityStatus;
public float startTime;

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

// 	NOG NIET MET TEMPLATES

// TODO Dit op de juiste plek zetten
/** TO BE ADDED TO CLASS HEADING
 * @invar  The position of each unit must be a valid position for any
 *         unit.
 *       | isValidPosition(getPosition())
 */


/**
 * Initialize this new unit with given position.
 *
 * @param  position
 *         The position for this new unit.
 * @effect The position of this new unit is set to
 *         the given position.
 *       | this.setPosition(position)
 */
public Unit(Vector position)
		throws IllegalPositionException {
	this.setPosition(position);
}


/**
 * Return the position of this unit.
 */
@Basic @Raw
public Vector getPosition() {
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
public static boolean isValidPosition(Vector position) {
	return position.inBorders();
	// TODO Een test maken om dit uit te testen
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
public void setPosition(Vector position) 
		throws IllegalPositionException {
	if (! isValidPosition(position))
		throw new IllegalPositionException();
	this.position = position;
}

/**
 * Variable registering the position of this unit.
 */
private Vector position;


/**
 * Return the Cube of this Unit.
 */
@Basic @Raw
public Cube getCube() {
	return this.getPosition().getCube();
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

// TODO Dit moet weer vanboven komen.
/**
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 */

/**
 * Initialize this new unit with given orientation.
 * 
 * @param  orientation
 *         The orientation for this new unit.
 * @post   If the given orientation is a valid orientation for any unit,
 *         the orientation of this new unit is equal to the given
 *         orientation. Otherwise, the orientation of this new unit is equal
 *         to PI/2.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 *       |   else new.getOrientation() == PI/2
 */
public Unit(float orientation) {
	if (! isValidOrientation(orientation))
		orientation = (float) (Math.PI/2);
	setOrientation(orientation);
}

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


}