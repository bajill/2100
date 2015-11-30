package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* variable ::= <name> ['(' <expression> [, loop] ')'] */

class Variable extends Factor {
    String id;
    Expression expression;
    int blockLevel;
    int offSet;

    Variable(String id, int lNum) {
        super(lNum);
        this.id = id; 
    }

    @Override void genCode(CodeFile f) {
    
        if (expression != null)
            expression.genCode(f);
        f.genInstr("", "movl", Integer.toString(-4 * blockLevel) + "(%ebp),%edx",
                "move variable to %edx");
        f.genInstr("", "movl", "%eax," + (-32 - (4*offSet)) + "(%edx)", id + " :=");
    }

    @Override public String identify() {
        return "<variable> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint(id + " ");
        if (expression != null) {
            Main.log.prettyPrint("[");
            expression.prettyPrint();
            Main.log.prettyPrint("]");
        } 
    }

    @Override void check(Block curscope, Library lib){
        offSet = curscope.offSet;
        blockLevel = curscope.blockLevel;
        curscope.findDecl(id, this);
        if(expression != null)
            expression.check(curscope, lib);
        PascalDecl d = curscope.findDecl(id, this);
        offSet = d.declOffset;
    }

    static Variable parse(Scanner s) {
        enterParser("variable"); 

        /* <name> */
        s.test(nameToken);
        Variable v = new Variable(s.curToken.id, s.curLineNum()); 
        s.readNextToken();

        /* <expression> */
        if(s.curToken.kind == leftBracketToken){
            s.skip(leftBracketToken);
            v.expression = Expression.parse(s);
            s.skip(rightBracketToken);
        }

        leaveParser("variable");
        return v;
    }
}
