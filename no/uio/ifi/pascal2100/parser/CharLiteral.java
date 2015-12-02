package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class CharLiteral extends Constant{
    String name;

    CharLiteral(String name, int lNum) {
        super(lNum);
        this.name = name;
    }

    @Override void genCode(CodeFile f) {
        /* char from write */
        f.genInstr("", "pushl", "%eax", "");
        f.genInstr("", "call", "write_char", "");
        f.genInstr("", "addl", "$4,%esp", "");

        /* const */ 
        // TODO code 
    }
    

    @Override public String identify() {
        return "<char literal> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(name + " ");
    }
    @Override void check(Block curscope, Library lib){
        curscope.findDecl(name, this);
    }

    static CharLiteral parse(Scanner s) {
        enterParser("char literal"); 
        CharLiteral cl = new CharLiteral(s.curToken.id, s.curLineNum());
        s.readNextToken();
        leaveParser("char literal");
        return cl;
    }
}
