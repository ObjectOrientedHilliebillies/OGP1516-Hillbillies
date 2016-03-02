package hillbillies.model;


// unit has a position, occupied block (defensive), 
//			  name (defensive), 
//			  weight, strength, agility, toughness (total)
//			  stamina, hitpoints (nominal)		
//			  orientation (total)
//			  interaction with game world (defensive)

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
 */

public Unit(int weight, int strength, int agility, int toughness, String Name)
		throws IllegalArgumentException {
	if (! isValidWeight(weight))
		weight = (strength+agility)/2;
	setWeight(weight);
	if (! isValidStrength(strength))
		strength = 25;
	setStrength(weight);
	
	this.setName(Name);
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
<<<<<<< HEAD
public boolean isValidWeight(int weight) {
<<<<<<< HEAD
	return (weight >=(this.getStrength() +this.getStrength())/2 && weight <= maxWeight);
=======
public boolean isValidWeight(int unitWeight) {
	return (unitWeight >=(this.getStrength() +this.getAgility())/2);
>>>>>>> origin/master
=======
	return (weight >=(this.getStrength() + this.getAgility())/2 && weight <= maxWeight);
>>>>>>> refs/remotes/origin/Jonas
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

 */

/**
 * Initialize this new unit with given stamina.
 * 
 * @param  stamina
 *         The stamina for this new unit.
 * @pre    The given stamina must be a valid stamina for any unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this new unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 */
public Unit(int stamina) {
	this.setStamina(stamina);
}

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
private int maxStamina = this.getWeight()*this.getToughness()/50;

// 	NOG NIET MET TEMPLATES


///**
// * 
// * @return unit's position
// */
//@Basic @Immutable
//public double[] getPosition() {
//	return this.position;
//}
//
///**
// * 
// * @return block occupied by unit
// */
//@Basic @Immutable
//public int[] getOccupiedBlock() {
//	return this.occupiedBlock;
//}

private double currentTime;
private final double increment = 0.2;

public void advanceTime(double currentTime) {
	this.currentTime = currentTime + this.increment;
}

public double getTime() {
	return this.currentTime;
}


public int hitpoints;

public int getMaxHitpoints() {
	return 200*getWeight()/100*getToughness()/100;
}


public int getHitpoints() {
	assert this.hitpoints <= this.getMaxHitpoints();
	return this.hitpoints;
}






//public void rest() {
//	if (this.hitpoints != this.getMaxHitpoints() && (!this.isUnderAttack()) )
//		this.hitpoints = this.hitpoints + this.toughness/200;
//	else if (this.stamina != this.getMaxStamina() && (!this.isUnderAttack()))
//		this.stamina = this.stamina + this.toughness/100;
}