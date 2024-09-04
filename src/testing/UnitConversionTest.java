package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.DefaultIngredients;
import ingredient.Ingredient;
import tool.unit.MassUnit;
import tool.unit.UnitConversion;
import tool.unit.VolumeUnit;

class UnitConversionTest 
{

	@Test
	public void convertWeightToWeight() 
	{
		// Arrange
		double amount = 2.0;
		MassUnit og = MassUnit.OUNCE;
		MassUnit newUnit = MassUnit.POUND;
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit);
		
		// Assert
		Assertions.assertEquals("0.1", convert);
	}
	
	@Test
	void convertVolumeToVolume() 
	{
		// Arrange
		double amount = 2.0;
		VolumeUnit og = VolumeUnit.TABLESPOON;
		VolumeUnit newUnit = VolumeUnit.CUP;
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit);
		
		// Assert
		Assertions.assertEquals("0.1", convert);
	}
	
	@Test
	void convertWeightToVolumeDefaultIngredient() 
	{
		// Arrange
		double amount = 4.0;
		VolumeUnit og = VolumeUnit.TABLESPOON;
		MassUnit newUnit = MassUnit.POUND;
		DefaultIngredients i  = DefaultIngredients.ALCOHOL;
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit, i);
		
		// Assert
		Assertions.assertEquals("0.1", convert);
	}
	
	@Test
	void convertVolumetoWeightDefaultIngredient() 
	{
		// Arrange
		double amount = 4.0;
		MassUnit og = MassUnit.POUND;
		VolumeUnit newUnit = VolumeUnit.TABLESPOON;
		DefaultIngredients i  = DefaultIngredients.ALCOHOL;
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit, i);
		
		// Assert
		Assertions.assertEquals("155.3", convert);
	}
	
	@Test
	void convertWeightToVolumeCustomIngredient() 
	{
		// Arrange
		double amount = 4.0;
		VolumeUnit og = VolumeUnit.TABLESPOON;
		MassUnit newUnit = MassUnit.POUND;
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit, i);
		
		// Assert
		Assertions.assertEquals("0.1", convert);
	}
	
	@Test
	void convertVolumetoWeightCustomIngredient() 
	{
		// Arrange
		double amount = 4.0;
		MassUnit og = MassUnit.POUND;
		VolumeUnit newUnit = VolumeUnit.TABLESPOON;
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		
		// Act
		String convert = UnitConversion.convert(amount, og, newUnit, i);
		
		// Assert
		Assertions.assertEquals("159.4", convert);
	}

}
