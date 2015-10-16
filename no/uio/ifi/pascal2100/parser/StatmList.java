
package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

import java.util.ArrayList;

class StatmList extends PascalSyntax {
    ArrayList<Statement> statements = new ArrayList<Statement>();
    Statement statement;

    StatmList(int lNum) {
        super(lNum);
    }


    @Override public String identify() {
        return "<statm list> on line " + lineNum;
    }

/*
    @Override void check(Block curScope, Library lib) {
        // Til del 3 av prosjektet
    }


    @Override void genCode(CodeFile f) {
        // Til del 4 av prosjektet
    }


    @Override void prettyPrint() {
        // Til neste ukes oppgaver
    }
*/

    static StatmList parse(Scanner s) {
        enterParser("statm list");

        StatmList sl = new StatmList(s.curLineNum());
        sl.statement = Statement.parse(s);

        leaveParser("statm list");
        return sl;
    }
}
