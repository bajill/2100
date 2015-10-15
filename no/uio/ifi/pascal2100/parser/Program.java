package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/*<program> ::= ’program’ <name> ’;’ <block> ’.’*/
public class Program extends PascalDecl {
    // Block progBlock;
    Program(String id, int lNum) {
        super(id, lNum);
    }
    @Override public String identify() {
        return "<program> " + name + " on line " + lineNum;
    }
}
