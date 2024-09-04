package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tool.unit.MassUnit;
import tool.unit.VolumeUnit;

class VolumeUnitTest {

	@Test
	void itTestsConstructor() 
	{
		// Arrange
		VolumeUnit m = VolumeUnit.QUART;
		
		// Act
		
		// Assert
		Assertions.assertEquals(946.35296, m.getMilliliters());
		Assertions.assertEquals("quarts", m.getName());
	}
	
	@Test
	void itGetsMassUnitFromString()
	{
		// Arrange
		String s = "quarts";
		
		// Act
		VolumeUnit m = VolumeUnit.fromString(s);
		
		// Assert
		Assertions.assertEquals(946.35296, m.getMilliliters());
		Assertions.assertEquals("quarts", m.getName());
	}
	
	@Test
	void itReturnsNullWhenStringDoesNotMatchUnits()
	{
		// Arrange
		String s = "shots";
		
		// Act
		VolumeUnit m = VolumeUnit.fromString(s);
		
		// Assert
		Assertions.assertNull(m);
	}
	
	@Test
	void itGetsAllNames()
	{
		// Arrange
		
		// Act
		ArrayList<String> allNames = VolumeUnit.getAllNames();
		
		// Assert
		Assertions.assertEquals("quarts", allNames.get(6));
	}


}
