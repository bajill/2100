
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class ArrayType extends Type{
    Type type;
    Type additionalType;
    ArrayType(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<array-type> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    // DONE, BUT WORKING?
    static ArrayType parse(Scanner s) {
        enterParser("array-type"); 
        s.skip(arrayToken);
        s.skip(leftBracketToken);
        ArrayType at = new ArrayType(s.curLineNum());

        at.type = Type.parse(s);
        s.skip(rightBracketToken);
        System.out.println("Arraytype ;" + s.curToken.kind);

        s.skip(ofToken);
        at.additionalType = Type.parse(s); 

        leaveParser("array-type");
        return at;
    }
}
