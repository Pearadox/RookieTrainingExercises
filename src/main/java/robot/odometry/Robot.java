package robot.odometry;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Robot extends TimedRobot {
  // NOTE: DO NOT MODIFY THIS CLASS

  private Command autonomousCommand;

  private RobotContainer robotContainer;

  /**
   * Robot-wide initialization code should go here.
   *
   * <p>Users should override this method for default Robot-wide initialization which will be called
   * when the robot is first powered on. It will be called exactly one time.
   *
   * <p>Warning: the Driver Station "Robot Code" light and FMS "Robot Ready" indicators will be off
   * until RobotInit() exits. Code in RobotInit() that waits for enable will cause the robot to
   * never indicate that the code is ready, causing the robot to be bypassed in a match.
   */
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
  }

  /**
   * Initialization code for autonomous mode should go here.
   *
   * <p>Users should override this method for initialization code which will be called each time the
   * robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutonomousCommand();

    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /**
   * Periodic code for all robot modes should go here.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  // Don't touch this
  public void closeResources() {
    var fields = Arrays.stream(this.getClass().getDeclaredFields()).filter(
        a -> !a.getType().isAssignableFrom(AutoCloseable.class)
    ).toArray();

    for (var field: fields) {
      try {
        ((AutoCloseable) ((Field) field).get(this)).close();
      } catch (Exception ignored) {}
    }
  }
}
