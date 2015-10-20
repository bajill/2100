
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* factor ::= [<constant>] [<variable>] [<func call>] [<inner expr>]
 * [<negation>] */
abstract class Factor extends PascalSyntax {
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
            // numeric or stringliteral

            case stringValToken:
            case intValToken:
                System.out.println("Factor hit?");
                f = Constant.parse(s);  break;
            // Hvis factor er variable
            case nameToken:
                System.out.println("NameToken");
            // TODO func call
            // TODO inner expr
            // TODO negation
                //st = IfStatm.parse(s);  break;
        } 
        leaveParser("factor");
        System.out.println("factor ut " + f);
        return f;
    }
}
