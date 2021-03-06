package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class NumberLiteral extends Constant {
    int numValue;

    NumberLiteral(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {
        /* from const, assignstatm*/
        f.genInstr("", "movl", "$" + numValue + ",%eax", "  " + numValue);
        
        /* from write proccall */
        // TODO code in procCall
    }

    @Override void check(Block curscope, Library lib){
    }
    @Override public String identify() {
        return "<numeric literal> on line " + lineNum;
    }

    @Override void prettyPrint(){
        Main.log.prettyPrint(Integer.toString(numValue) + " ");
    }

    static NumberLiteral parse(Scanner s) {
        enterParser("numeric literal"); 
        NumberLiteral nl = new NumberLiteral(s.curLineNum());
        s.test(intValToken);
        nl.numValue = s.curToken.intVal;
        s.readNextToken();
        leaveParser("numeric literal");
        return nl;
    }
}
