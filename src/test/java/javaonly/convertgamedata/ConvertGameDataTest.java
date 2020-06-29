package javaonly.convertgamedata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertGameDataTest {

  @Test
  @DisplayName("Test to see if null and empty strings are handled")
  public void testNullAndEmpty() {
    assertEquals("No Color", ConvertGameData.convertGameData(""));

    assertEquals("No Color", ConvertGameData.convertGameData(null));
  }

  @Test
  @DisplayName("Test to see if invalid strings are handled")
  public void testInvalid() {
    assertEquals("Invalid Color", ConvertGameData.convertGameData("b"));

    assertEquals("Invalid Color", ConvertGameData.convertGameData("null"));
  }


}