
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class TypeName extends Type {
    String name;
    TypeName(String name, int lNum) {
        super(lNum);
        this.name = name;
    }


    @Override public String identify() {
        return "<type name> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }

    static TypeName parse(Scanner s) {
        enterParser("type name"); 
        s.test(nameToken);
        TypeName tm = new TypeName(s.curToken.id, s.curLineNum());
        s.readNextToken();
        leaveParser("type name");
        return tm;
    }
}
