package javaonly.printing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;

public class PrintingTest {

  @Test
  @DisplayName("Test to see if you printed 'Hello Java' using System.out.println()")
  public void helloJavaTest() {
    var stdout = new ByteArrayOutputStream();

    System.setOut(new PrintStream(stdout));

    Printing.print();

    var output = stdout.toString().replaceAll("\r", "");
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

    Assertions.assertEquals("Hello Java\n", output, "You did not print 'Hello Java' to standard output");
  }

}
