package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Operator extends PascalSyntax {
    String name; 

    Operator(String name, int lNum) {
        super(lNum);
        this.name = name;
    }

    @Override abstract void genCode(CodeFile f);

    @Override public String identify() {
        return "<operator> on line " + lineNum;
    }

    static Operator parse(Scanner s) {
        enterParser("operator"); 
        leaveParser("operator");
        return null;
    }
}
