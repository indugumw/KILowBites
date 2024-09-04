package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.Ingredient;
import tool.unit.UnitConverter;

class UnitConverterTest 
{

	@Test
	void nullIngredientMassFromUnit() 
	{
		// Arrange
		String fromAmount = "2";
		String fromUnit = "ounces";
		String toUnit = "pounds";
		Ingredient i = null;
		
		// Act
		String s = UnitConverter.calculate(fromAmount, fromUnit, toUnit, i);
		
		// Assert
		Assertions.assertEquals("0.1", s);
	}
	
	@Test
	void nullIngredientVolumeFromUnit() 
	{
		// Arrange
		String fromAmount = "2";
		String fromUnit = "pints";
		String toUnit = "cups";
		Ingredient i = null;
		
		// Act
		String s = UnitConverter.calculate(fromAmount, fromUnit, toUnit, i);
		
		// Assert
		Assertions.assertEquals("4.0", s);
	}
	
	@Test
	void nonNullIngredientMassFromUnit() 
	{
		// Arrange
		String fromAmount = "2";
		String fromUnit = "ounces";
		String toUnit = "cups";
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		
		// Act
		String s = UnitConverter.calculate(fromAmount, fromUnit, toUnit, i);
		
		// Assert
		Assertions.assertEquals("0.3", s);
	}
	
	@Test
	void nonNullIngredientVolumeFromUnit() 
	{
		// Arrange
		String fromAmount = "2";
		String fromUnit = "pints";
		String toUnit = "ounces";
		Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);
		
		// Act
		String s = UnitConverter.calculate(fromAmount, fromUnit, toUnit, i);
		
		// Assert
		Assertions.assertEquals("25.7", s);
	}

}
