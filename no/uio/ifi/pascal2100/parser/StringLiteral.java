package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class StringLiteral extends Constant {
    String id;

    StringLiteral(int lNum, String id) {
        super(lNum);
        this.id = id;
    }

    @Override void genCode(CodeFile f) {
        /* if of type constdecl */
        if(id.length() == 1){
            f.genInstr("", "movl", "$" +(int)id.charAt(0) + ",%eax", "");
        }

        else{
            /* if write a string, TODO should be in procDecl??*/
            String label = f.getLocalLabel();
            f.genInstr("", ".data", "", "");
            f.genInstr(label + "", ".asciz", " \"" +id+"\"", "");
            f.genInstr("", ".align", "2", "");
            f.genInstr("", ".text", "", "");
            f.genInstr("", "leal", label +",%eax", "Addr(\"" + id + "\")");
        }
    }

    /* id expression in assignstatm are a string */
    // TODO code


    @Override public String identify() {
        return "<string literal> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("'" + id +"' ");
    }
    @Override void check(Block curscope, Library lib){
    }

    static StringLiteral parse(Scanner s) {
        enterParser("string literal"); 
        s.test(stringValToken);
        StringLiteral sl = new StringLiteral(s.curLineNum(), s.curToken.strVal);
        s.readNextToken();
        leaveParser("string literal");
        return sl;
    }
}
