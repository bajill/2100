
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* factor ::= [<constant>] [<variable>] [<func call>] [<inner expr>]
 * [<negation>] */
abstract class Factor extends PascalSyntax {
    IfStatm ifStatm;
    Factor(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<factor> on line " + lineNum;
    }

    @Override void prettyPrint() {


    }

    static Factor parse(Scanner s) {
        enterParser("factor"); 
        Factor f = null;
        switch (s.curToken.kind) {

            // constant: numeric or stringliteral
            case stringValToken:
            case intValToken:
                f = Constant.parse(s);  
                break;
            
            case nameToken:
                switch (s.nextToken.kind){

                    case leftBracketToken:
                        f = Variable.parse(s);
                        break;

                    case leftParToken:
                        f = FuncCall.parse(s);
                        break;

                    default:
                        f = CharLiteral.parse(s);
                        break;
                }
                break;
            case leftParToken:
                f = InnerExpr.parse(s);
                break;

            case notToken:
                f = Negation.parse(s);
        }

        leaveParser("factor");
        return f;
    }
}
