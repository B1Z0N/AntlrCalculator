package org.antlrcalculator.app;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlrcalculator.app.core.parser.*;

import java.io.InputStream;
import java.math.BigDecimal;

public class MainVisitor {
  private static class Visitor extends CalculatorBaseVisitor<BigDecimal> {
    @Override 
    public BigDecimal visitMulDiv(CalculatorParser.MulDivContext ctx) { 
      var left = visit(ctx.left);
      var right = visit(ctx.right);
      if (ctx.op.getType() == CalculatorParser.MUL) {
          return left.multiply(right);
      } else {
          return left.divide(right);
      }
    }

    @Override
    public BigDecimal visitAddSub(CalculatorParser.AddSubContext ctx) { 
      var left = visit(ctx.left);
      var right = visit(ctx.right);
      if (ctx.op.getType() == CalculatorParser.PLUS) {
          return left.add(right);
      } else {
          return left.subtract(right);
      }
    }

    @Override
    public BigDecimal visitUnary(CalculatorParser.UnaryContext ctx) { 
      var value = ctx.expr();       
      if (ctx.op.getType() == CalculatorParser.MINUS) {
        return visit(value).negate();
      } else {
        return visit(value);
      }
    }

	  @Override 
    public BigDecimal visitParens(CalculatorParser.ParensContext ctx) { 
      return visit(ctx.expr());
    }

	  @Override 
    public BigDecimal visitNumber(CalculatorParser.NumberContext ctx) { 
      return new BigDecimal(ctx.NUM().getText());
    }
  }

  public static BigDecimal run(InputStream input) {
    var is = CharStreams.fromStream(input);
    var lexer = new CalculatorLexer(is);
    var tokens = new CommonTokenStream(lexer);
    var parser = new CalculatorParser(tokens);
    
    var tree = parser.expr();
    var eval = new Visitor();
    System.out.println(eval.visit(tree));
  }

  public static void main(String[] args) throws Exception {
    System.out.println(run(System.in));
  }
}
