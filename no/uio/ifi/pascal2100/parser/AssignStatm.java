
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class AssignStatm extends Statement {
    Variable variable;
    Expression expression;
    AssignStatm(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<assign statm> on line " + lineNum;
    }


    @Override void prettyPrint() {

        variable.prettyPrint();
        Main.log.prettyPrint(":= ");
        expression.prettyPrint();
    }

    static AssignStatm parse(Scanner s) {
        enterParser("assign statm"); 
        AssignStatm as = new AssignStatm(s.curLineNum());
        as.variable = Variable.parse(s);
        s.skip(assignToken);
        as.expression = Expression.parse(s);
        leaveParser("assign statm");
        return as;
    }
}
