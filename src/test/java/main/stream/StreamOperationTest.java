package main.stream;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamOperationTest {

  @Test
  void testStreamOperation() {

    List<String> names = List.of("Eko", "Kurniawan", "Khannedy");

    Stream<String> streamNames = names.stream();
    Stream<String> streamUpper = streamNames.map(name -> name.toUpperCase());

    streamUpper.forEach(System.out::println);

    names.forEach(System.out::println);

  }
}
