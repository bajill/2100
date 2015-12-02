package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class AssignStatm extends Statement {
    Variable variable;
    Expression expression;

    AssignStatm(int lNum) {
        super(lNum);
    }

    @Override void genCode(CodeFile f) {
        expression.genCode(f);

        /* if variable are a funcreturn */
        if(variable.scope.findDecl(variable.id, this) instanceof FuncDecl){
            f.genInstr("", "movl", "%eax, -32(%ebp)", "" + variable.id + " :=");
        }
        
        /* if regular assign statement */
        else{
            f.genInstr("", "movl", (-4 * variable.blockLevel) + "(%ebp),%edx",
                    ""); 
            f.genInstr("", "movl", "%eax," + (-32 - (4*variable.offSet)) +
                    "(%edx)", variable.id + " :=");
        }

    }

    @Override public String identify() {
        return "<assign statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        variable.prettyPrint();
        Main.log.prettyPrint(":= ");
        expression.prettyPrint();
    }

    @Override void check(Block curscope, Library lib){
        variable.check(curscope, lib);
        expression.check(curscope, lib);
    }

    static AssignStatm parse(Scanner s) {
        enterParser("assign statm"); 
        AssignStatm as = new AssignStatm(s.curLineNum());
        as.variable = Variable.parse(s);
        s.skip(assignToken);
        as.expression = Expression.parse(s);
        leaveParser("assign statm");
        return as;
    }
}
