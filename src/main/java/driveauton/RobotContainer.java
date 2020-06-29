package driveauton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RobotContainer implements AutoCloseable {
  /*
   * You will be using the same setup as in the Joystick Drive exercise. You may copy and paste your drivetrain and your
   * joystick code, as the requirements will stay the same. However, you may want to change the name of the subsystem
   * unless you are comfortable enough with packages to determine which Drivetrain.java is which.
   *
   * Make sure you use CloseableSubsystem instead SubsystemBase.
   *
   * This time, you should implement an autonomous command that drives forward for 3 seconds.
   */

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {

  }

  public Command getAutonomousCommand() {
    return null;
  }


  // DO NOT TOUCH
  @Override
  public void close() throws Exception {
    var fields = Arrays.stream(this.getClass().getDeclaredFields()).filter(
        a -> !a.getType().isAssignableFrom(AutoCloseable.class)
    ).toArray();

    for (var field: fields) {
      ((AutoCloseable) ((Field) field).get(this)).close();
    }
  }
}
