package javaonly.fizzbuzzconditional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzConditionalTest {

  @Test
  @DisplayName("Test if fizzBuzzConditonal() returns Fizz for multiples of 3")
  public void testMultiplesOf3() {
    assertEquals("Fizz", FizzBuzzConditional.fizzBuzzConditional(9));

    assertEquals("Fizz", FizzBuzzConditional.fizzBuzzConditional(12));

    assertEquals("Fizz", FizzBuzzConditional.fizzBuzzConditional(2147483646));
  }

  @Test
  @DisplayName("Test if fizzBuzzConditonal() returns Buzz for multiples of 5")
  public void testMultiplesOf5() {
    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(10));

    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(200));

    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(2147483645));
  }

  @Test
  @DisplayName("Test if fizzBuzzConditonal() returns FizzBuzz for multiples of 3 and 5")
  public void testMultiplesOf3And5() {
    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(75));

    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(150));

    assertEquals("Buzz", FizzBuzzConditional.fizzBuzzConditional(2147483640));
  }

  @Test
  @DisplayName("Test if fizzBuzzConditional() returns n as a string if n isn't a multiple of 3 or 5")
  public void testNonMultiples() {
    assertEquals("74", FizzBuzzConditional.fizzBuzzConditional(74));

    assertEquals("2", FizzBuzzConditional.fizzBuzzConditional(2));

    assertEquals("2147483641", FizzBuzzConditional.fizzBuzzConditional(2147483641));
  }



}
