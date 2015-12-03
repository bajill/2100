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

        /* if variable is a type */
        if(scope.findDecl(id, this) instanceof EnumLiteral){
            if(id.equals("false")){
            f.genInstr("", "movl", "$0,%eax", "  enum value false (=0)");
            }
            if(id.equals("true")){
            f.genInstr("", "movl", "$1,%eax", "  enum value true (=1)");
            }
        }

        /* if variable is a parameter in proc */        
        else if(scope.findDecl(id, this) instanceof ParamDecl){
            PascalDecl pd = scope.findDecl(id, this);
            f.genInstr("", "movl", (-4*pd.declLevel) + "(%ebp),%edx", "");
            f.genInstr("", "movl", "" + (4 + (pd.declOffset*4)) +
                    "(%edx),%eax", "  " +id);
        }
                        
        /* if const */
        else if(scope.findDecl(id, this) instanceof ConstDecl){
            scope.findDecl(id, this).genCode(f);
            return;
        }

        /* if variable is an array */
        else if (expression != null)
            expression.genCode(f);

        /* if variable is var(??) */
        else {
        f.genInstr("", "movl", (-4*blockLevel) + "(%ebp),%edx", "");
        f.genInstr("", "movl", "-" + ((32) + offSet*(4)) + "(%edx),%eax", id);
        }
        
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

        // Lagt til scope for å ha tilgang til variabler
        scope = curscope;

        PascalDecl d = curscope.findDecl(id, this);
        if(expression != null)
            expression.check(curscope, lib);

        offSet = d.declOffset;
        blockLevel = d.declLevel;
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
