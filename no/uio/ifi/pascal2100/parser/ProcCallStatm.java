package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class ProcCallStatm extends Statement {
    //String procName;
    NamedConst name;
    ArrayList<Expression> expression;
    ProcDecl procRef;

    ProcCallStatm(int lNum) {
        super(lNum);
        expression = new ArrayList<Expression>();
    }

    @Override void genCode(CodeFile f) {
        for (int i = expression.size() - 1; i >= 0; i--) {
            expression.get(i).genCode(f);
            f.genInstr("", "pushl", "%eax", "Push param #" + (i+1) + ".");
    }
        f.genInstr("", "call", "proc$" +  "name??" + "_", "");
        f.genInstr("", "addl", "$?,%esp", "Pop parameters.");



    }
    @Override public String identify() {
        return "<proc call statm> on line " + lineNum;
    }

    @Override void check(Block curScope, Library lib) {
        name.check(curScope, lib);
        for(Expression e : expression)
            e.check(curScope, lib);
    }

    @Override public void prettyPrint() {
        name.prettyPrint();
        if (!expression.isEmpty()) {
            Main.log.prettyPrint("(");
            for (int i = 0; i < expression.size(); i++) {
                expression.get(i).prettyPrint();
                if (i < expression.size() - 1)
                    Main.log.prettyPrint(", ");
            }
            Main.log.prettyPrint(") ");
        }
    }

    static ProcCallStatm parse(Scanner s) {
        enterParser("proc call statm"); 
        ProcCallStatm pcs = new ProcCallStatm(s.curLineNum());
        pcs.name = NamedConst.parse(s);
        if(s.curToken.kind == leftParToken){
            s.skip(leftParToken);
            while(true){
                pcs.expression.add(Expression.parse(s));
                if(s.curToken.kind == commaToken)
                    s.skip(commaToken);
                else
                    break;
            }
            s.skip(rightParToken);
        }
        leaveParser("proc call statm");
        return pcs;
    }
}
