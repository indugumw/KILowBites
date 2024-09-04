package testing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ingredient.DefaultIngredients;
import ingredient.Ingredient;
import tool.calorie.CalorieCalculation;
import tool.unit.MassUnit;
import tool.unit.VolumeUnit;

class CalorieCalculationTest
{

  @Test
  void itCalculatesCalorieswithMassUnitCustomIngredient()
  {
    // Arrange
    Double amount = 2.0;
    MassUnit unit = MassUnit.OUNCE;
    Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);

    // Act
    double calories = CalorieCalculation.calculateCalories(amount, unit, i);

    // Assert
    Assertions.assertEquals(56.69904, calories);
  }

  @Test
  void itCalculatesCalorieswithVolumeUnitCustomIngredient()
  {
    // Arrange
    Double amount = 2.0;
    VolumeUnit unit = VolumeUnit.CUP;
    Ingredient i = new Ingredient("Red Onion", "Chopped", 20.0, "gram", 100, 0.77, null);

    // Act
    double calories = CalorieCalculation.calculateCalories(amount, unit, i);

    // Assert
    Assertions.assertEquals(614.5149090909091, calories);
  }

  @Test
  void itCalculatesCalorieswithMassUnitDefaultIngredient()
  {
    // Arrange
    Double amount = 2.0;
    MassUnit unit = MassUnit.OUNCE;
    DefaultIngredients i = DefaultIngredients.ALCOHOL;

    // Act
    @SuppressWarnings("deprecation")
    double calories = CalorieCalculation.calculateCalories(amount, unit, i);

    // Assert
    Assertions.assertEquals(155.92236, calories);
  }

  @Test
  void itCalculatesCalorieswithVolumeUnitDefaultIngredient()
  {
    // Arrange
    Double amount = 2.0;
    VolumeUnit unit = VolumeUnit.CUP;
    DefaultIngredients i = DefaultIngredients.ALCOHOL;

    // Act
    double calories = CalorieCalculation.calculateCalories(amount, unit, i);

    // Assert
    Assertions.assertEquals(1027.9759028, calories);
  }

}
