package utils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CloseableSubsystem extends SubsystemBase implements AutoCloseable {

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
