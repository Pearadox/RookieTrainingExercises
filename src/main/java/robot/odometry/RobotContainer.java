package robot.odometry;

import edu.wpi.first.wpilibj2.command.Command;
import robot.odometry.subsystems.Drivetrain;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RobotContainer implements AutoCloseable {

  // Don't modify this class
  Drivetrain drivetrain = new Drivetrain();

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
