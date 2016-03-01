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
 * @invar  The strength of each unit must be a valid strength for any
 *         unit.
 *       | isValidStrength(getStrength())
 * @invar  The agility of each unit must be a valid agility for any
 *         unit.
 *       | isValidAgility(getAgility())
 *       
 * @invar  The toughness of each unit must be a valid toughness for any
 *         unit.
 *       | isValidToughness(getToughness())
 *       
 * @version 1.0
 * @author  Jonas Vantrappen & Victor Van Eetvelt
 */

public class Unit {


	/**
 * Initialize this new unit with given weight. 
 * 
 * @param  unitWeight
 *         The weight for this new unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight. Otherwise, the weight of this new unit is equal
 *         to (strength+agility)/2.
 *       | if (isValidWeight(unitWeight))
 *       |   then new.getWeight() == unitWeight
 *       |   else new.getWeight() == (strength+agility)/2
 *       
 * Initialize this new unit with given strength.
 * 
 * @param  unitStrength
 *         The strength for this new unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength. Otherwise, the strength of this new unit is equal
 *         to 25.
 *       | if (isValidStrength(unitStrength))
 *       |   then new.getStrength() == unitStrength
 *       |   else new.getStrength() == 25
 *       
 * Initialize this new unit with given agility.
 * 
 * @param  unitAgility
 *         The agility for this new unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility. Otherwise, the agility of this new unit is equal
 *         to 25.
 *       | if (isValidAgility(unitAgility))
 *       |   then new.getAgility() == unitAgility
 *       |   else new.getAgility() == 25
 *       
 * Initialize this new unit with given toughness.
 * 
 * @param  unitToughness
 *         The toughness for this new unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness. Otherwise, the toughness of this new unit is equal
 *         to 25.
 *       | if (isValidToughness(unitToughness))
 *       |   then new.getToughness() == unitToughness
 *       |   else new.getToughness() == 25
 */

public Unit(int unitWeight, int unitStrength, int unitAgility, int unitToughness, String unitName)
		throws IllegalArgumentException {
	if (! isValidWeight(unitWeight))
		unitWeight = (unitStrength+unitAgility)/2;
	setWeight(unitWeight);
	if (! isValidStrength(unitStrength))
		unitStrength = 25;
	setStrength(unitWeight);
	
	this.setName(unitName);
}

/**
 * Return the weight of this unit.
 */
@Basic @Raw
public int getWeight() {
	return this.unitWeight;
}

/**
 * Check whether the given weight is a valid weight for
 * any unit.
 *  
 * @param  unitWeight
 *         The weight to check.
 * @return 
 *       | result == unitWeight >= (unitStrength+unitAgility)/2
*/
public boolean isValidWeight(int unitWeight) {
	return (unitWeight >=(this.getStrength() +this.getAgility())/2);
}

/**
 * Set the weight of this unit to the given weight.
 * 
 * @param  unitWeight
 *         The new weight for this unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight.
 *       | if (isValidWeight(unitWeight))
 *       |   then new.getWeight() == unitWeight
 */
@Raw
public void setWeight(int unitWeight) {
	if (isValidWeight(unitWeight))
		this.unitWeight = unitWeight;
}

/**
 * Variable registering the weight of this unit.
 */
private int unitWeight;
	

/**
 * Return the strength of this unit.
 */
@Basic @Raw
public int getStrength() {
	return this.unitStrength;
}

/**
 * Check whether the given strength is a valid strength for
 * any unit.
 *  
 * @param  unitStrength
 *         The strength to check.
 * @return 
 *       | result == unitStrength <= 200
*/
public static boolean isValidStrength(int unitStrength) {
	return unitStrength <= 200;
}

/**
 * Set the strength of this unit to the given strength.
 * 
 * @param  unitStrength
 *         The new strength for this unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength.
 *       | if (isValidStrength(unitStrength))
 *       |   then new.getStrength() == unitStrength
 */
@Raw
public void setStrength(int unitStrength) {
	if (isValidStrength(unitStrength))
		this.unitStrength = unitStrength;
}

/**
 * Variable registering the strength of this unit.
 */
private int unitStrength;


/**
 * Return the agility of this unit.
 */
@Basic @Raw
public int getAgility() {
	return this.unitAgility;
}

/**
 * Check whether the given agility is a valid agility for
 * any unit.
 *  
 * @param  unitAgility
 *         The agility to check.
 * @return 
 *       | result == unitAgility <= 200
*/
public static boolean isValidAgility(int unitAgility) {
	return unitAgility <= 200;
}

/**
 * Set the agility of this unit to the given agility.
 * 
 * @param  unitAgility
 *         The new agility for this unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility.
 *       | if (isValidAgility(unitAgility))
 *       |   then new.getAgility() == unitAgility
 */
@Raw
public void setAgility(int unitAgility) {
	if (isValidAgility(unitAgility))
		this.unitAgility = unitAgility;
}

/**
 * Variable registering the agility of this unit.
 */
private int unitAgility;


/**
 * Return the toughness of this unit.
 */
@Basic @Raw
public int getToughness() {
	return this.unitToughness;
}

/**
 * Check whether the given toughness is a valid toughness for
 * any unit.
 *  
 * @param  unitToughness
 *         The toughness to check.
 * @return 
 *       | result == unitToughness <= 200
*/
public static boolean isValidToughness(int unitToughness) {
	return unitToughness <= 200;
}

/**
 * Set the toughness of this unit to the given toughness.
 * 
 * @param  unitToughness
 *         The new toughness for this unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(unitToughness))
 *       |   then new.getToughness() == unitToughness
 */
@Raw
public void setToughness(int unitToughness) {
	if (isValidToughness(unitToughness))
		this.unitToughness = unitToughness;
}

/**
 * Variable registering the toughness of this unit.
 */
private int unitToughness;

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
			&& unitName.matches("[a-zA-Z]+");
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


public int stamina;
public int hitpoints;

public int getMaxHitpoints() {
	return 200*getWeight()/100*getToughness()/100;
}

public int getMaxStamina() {
	return 200*getWeight()/100*getToughness()/100;
}

public int getHitpoints() {
	assert this.hitpoints <= this.getMaxHitpoints();
	return this.hitpoints;
}

public int getStamina() {	
	assert this.stamina <= this.getMaxStamina();
	return this.stamina;
}




//public void rest() {
//	if (this.hitpoints != this.getMaxHitpoints() && (!this.isUnderAttack()) )
//		this.hitpoints = this.hitpoints + this.toughness/200;
//	else if (this.stamina != this.getMaxStamina() && (!this.isUnderAttack()))
//		this.stamina = this.stamina + this.toughness/100;
}