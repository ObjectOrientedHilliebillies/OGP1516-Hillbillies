package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {	
		
		return null;
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		return unit.getPosition().getVector();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return unit.getCube().getIntCube();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {unit.setName(newName);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHitpoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getHitpoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getStamina();
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}
	 
}

/*
* Consult the <a href=
* "http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">
* Java tutorial</a> for more information on interfaces, if necessary.
*/
