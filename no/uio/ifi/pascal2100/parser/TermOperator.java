package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class TermOperator extends Operator {

    TermOperator(String name, int lNum) {
        super(name, lNum);
    }

    @Override void genCode(CodeFile f) {
    }

    @Override public String identify() {
        return "<term operator> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + " ");
    }

    @Override void check(Block curscope, Library lib){
    }
    static TermOperator parse(Scanner s) {
        enterParser("term operator"); 
        TermOperator tm = new TermOperator(s.curToken.kind.toString(), s.curLineNum());
        s.readNextToken();
        leaveParser("term operator");
        return tm;
    }
}
