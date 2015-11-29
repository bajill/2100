package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class EnumLiteral extends PascalDecl{

    EnumLiteral(String name, int lNum) {
        super(name, lNum);
    }

    @Override void genCode(CodeFile f) {
    }
    

    @Override public String identify() {
        return "<enum literal> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name);
    }
    @Override void check(Block curscope, Library lib){
        curscope.findDecl(name, this);
    }

    static EnumLiteral parse(Scanner s) {
        enterParser("enum literal"); 
        EnumLiteral el = new EnumLiteral(s.curToken.id, s.curLineNum());
        s.readNextToken();
        leaveParser("enum literal");
        return el;
    }
}
