package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

/* simple expr ::= [<prefix opr>] <term> [<term opr> loop] */

class SimpleExpr extends PascalSyntax {
    /* term and operator belongs together */
    ArrayList<Term> term;
    ArrayList<Operator> operator;
    /* prefix operator*/
    Operator optionalOperator;

    SimpleExpr(int lNum) {
        super(lNum);
        term = new ArrayList<Term>();
        operator = new ArrayList<Operator>();
    }

    @Override void genCode(CodeFile f) {
        /* first term and prefix operator */
        term.get(0).genCode(f);
        System.out.println("term size" + term.size());
        if (optionalOperator != null)
            optionalOperator.genCode(f);

        /* Additional terms and term operators*/
        for (int i = 1; i < term.size(); i++) {
            f.genInstr("", "pushl", "%eax", "");

            term.get(i).genCode(f);
            f.genInstr("", "movl", "%eax,%ecx", "");
            f.genInstr("", "popl", "%eax", "");


            String oper = operator.get(i-1).name;

            if(oper == "+")
                f.genInstr("", "addl", "%ecx,%eax", "+");
            else if(oper == "-")
                f.genInstr("", "subl", "%ecx,%eax", "-");
            else if(oper == "*")
                f.genInstr("", "imull", "%ecx,%eax", "*");
            else if(oper == "div"){
                f.genInstr("", "cdq", "", "");
                f.genInstr("", "idivl", "%ecx", "div");
            }
            else if(oper == "mod"){
                f.genInstr("", "cdq", "", "");
                f.genInstr("", "idivl", "%ecx", "");
                f.genInstr("", "movl", "%edx,%eax", "mod");
            }
            else 
                System.out.println("ELSE simpleexpr");

        }
    }

    @Override public String identify() {
        return "<simpleExpr> on line " + lineNum;
    }

    @Override void check(Block curScope, Library lib) {
        for(Term t : term){
            t.check(curScope, lib);
        }
    }

    @Override void prettyPrint() { 
        if (optionalOperator != null)
            optionalOperator.prettyPrint();
        for (int i = 0; i < term.size(); i++) {
            term.get(i).prettyPrint();
            if (i < term.size() - 1)
                operator.get(i).prettyPrint();
        }
    }

    static SimpleExpr parse(Scanner s) {
        enterParser("simpleExpr"); 
        SimpleExpr se = new SimpleExpr(s.curLineNum());

        if(s.curToken.kind.isPrefixOpr()){
            se.optionalOperator = PrefixOpr.parse(s);
        }

        while(true){

            se.term.add(Term.parse(s));
            if(s.curToken.kind.isTermOpr())
                se.operator.add(TermOperator.parse(s));
            else
                break;
            /*
               if(!s.curToken.kind.isTermOpr())
               break;
               se.operator.add(TermOperator.parse(s));
               */
        }    

        leaveParser("simpleExpr");
        return se;
    }
}
