
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Type extends PascalSyntax {
    Type(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<type> on line " + lineNum;
    }

    static Type parse(Scanner s) {
        enterParser("type"); 
        
        Type t = null;
        switch (s.curToken.kind){
            case nameToken:
                t = TypeName.parse(s);
                
        }
            leaveParser("type");
        return t;
    }
}
