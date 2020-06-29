package robot.flywheelstates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RobotContainer implements AutoCloseable {
  /*
   * This time, you will be making a simple state machine for a Flywheel. The Flywheel should have 4 states:
   * - Off (0)
   * - Power Port (0.25)
   * - Initiation Line (0.5)
   * - Trench Run (0.75)
   *
   * This Flywheel subsystem should have a PWMVictorSPX in port 3.
   * You will need a Joystick with port 0.
   * Bind button 1 to power port, button 2 to initiation line, and button 3 to trench run, setting the flywheel speed
   * to the number beside the state. When any button is released, then set the flywheel state to off.
   *
   * Make sure you use CloseableSubsystem instead SubsystemBase.
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
