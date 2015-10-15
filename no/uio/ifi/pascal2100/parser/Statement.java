package no.uio.ifi.pascal2100.parser;

import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;

class Statement extends StatmList {
    Statement(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<empty statm> on line " + lineNum;
    }


    @Override void check(Block curScope, Library lib) {
    // Til del 3 av prosjektet
    }


    @Override void genCode(CodeFile f) {
    // Til del 4 av prosjektet
    }


    @Override void prettyPrint() {
    // Til neste ukes oppgaver
    }


    static Statement parse(Scanner s) {
        enterParser("statement"); 

        Statement st = new Statement(s.curLineNum()); 
    // Fyll ut resten her.

        leaveParser("statement ");
        return st;
    }
}
