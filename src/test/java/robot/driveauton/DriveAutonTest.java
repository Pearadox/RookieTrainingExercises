package robot.driveauton;


import edu.wpi.first.wpilibj.simulation.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriveAutonTest {

  private Robot robot = new Robot();

  @BeforeEach
  public void enable() {
    DriverStationSim.setAutonomous(true);
    DriverStationSim.setEnabled(true);
  }

  @Test
  @DisplayName("Test if robot drives for 3 seconds")
  public void testDrive() {
    SimHooks.restartTiming();
    SimHooks.pauseTiming();

    var left = new PWMSim(1);
    var right = new PWMSim(2);

    robot.robotInit();
    robot.autonomousInit();
    robot.robotPeriodic();

    for (double time = 0; time < 2.9; time += 0.02) {
      SimHooks.stepTiming((long) (0.02 * 1E6));
      robot.robotPeriodic();

      assertNotEquals(0, left.getSpeed());
      assertNotEquals(0, right.getSpeed());
    }
  }

  @Test
  @DisplayName("Test if robot stops after 3 seconds")
  public void testStop() throws InterruptedException {
    SimHooks.restartTiming();
    SimHooks.pauseTiming();

    var left = new PWMSim(1);
    var right = new PWMSim(2);


    robot.robotInit();
    robot.autonomousInit();
    robot.robotPeriodic();

    SimHooks.stepTiming(3100000);
    Thread.sleep(50);
    for (double time = 3.1; time < 15; time += 0.02) {
      SimHooks.stepTiming((long) (0.02 * 1E6));
      robot.robotPeriodic();

      assertEquals(0, left.getSpeed(), 1E-9);
      assertEquals(0, right.getSpeed(), 1E-9);
    }
  }

  @AfterEach
  public void closeResources() {
    robot.closeResources();
    CommandScheduler.getInstance().cancelAll();
  }

}