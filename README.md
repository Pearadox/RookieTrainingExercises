# RookieTrainingExercises
Rookie training exercises for 5414 Pearadox Rookies wanting to do some programming.

## Prerequisites:

- Git
- A suitable IDE/Text Editor (Visual Studio Code, IntelliJ, or your preferred)
- Java 11 JDK


## Instructions:

1. Fork this repository using GitHub.
2. Clone the forked repository using Git.
3. Find the exercise you want to work on, and complete it.
4. To test, run `./gradlew test` to run tests on all exercises. Run `./gradlew test --tests '<exercisepackage>*'` to test one specific project. To test only Java-only or Java+WPILib exercises, run `./gradlew test --tests '<javaonly|robot>*'`
5. You can review results in the terminal, or from the generated HTML report.

### Exercise List:

#### Java-only:
- Printing: Exercise to print to a console output.
- AutoAim: Exercise to implement a simple P+arbFF loop.
- FizzBuzzConditional: Implement only the conditional for FizzBuzz.
- ConvertGameData: Implement a method that converts 2020 INFINITE RECHARGE game data into a color string. 

#### Robot:
- DriveForward: Exercise to drive a TimedRobot forward based on a Joystick button input.
- JoystickDrive: Exercise to drive a Command-based robot using the arcadeDrive control scheme.
- DriveAuton: Exercise to drive a Command-based robot off the initiation line in autonomous.
- FlywheelStates: Exercise to implement a state machine (flywheel) with WPILib and command-based.
