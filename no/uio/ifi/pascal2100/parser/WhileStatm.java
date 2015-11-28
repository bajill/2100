package no.uio.ifi.pascal2100.parser; 
import no.uio.ifi.pascal2100.main.* ;
import no.uio.ifi.pascal2100.scanner.* ; 
import static no.uio.ifi.pascal2100.scanner.TokenKind.* ; 

class WhileStatm extends Statement {
    Expression expr;
    Statement body;

    WhileStatm(int lNum) {
        super(lNum);
    }


    @Override void genCode(CodeFile f) {
        String testLabel = f.getLocalLabel();
        String endLabel = f.getLocalLabel();

        f.genInstr(testLabel, "", "", "Start while-statement");
        expr.genCode(f);
        f.genInstr("", "cmpl", "$0, %eax", "");
        f.genInstr("", "je", endLabel, "");
        body.genCode(f);
        f.genInstr("", "jmp", testLabel, "");
        f.genInstr(endLabel, "", "", "end while-statement");
    }


    @Override void check(Block curScope, Library lib) {
        expr.check(curScope, lib);
        body.check(curScope, lib);
    }

    @Override public String identify() {
        return "<while-statm> on line " + lineNum;
    }

    @Override void prettyPrint() {
        Main.log.prettyPrint("while ");
        expr.prettyPrint();
        Main.log.prettyPrintLn("do ");
        Main.log.prettyIndent();
        body.prettyPrint();
        Main.log.prettyOutdent();
    }

    static WhileStatm parse(Scanner s) {
        enterParser("while-statm");
        WhileStatm ws = new WhileStatm(s.curLineNum());
        s.skip(whileToken);
        ws.expr = Expression.parse(s);
        s.skip(doToken);
        ws.body = Statement.parse(s);
        leaveParser("while-statm");
        return ws;
    }
}
