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
    Block scope;

    Variable(String id, int lNum) {
        super(lNum);
        this.id = id; 
    }

    @Override void genCode(CodeFile f) {

        /* if variable are a parameter in proc */        
        if(scope.decls.get(id) instanceof ParamDecl){
            f.genInstr("", "movl", (-4*blockLevel) + "(%ebp),%edx", "");
            f.genInstr("", "movl", "" + (8 +
                        (scope.decls.get(id).paramOffset*4)) + "(%edx),%eax",
                    "  " +id);
        }
                        
        // TODO denne slår ut på param også
        /* if const */
        if(offSet == 0){
            System.out.println("\nvariable-"+scope.decls.get(id));
            scope.decls.get(id).genCode(f);
            return;
        }

        // TODO denne burde kanskje være instanceof, hva nå dette er 
        if (expression != null)
            expression.genCode(f);

        /* if variable are var(??) */
        f.genInstr("", "movl", (-4*blockLevel) + "(%ebp),%edx", "");
        f.genInstr("", "movl", "-" + ((32) + offSet*(4)) + "(%edx),%eax", id);
        
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

        // Lagt til scope for å ha tilgang til variabler
        scope = curscope;

        PascalDecl d = curscope.findDecl(id, this);
        if(expression != null)
            expression.check(curscope, lib);

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
