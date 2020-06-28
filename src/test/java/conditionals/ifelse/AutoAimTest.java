package conditionals.ifelse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AutoAimTest {

  @Test
  @DisplayName("Test to see if autoAim() returns 0 if the error is within the tolerance")
  public void testWithinTolerance() {
    var rng = new Random();
    for (int i = 0; i < 10; ++i) {
      var error = rng.nextDouble() * (0.0999 * 2) - 0.0999;
      assertEquals(0.0d, AutoAim.autoAim(error), 1E-9);
    }
  }

  @Test
  @DisplayName("Test to see if autoAim() returns correct values for positive errors")
  public void testPositiveErrors() {
    for (double error = 0.15; error < 1; error += 0.1) {
      assertEquals(5 * error + 0.5, AutoAim.autoAim(error), 1E-9);
    }
  }

  @Test
  @DisplayName("Test to see if autoAim() returns correct values for negative errors")
  public void testNegativeErrors() {
    for (double error = -1; error < -0.15; error += 0.1) {
      assertEquals(5 * error - 0.5, AutoAim.autoAim(error), 1E-9);
    }
  }
}