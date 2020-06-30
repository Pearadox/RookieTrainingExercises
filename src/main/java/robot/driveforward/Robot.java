package robot.driveforward;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Robot extends TimedRobot {

  /*
   * In here, make two motors and a DifferentialDrive class, as well as a Joystick.
   * Steps:
   * 1. Make two motor controllers (using PWMVictorSPX) on ports 1 and 2.
   * 2. Make a DifferentialDrive object using both of those motor controllers
   * 3. Make a Joystick using the Joystick class (with port 0)
   *
   */


  @Override
  public void teleopPeriodic() {
    /*
     * In here, make it so that if you press the button 1 on the joystick, it
     * makes the drivetrain move. You can do so by calling tankDrive and putting in inputs for both sides.
     *
     * joystick.getRawButton() will get any button's state on the joystick.
     */
  }

  // DO NOT TOUCH
  public void closeResources() {
    var fields = Arrays.stream(this.getClass().getDeclaredFields()).filter(
        a -> !a.getType().isAssignableFrom(AutoCloseable.class)
    ).toArray();

    for (var field: fields) {
      try {
        ((Field) field).setAccessible(true);
        ((AutoCloseable) ((Field) field).get(this)).close();
      } catch (Exception ignored) {}
    }
  }

}
