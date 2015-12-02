package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class RelOperator extends Operator{

    RelOperator(String name, int lNum) {
        super(name, lNum);
    }
    @Override void genCode(CodeFile f) {
        System.out.println("reloperator " +name);
        if(name == "<")
            f.genInstr("", "setl","%al", "Test <");
        if(name == ">")
            f.genInstr("", "setg","%al", "Test >");
        if(name == "<=")
            f.genInstr("", "setle","%al", "Test <=");
        if(name == ">=")
            f.genInstr("", "setge","%al", "Test >=");
        if(name == "=")
            f.genInstr("", "sete","%al", "Test =");
        if(name == "<>")
            f.genInstr("", "setne","%al", "Test <>");

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
