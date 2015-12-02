package no.uio.ifi.pascal2100.parser; 
import no.uio.ifi.pascal2100.main.* ;
import no.uio.ifi.pascal2100.scanner.* ; 
import static no.uio.ifi.pascal2100.scanner.TokenKind.* ; 

class IfStatm extends Statement {
    Expression expression;
    Statement statement;
    Statement additionalStatement;

    IfStatm(int lNum) {
        super(lNum);
    }

    
    @Override void genCode(CodeFile f) {

        String firstLabel = f.getLocalLabel();

        //TODO meget lik while, med en else om additional statement
        if(additionalStatement == null){
            f.genInstr("", "", "", "Start if-statement");
            expression.genCode(f);
            f.genInstr("", "cmpl", "$0,%eax", "");
            f.genInstr("", "je", firstLabel, "");
            statement.genCode(f);
            f.genInstr(firstLabel, "", "", "End if-statement");
        }else{
            String endLabel = f.getLocalLabel();
            f.genInstr("", "", "", "Start if-statement");
            expression.genCode(f);
            f.genInstr("", "cmpl", "$0,%eax", "");
            f.genInstr("", "je", firstLabel, "");
            statement.genCode(f);
            f.genInstr("", "jmp", endLabel, "");
            f.genInstr(firstLabel, "", "", "");
            additionalStatement.genCode(f);
            f.genInstr(endLabel, "", "", "");
        }

        
    }

    @Override void check(Block curScope, Library lib) {
        expression.check(curScope, lib);
        statement.check(curScope, lib);
        if(additionalStatement != null)
            additionalStatement.check(curScope, lib);
    }

    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("if ");
        expression.prettyPrint();
        Main.log.prettyPrintLn("then ");
        Main.log.prettyIndent();
        statement.prettyPrint();
        Main.log.prettyOutdent();
        if (additionalStatement != null) {
            Main.log.prettyPrintLn();
            Main.log.prettyPrint("else ");
            Main.log.prettyIndent();
            additionalStatement.prettyPrint();
            Main.log.prettyOutdent();
        }
    }

    static IfStatm parse(Scanner s) {
        enterParser("if-statm");
        IfStatm is = new IfStatm(s.curLineNum());
        s.skip(ifToken);
        is.expression = Expression.parse(s);
        s.skip(thenToken);
        is.statement = Statement.parse(s);

        if(s.curToken.kind == elseToken){
            s.skip(elseToken);
            is.additionalStatement = Statement.parse(s);
        }

        leaveParser("if-statm");
        return is;
    }
}
