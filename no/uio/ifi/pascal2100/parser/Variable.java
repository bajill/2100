
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class Variable extends Factor {
    String id;
    Variable(int lNum, String id) {
        super(lNum);
        this.id = id; 
    }


    @Override public String identify() {
        return "<variable> on line " + lineNum;
    }
    
    static Variable parse(Scanner s) {
        enterParser("variable"); 
        s.test(nameToken);

        Variable v = new Variable(s.curLineNum(), s.curToken.id); 
        s.readNextToken();
        leaveParser("variable");
        return v;
    }
}
