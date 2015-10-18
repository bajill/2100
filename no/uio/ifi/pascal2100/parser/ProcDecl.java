
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class ProcDecl extends PascalDecl {
    //ParamDeclList paramDeclList;
    Block block;
    ProcDecl(String name, int lNum) {
        super("", name, lNum);
    }


    @Override public String identify() {
        return "<proc decl> on line " + lineNum;
    }

    @Override public void prettyPrint() {

    }

    static ProcDecl parse(Scanner s) {
        enterParser("proc decl"); 
        ProcDecl pd = new ProcDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        if(s.curToken.kind == leftParToken){
            //TODO ParamDeclList
            System.out.println();
        }
        s.skip(semicolonToken);
        pd.block = Block.parse(s);

        s.skip(semicolonToken);

        leaveParser("proc decl");
        return pd;
    }
}
