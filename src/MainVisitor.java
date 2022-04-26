import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream; import java.math.BigDecimal;

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

  public static void main(String[] args) throws Exception {
    var is = CharStreams.fromStream(System.in);
    var lexer = new CalculatorLexer(is);
    var tokens = new CommonTokenStream(lexer);
    var parser = new CalculatorParser(tokens);
    
    var tree = parser.expr();
    var eval = new Visitor(); 
    System.out.println(eval.visit(tree));
  }
}
