
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

public class ConstDecl extends PascalDecl {
    Constant constant;
    ConstDecl(String name, int lNum) {
        super(name, lNum);
    }


    @Override public String identify() {
        return "<const decl> on line " + lineNum;
    }

    public static ConstDecl parse(Scanner s) {
        enterParser("const decl"); 
        s.test(nameToken);

        /* Gives superclass the string name */
        ConstDecl cd = new ConstDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(equalToken);

        cd.constant = Constant.parse(s);      
        s.skip(semicolonToken);
        System.out.println("Er dette noe? " + cd);
        System.out.println("Er dette noe? " + cd.constant);

        leaveParser("const decl");
        return cd;
    }
}
