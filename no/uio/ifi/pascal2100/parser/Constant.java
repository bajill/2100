
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Constant extends Factor {
    Constant(int lNum) {
    super(lNum);
    }

    
    @Override public String identify() {
    return "<constant> on line " + lineNum;
    }

    @Override void prettyPrint() {
    }

    static Constant parse(Scanner s) {
        enterParser("constant"); 
        Constant c = null;
        switch (s.curToken.kind){
            case nameToken:
             //TODO her det et problem, klassen er abstrakt og kan derfor ikke
             // være noe object i seg selv, hvor skal id??
             //c = new Constant(s.curToken.id, s.curLineNum());
            case stringValToken:
                c = StringLiteral.parse(s);
                break;
            case intValToken:
                //System.out.println("Constant hit?");
                c = NumberLiteral.parse(s);
                break;
            default:
                System.out.println("Something wrong in Constant");
        }
        leaveParser("constant");
        //System.out.println("Constant " + c);
        return c;
    }
}
