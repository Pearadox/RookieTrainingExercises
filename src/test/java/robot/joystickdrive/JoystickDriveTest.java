package robot.joystickdrive;

import edu.wpi.first.hal.simulation.DriverStationDataJNI;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj.simulation.PWMSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JoystickDriveTest {

  Robot robot = new Robot();

  @Test
  @DisplayName("Test to see that robot is still when no inputs")
  public void testNoInput() throws InterruptedException {
    var leftMotor = new PWMSim(1);
    var rightMotor = new PWMSim(2);

    DriverStationDataJNI.setJoystickAxes((byte) 0, new float[] {0, 0, 0, 0, 0, 0});
    DriverStationSim.notifyNewData();
    Thread.sleep(50);

    assertEquals(0.0, leftMotor.getSpeed(), 1E-9);
    assertEquals(0.0, rightMotor.getSpeed(), 1E-9);
  }

  @Test
  @DisplayName("Test to see correct values for random joystick inputs")
  public void testRandomInputs() throws InterruptedException {

    var leftMotor = new PWMSim(1);
    var rightMotor = new PWMSim(2);

    var leftMotorTest = new PWMVictorSPX(3);
    var rightMotorTest = new PWMVictorSPX(4);
    var diffDriveRef = new DifferentialDrive(leftMotorTest, rightMotorTest);
    var leftMotorReference = new PWMSim(3);
    var rightMotorReference = new PWMSim(4);
    var rng = new Random();

    robot.robotInit();
    DriverStationSim.setEnabled(true);
    DriverStationSim.setAutonomous(false);

    robot.robotPeriodic();

    for (int i = 0; i < 20; ++i) {
      var throttle = rng.nextFloat() * 2 - 1;
      var twist = rng.nextFloat() * 2 - 1;
      DriverStationDataJNI.setJoystickAxes((byte) 0, new float[] {0, throttle, twist, 0, 0, 0});
      DriverStationSim.notifyNewData();
      Thread.sleep(50);
      robot.robotPeriodic();
      diffDriveRef.arcadeDrive(throttle, twist);
      assertEquals(leftMotorReference.getSpeed(), leftMotor.getSpeed(), 1E-9);
      assertEquals(rightMotorReference.getSpeed(), rightMotor.getSpeed(), 1E-9);
    }
    leftMotorTest.close();
    rightMotorTest.close();
    diffDriveRef.close();
  }

  @AfterEach
  public void closeResources() {
    robot.closeResources();
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
  }
}