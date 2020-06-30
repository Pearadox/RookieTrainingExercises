package robot.driveforward;

import edu.wpi.first.hal.simulation.DriverStationDataJNI;
import edu.wpi.first.wpilibj.simulation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriveRobotTest {
  Robot robot = new Robot();

  @Test
  @DisplayName("Test to see if robot does not move when there is no input")
  public void testNoInput() throws InterruptedException {
    var roboRIO = new RoboRioSim(0);

    var ds = new DriverStationSim();

    var leftMotor = new PWMSim(1);
    var rightMotor = new PWMSim(2);

    DriverStationDataJNI.setJoystickButtons((byte) 1, 0b0, 12);
    ds.notifyNewData();
    Thread.sleep(50);
    for (double currentTime = 0; currentTime < 5; currentTime += 0.02) {
      robot.teleopPeriodic();
      assertEquals(0.0, leftMotor.getSpeed());
      assertEquals(0.0, rightMotor.getSpeed());
    }

  }

  @Test
  @DisplayName("Test to see if robot moves when Button 1 is pressed")
  public void testButton1() throws InterruptedException {
    var roboRIO = new RoboRioSim(0);

    var ds = new DriverStationSim();

    var leftMotor = new PWMSim(1);
    var rightMotor = new PWMSim(2);

    DriverStationDataJNI.setJoystickButtons((byte) 1, 0b1, 12);
    ds.notifyNewData();
    Thread.sleep(50);
    for (double currentTime = 0; currentTime < 5; currentTime += 0.02) {
      robot.teleopPeriodic();

      assertNotEquals(0.0, leftMotor.getSpeed());
      assertNotEquals(0.0, rightMotor.getSpeed());
    }
  }

  @Test
  @DisplayName("Test to see if robot stops when Button 1 is released")
  public void testButtonReleased() throws InterruptedException {
    var roboRIO = new RoboRioSim(0);

    var ds = new DriverStationSim();

    var leftMotor = new PWMSim(1);
    var rightMotor = new PWMSim(2);

    DriverStationDataJNI.setJoystickButtons((byte) 1, 0b1, 12);
    ds.notifyNewData();
    Thread.sleep(50);
    for (double currentTime = 0; currentTime < 5; currentTime += 0.02) {
      robot.teleopPeriodic();
      assertNotEquals(0.0, leftMotor.getSpeed());
      assertNotEquals(0.0, rightMotor.getSpeed());
    }
    DriverStationDataJNI.setJoystickButtons((byte) 1, 0b0, 12);
    ds.notifyNewData();
    Thread.sleep(50);
    for (double currentTime = 0; currentTime < 5; currentTime += 0.02) {
      robot.teleopPeriodic();
      assertEquals(0.0, leftMotor.getSpeed(), 1E-9);
      assertEquals(0.0, rightMotor.getSpeed(), 1E-9);
    }
  }

  @AfterEach
  public void closeResources() {
    robot.closeResources();
  }

}