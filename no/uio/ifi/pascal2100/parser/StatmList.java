package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class StatmList extends PascalSyntax {
    ArrayList<Statement> statement;

    StatmList(int lNum) {
        super(lNum);
        statement = new ArrayList<Statement>();
    }

    @Override void genCode(CodeFile f) {
        for (Statement s: statement) 
            s.genCode(f);
    }

    @Override public String identify() {
        return "<statm list> on line " + lineNum;
    }

    @Override void prettyPrint() {
        for (int i = 0; i < statement.size(); i ++) {
            statement.get(i).prettyPrint();
            if (i < statement.size() - 1)
                Main.log.prettyPrintLn(";");
        }
    }

    @Override void check(Block curscope, Library lib){
        for(Statement st : statement){
            st.check(curscope, lib);
        }
    }

    static StatmList parse(Scanner s) {
        enterParser("statm list");
        StatmList sl = new StatmList(s.curLineNum());

        while (true) {
            sl.statement.add(Statement.parse(s));
            if (s.curToken.kind != semicolonToken) 
                break;
            s.skip(semicolonToken);
        }

        leaveParser("statm list");
        return sl;
    }
}
