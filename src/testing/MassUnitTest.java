package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tool.unit.MassUnit;

class MassUnitTest 
{

	@Test
	void itTestsConstructor() 
	{
		// Arrange
		MassUnit m = MassUnit.DRAM;
		
		// Act
		
		// Assert
		Assertions.assertEquals(1.771845, m.getGrams());
		Assertions.assertEquals("drams", m.getName());
	}
	
	@Test
	void itGetsMassUnitFromString()
	{
		// Arrange
		String s = "drams";
		
		// Act
		MassUnit m = MassUnit.fromString(s);
		
		// Assert
		Assertions.assertEquals(1.771845, m.getGrams());
		Assertions.assertEquals("drams", m.getName());
	}
	
	@Test
	void itReturnsNullWhenStringDoesNotMatchUnits()
	{
		// Arrange
		String s = "shots";
		
		// Act
		MassUnit m = MassUnit.fromString(s);
		
		// Assert
		Assertions.assertNull(m);
	}
	
	@Test
	void itGetsAllNames()
	{
		// Arrange
		
		// Act
		ArrayList<String> allNames = MassUnit.getAllNames();
		
		// Assert
		Assertions.assertEquals("drams", allNames.get(0));
	}

}
