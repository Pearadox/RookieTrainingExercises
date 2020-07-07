package robot.odometry;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import robot.odometry.subsystems.Drivetrain;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class OdometryTest {

  Robot robot = new Robot();

  @BeforeEach
  public void enable() {
    DriverStationSim.setEnabled(true);
  }

  @Test
  @DisplayName("Test that odometry works correctly with random encoder values")
  public void testRandom() throws IllegalAccessException, NoSuchFieldException {
    var left = EncoderSim.createForChannel(1);
    var right = EncoderSim.createForChannel(3);
    var gyro = new AnalogGyroSim(0);
    var rng = new Random();

    left.setCount(0);
    right.setCount(0);

    robot.robotInit();

    robot.robotPeriodic();

    var robotContainerField = robot.getClass().getDeclaredField("robotContainer");
    robotContainerField.setAccessible(true);
    var robotContainer = robotContainerField.get(robot);
    var drivetrainField = robotContainer.getClass().getDeclaredField("drivetrain");
    drivetrainField.setAccessible(true);
    var drivetrain = (Drivetrain) drivetrainField.get(robotContainer);

    var testOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0), new Pose2d(3, 2, new Rotation2d()));
    var testEncoderCounts = 0;
    var testGyroAngle = 0.0d;

    testOdometry.update(Rotation2d.fromDegrees(testGyroAngle),
        testEncoderCounts / 1024.0,
        testEncoderCounts / 1024.0);

    for (int i = 0; i < 10; ++i) {
      int count = (int) (rng.nextDouble() - 0.5 * 1024);
      left.setCount(left.getCount() + count);
      right.setCount(right.getCount() + count);
      testEncoderCounts += count;

      var angle = rng.nextDouble() * 10 - 5;
      gyro.setAngle(gyro.getAngle() + angle);
      testGyroAngle += angle;

      testOdometry.update(Rotation2d.fromDegrees(testGyroAngle),
          testEncoderCounts / 1024.0,
          testEncoderCounts / 1024.0);
      robot.robotPeriodic();
      assertEquals(testOdometry.getPoseMeters(), drivetrain.getPose());
    }
  }

  @AfterEach
  public void closeResources() {
    robot.closeResources();
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
  }

}
