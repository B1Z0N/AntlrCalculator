package org.antlrcalculator.app;

import java.io.Console;
import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.function.Function;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MainVisitorTest 
{
  private static BigDecimal run(String input) {
    try {
      return MainVisitor.run(input);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    
    // not reachable, just to suppress warnings
    return null;
  }
  
  // one and only test
  
  @ParameterizedTest
  @MethodSource({"numbers", "unaryOps", "parentheses", "addSub", "mulDiv", "misc"})
  public void shouldBeEqual(String expression, BigDecimal expectedResult) {
    var result = run(expression);
    var msg = String.join("", "(!) '", expression, "' should be equal to '", 
      expectedResult.toString(), "', but it's equal to '", result.toString(), "'");
    assertTrue(expectedResult.compareTo(result) == 0, msg);
  }

  // and lots of cases

  private static Stream<Arguments> numbers() {
    return Stream.of(
        Arguments.of("0", BigDecimal.valueOf(0)),
        Arguments.of("1", BigDecimal.valueOf(1)),
        Arguments.of("10", BigDecimal.valueOf(10)),
        Arguments.of("1.0", BigDecimal.valueOf(1.0)),
        Arguments.of("2.539", BigDecimal.valueOf(2.539)),
        Arguments.of("52.23e1", new BigDecimal("52.23e1")),
        Arguments.of("52.23e-1", new BigDecimal("52.23e-1")),
        Arguments.of("52.23e+1", new BigDecimal("52.23e1")),
        Arguments.of("52.00e+1", new BigDecimal("52.0e1")),
        Arguments.of("52e+1", new BigDecimal("52E1")),
        Arguments.of("33E1", new BigDecimal("33e1")),
        Arguments.of("33.5E1", new BigDecimal("33.5e1"))
    );
  }

  private static Stream<Arguments> unaryOps() {
    var neg = numbers()
      .map(args -> {
        var expr = (String) args.get()[0];
        var expected = (BigDecimal) args.get()[1];
        return Arguments.of("-" + expr, expected.negate());
      });
    var pos = numbers()
      .map(args -> {
        var expr = (String) args.get()[0];
        var expected = (BigDecimal) args.get()[1];
        return Arguments.of("+" + expr, expected);
      });
    return Stream.concat(neg, pos);
  }

  private static Stream<Arguments> parentheses() {
    Function<Arguments, Arguments> parenthesize = args -> {
      var expr = (String) args.get()[0];
      var expected = (BigDecimal) args.get()[1];
      return Arguments.of("(" + expr + ")", expected);
    };

    var numbers = Stream.concat(
      numbers().map(parenthesize), 
      unaryOps().map(parenthesize)
    );
      
    var misc = Stream.of(
      Arguments.of("(1)", BigDecimal.valueOf(1)),
      Arguments.of("(1+2)", BigDecimal.valueOf(3)),
      Arguments.of("(1+2)*3", BigDecimal.valueOf(9)),
      Arguments.of("(1.5+2.5)*3", BigDecimal.valueOf(12)),
      Arguments.of("(1.5+2.5)*3.5", BigDecimal.valueOf(14.0)),
      Arguments.of("(1.5+2.5)*3.5e-1", BigDecimal.valueOf(1.4)),
      Arguments.of("(5 - -5)", BigDecimal.valueOf(10)),
      Arguments.of("(5 - -5)*2", BigDecimal.valueOf(20)),
      Arguments.of("(5 - +5)*2", BigDecimal.valueOf(0)),
      Arguments.of("(5-5)*2", BigDecimal.valueOf(0))
    );

    return Stream.concat(numbers, misc);
  }

  private static Stream<Arguments> addSub() {
    return Stream.of(
      Arguments.of("1+2", BigDecimal.valueOf(3)),
      Arguments.of("1-2", BigDecimal.valueOf(-1)),
      Arguments.of("1-5+6", BigDecimal.valueOf(2)),
      Arguments.of("0+0-0", BigDecimal.valueOf(0))
    );
  }

  private static Stream<Arguments> mulDiv() {
    return Stream.of(
      Arguments.of("1*2", BigDecimal.valueOf(2)),
      Arguments.of("1/2", BigDecimal.valueOf(0.5)),
      Arguments.of("1/2*3", BigDecimal.valueOf(1.5)),
      Arguments.of("1/2e-1*3/4", BigDecimal.valueOf(3.75)),
      Arguments.of("0*0", BigDecimal.valueOf(0)),
      Arguments.of("0*5.0", BigDecimal.valueOf(0)),
      Arguments.of("5e5*0", BigDecimal.valueOf(0)),
      Arguments.of("0 / 1", BigDecimal.valueOf(0))
    );
  }

  private static Stream<Arguments> misc() {
    return Stream.of(
      Arguments.of("1-(5 - -5)", BigDecimal.valueOf(-9)),
      Arguments.of("1--(5 - -5)", BigDecimal.valueOf(11))
    );
  }
}

