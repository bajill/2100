
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
class FuncCall extends Factor {
    CharLiteral name;
    ArrayList<Expression> expression;
    FuncCall(int lNum) {
    super(lNum);
    expression = new ArrayList<Expression>();
    }

    
    @Override public String identify() {
    return "<func call> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static FuncCall parse(Scanner s) {
        enterParser("func call"); 
        FuncCall fc = new FuncCall(s.curLineNum());
        fc.name = CharLiteral.parse(s);
        s.skip(leftParToken);
        while(true){
            fc.expression.add(Expression.parse(s));
            if(s.curToken.kind == commaToken)
                s.skip(commaToken);
            else
                break;
        }
        s.skip(rightParToken);
        leaveParser("func call");
        return fc;
    }
}
