package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import utility.SysUtil;

class SysUtilTest {
	
	@Test
    void itGetsDirectoryFromRoot() {
        // Arrange
		String filepath = "res/recipe";
        File expectedDirectory = new File("res/recipe");

        // Act
        File actualDirectory = SysUtil.getDirectoryFromRoot(filepath);

        // Assert
        assertEquals(expectedDirectory, actualDirectory);
        assertTrue(actualDirectory.isDirectory());
    }
	
	@Test
	void itGetsDirectoryFromExports() {
		JFileChooser fileChooser = new JFileChooser();
		try
	      {
	        fileChooser.setCurrentDirectory(SysUtil.getDirectoryFromExports("Shopping Lists"));
	      }
	      catch (NullPointerException npe)
	      {
	        fileChooser.setCurrentDirectory(SysUtil.getDirectoryFromRoot("Exports/Shopping Lists"));
	      }
	}
	
	@Test
	void itConstructsClass() {
		SysUtil utility = new SysUtil();
	}

}
