package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class RelOperator extends Operator{

    RelOperator(String name, int lNum) {
        super(name, lNum);
    }
    @Override void genCode(CodeFile f) {
    }

    @Override public String identify() {
        return "<rel operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + " "); 
    }
    @Override void check(Block curscope, Library lib){
    }

    static RelOperator parse(Scanner s) {
        enterParser("rel operator"); 
        RelOperator ro = new RelOperator(s.curToken.kind.toString(), s.curLineNum());
        s.readNextToken();
        leaveParser("rel operator");
        return ro;
    }
}
