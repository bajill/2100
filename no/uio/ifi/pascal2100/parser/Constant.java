
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Constant extends Factor {
    Constant constant;
    Constant(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<constant> on line " + lineNum;
    }

    static Constant parse(Scanner s) {
        // TODO Er dette feil? Constant er en abstrakt klasse, skal ikke
        // subklassene kalles direkte
        enterParser("constant"); 
        Constant c = null;
        switch (s.curToken.kind){
            case nameToken:
                
            case intValToken:
                c = NumberLiteral.parse(s);
                break;
            case stringValToken:
                c = StringLiteral.parse(s);
                break;
        }
        leaveParser("constant");
        return c;
    }
}
