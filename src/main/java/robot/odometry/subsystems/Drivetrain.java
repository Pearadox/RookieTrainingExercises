package robot.odometry.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import utils.CloseableSubsystem;

public class Drivetrain extends CloseableSubsystem {

  /*
   * You will use WPILib's odometry classes as well as an AnalogGyro and two encoders.
   * Don't worry about encoder gear ratio or wheel diameter or any of that. We'll assume 1024 ticks is one meter.
   *
   * The left drivetrain encoder will have channelA = 1, channelB = 2
   * The right encoder will have channelA = 3, channelB = 4
   *
   *
   * The gyro will be on channel 0
   *
   * The odometry should start at x = 2, y = 3, and an angle of 0.
   *
   * Don't worry about any of the motors or whatever.
   */

  // IMPLEMENT THIS
  public Pose2d getPose() {
    return null;
  }

  @Override
  public void periodic() {

  }
}
