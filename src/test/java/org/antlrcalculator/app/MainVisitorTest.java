package org.antlrcalculator.app;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainVisitorTest 
{
  private static BigDecimal run(String input) {
    return MainVisitor.run(new ByteArrayInputStream(input.getBytes()));
  }
  
  @ParameterizedTest
  @MethodSource("simpleNumbers")
  void evaluateMathExpressions(String expression, BigDecimal expectedResult) {
    var result = run(expression);
    assertEquals(expectedResult, result);
  }

  public Stream<Arguments> simpleNumbers() {
    return Stream.of(
        Arguments.of("0", BigDecimal.valueOf(0)),
        Arguments.of("1", BigDecimal.valueOf(1)),
        Arguments.of("10", BigDecimal.valueOf(10)),
        Arguments.of("1.0", BigDecimal.valueOf(1.0)),
        Arguments.of("2.0", BigDecimal.valueOf(5.0)),
        Arguments.of("5", BigDecimal.valueOf(5)),
        Arguments.of("6", BigDecimal.valueOf(6)),
        Arguments.of("7", BigDecimal.valueOf(7)),
        Arguments.of("7", BigDecimal.valueOf(0))
    );
  }
}

