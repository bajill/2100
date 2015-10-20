package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
/* param decl list ::= '(' <param decl> [;][loop param decl] ')' */
class ParamDeclList extends PascalSyntax{
    ArrayList<ParamDecl> paramDecl;

    ParamDeclList(int lNum) {
        super(lNum);
        paramDecl = new ArrayList<ParamDecl>();
    }
    
    @Override public String identify() {
    return "<param decl part> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint(" (");
        for (int i = 0; i < paramDecl.size(); i++) {
            paramDecl.get(i).prettyPrint();
            if (i < paramDecl.size() - 1)
                Main.log.prettyPrint("; ");
        }        
        Main.log.prettyPrint(")");
    }

    static ParamDeclList parse(Scanner s) {
        enterParser("param decl part"); 
        s.skip(leftParToken);
        ParamDeclList pdl = new ParamDeclList(s.curLineNum());
        while(true){
            pdl.paramDecl.add(ParamDecl.parse(s));
            if(s.curToken.kind != semicolonToken)
                break;
            s.skip(semicolonToken);
        }
        s.skip(rightParToken);
        leaveParser("param decl part");
        return pdl;
    }
}
