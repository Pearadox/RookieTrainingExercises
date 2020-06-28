package joystickdrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RobotContainer implements AutoCloseable {
  /*
   * Create a Drivetrain subsystem using WPILib's DifferentialDrive class and two PWMVictorSPXs on ports 1 and 2.
   *
   * NOTE: Change SubsystemBase in your subsystem to CloseableSubsystem, or else the tests won't work correctly.
   *
   * Use a Joystick on Port 0 in order to call arcadeDrive in a Command that runs indefinitely, requires the drivetrain,
   * and calls the arcadeDrive() method in WPILib's DifferentialDrive class. Then set that command as the Drivetrain's
   * default command. Use Joystick's getY() as the xSpeed, and use getZ() as zRotation().
   *
   * BONUS: Use RunCommand() and lambdas to inline it without creating a Command subclass.
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
