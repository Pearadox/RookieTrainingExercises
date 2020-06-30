package robot.flywheelstates;


import edu.wpi.first.hal.simulation.DriverStationDataJNI;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj.simulation.PWMSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlywheelStateTest {

  Robot robot = new Robot();
  RoboRioSim roboRioSim = new RoboRioSim(0);
  DriverStationSim ds = new DriverStationSim();

  @BeforeEach
  public void enable() {
    ds.setEnabled(true);
    ds.setAutonomous(false);
  }

  @Test
  @DisplayName("Test that flywheel state is off when no buttons assigned are pressed")
  public void testNoInput() throws InterruptedException {
    var flywheel = new PWMSim(3);

    DriverStationDataJNI.setJoystickButtons((byte) 0, 0b0, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotInit();
    robot.robotPeriodic();

    for (double t = 0; t < 5; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.0, flywheel.getSpeed(), 1E-9);
    }

    DriverStationDataJNI.setJoystickButtons((byte) 0, 0xFFF << 3, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();

    for (double t = 0; t < 5; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.0, flywheel.getSpeed(), 1E-9);
    }
  }

  @Test
  @DisplayName("Test that flywheel state is power port when you press button 1")
  public void testPowerPort() throws InterruptedException {
    var flywheel = new PWMSim(3);

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotInit();
    robot.robotPeriodic();
    for (double t = 0; t < 5; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.25, flywheel.getSpeed(), 1E-9);
    }
  }

  @Test
  @DisplayName("Test that flywheel state is initiation line when you press button 2")
  public void testInitiationLine() throws InterruptedException {
    var flywheel = new PWMSim(3);

    robot.robotInit();

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1 << 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 5; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.5, flywheel.getSpeed(), 1E-9);
    }
  }

  @Test
  @DisplayName("Test that flywheel state is trench when you press button 3")
  public void testTrench() throws InterruptedException {
    var flywheel = new PWMSim(3);

    robot.robotInit();

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1 << 2, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();

    for (double t = 0; t < 5; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.75, flywheel.getSpeed(), 1E-9);
    }
  }

  @Test
  @DisplayName("Test that flywheel state transitions when you switch buttons")
  public void testTransition() throws InterruptedException {
    var flywheel = new PWMSim(3);

    robot.robotInit();

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1 << 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.5, flywheel.getSpeed(), 1E-9);
    }

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1 << 2, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.75, flywheel.getSpeed(), 1E-9);
    }

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.25, flywheel.getSpeed(), 1E-9);
    }
  }

  @Test
  @DisplayName("Test that flywheel turns off on button release")
  public void testRelease() throws InterruptedException {
    var flywheel = new PWMSim(3);

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1 << 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotInit();
    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.5, flywheel.getSpeed(), 1E-9);
    }

    DriverStationDataJNI.setJoystickButtons((byte) 0, 0, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.0, flywheel.getSpeed(), 1E-9);
    }

    DriverStationDataJNI.setJoystickButtons((byte) 0, 1, 12);
    ds.notifyNewData();
    Thread.sleep(50);

    robot.robotPeriodic();
    for (double t = 0; t < 2; t += 0.02) {
      robot.robotPeriodic();
      assertEquals(0.25, flywheel.getSpeed(), 1E-9);
    }
  }



  @AfterEach
  public void closeResources() {
    robot.closeResources();
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
  }

}