package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/*<program> ::= ’program’ <name> ’;’ <block> ’.’*/
public class Program extends PascalDecl {
    Block progBlock;
    Program(String id, int lNum) {
        super(id, lNum);
    }
    @Override public String identify() {
        return "<program> " + name + " on line " + lineNum;

    }

    public static Program parse(Scanner s) {
        enterParser("program");
        s.skip(programToken);
        s.test(nameToken);
        Program p = new Program(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(semicolonToken);
        //System.out.println("1. " + s.curToken.identify());
        p.progBlock = Block.parse(s); // p.progBlock.context = p;
        //System.out.println("2. " + s.curToken.identify());
        s.skip(dotToken);
        //System.out.println("3. " + s.curToken.identify());
        
        s.skip(eofToken);
        leaveParser("program");
        return p;
    }

}
