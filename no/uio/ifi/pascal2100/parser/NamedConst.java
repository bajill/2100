package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class NamedConst extends Constant{
    String name;

    NamedConst(String name, int lNum) {
        super(lNum);
        this.name = name;
    }

    @Override void genCode(CodeFile f) {
    }
    

    @Override public String identify() {
        return "<named constant> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + " ");
    }
    @Override void check(Block curscope, Library lib){
        curscope.findDecl(name, this);
    }

    static NamedConst parse(Scanner s) {
        enterParser("named constant"); 
        NamedConst nc = new NamedConst(s.curToken.id, s.curLineNum());
        s.readNextToken();
        leaveParser("named constant");
        return nc;
    }
}
