package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class Term extends PascalSyntax  {
    ArrayList<Operator> operator;
    ArrayList<Factor> factor;

    Term(int lNum) {
        super(lNum);
        factor = new ArrayList<Factor>();
        operator = new ArrayList<Operator>();
    }

    @Override void genCode(CodeFile f) {
        /* first factor */
        factor.get(0).genCode(f);

        /* additional factors */
        for (int i = 1; i < factor.size(); i++) {

            f.genInstr("", "pushl", "%eax", "");

            factor.get(i).genCode(f);
            f.genInstr("", "movl", "%eax,%ecx", "");
            f.genInstr("", "popl", "%eax", "");

            String oper = operator.get(i-1).name;
            if(oper == "div"){
                f.genInstr("", "cdq", "", "");
                f.genInstr("", "idivl", "%ecx", "div");
            }

            else if(oper == "*")
                f.genInstr("", "imull", "%ecx,%eax", "*");

            else if(oper == "mod"){
                f.genInstr("", "cdq", "", "");
                f.genInstr("", "idivl", "%ecx", "");
                f.genInstr("", "movl", "%edx,%eax", "mod");
            }
            else if(oper == "and"){
                f.genInstr("", "andl", "%ecx,%eax", "  and");
            }
        }
    }

    @Override public String identify() {
        return "<term> on line " + lineNum;
    }
    @Override void check(Block curscope, Library lib){
        for(Factor f : factor){
            f.check(curscope, lib);
        }
    }

    @Override void prettyPrint() {
        for (int i = 0; i < factor.size(); i++) {
            factor.get(i).prettyPrint();
            if (i < factor.size() -1)
                operator.get(i).prettyPrint();
        }
    }

    static Term parse(Scanner s) {
        enterParser("term"); 
        Term t = new Term(s.curLineNum());

        while(true){
            Factor f = Factor.parse(s);
            t.factor.add(f);
            /* if more factors */
            if(s.curToken.kind.isFactorOpr())
                t.operator.add(FactorOperator.parse(s));
            else
                break;
        }

        leaveParser("term");
        return t;
    }
}
