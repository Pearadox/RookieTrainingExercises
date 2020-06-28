package conditionals.ifelse;

public class AutoAim {

  public static double autoAim(double errorRad) {
    /*
     * One of the many things that we need to do in FRC is automatically align to a target. If we have vision of a
     * target, we can typically estimate the angle error from the robot to the target. We then can apply a voltage v
     * to the right side of the drivetrain, and -v to the left side of our drivetrain based on the angle error.
     *
     * This error is CCW positive. So, if the target is to our left, and we can turn CCW to aim at the target, then
     * it is a positive error.
     *
     * In order to calculate the voltage v, add the kP (5) times the error to the kArbFF (0.5) with the sign of the
     * error if the error is greater than 0.1 radians. If the error's magnitude is less than 0.1 radians, then return 0.
     */

    return 0;
  }
}
