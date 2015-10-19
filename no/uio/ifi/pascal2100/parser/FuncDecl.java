
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* func decl ::= 'function' <name> [param decl list] 
 * ':' <type name> ';' <block> ';'*/
class FuncDecl extends ProcDecl{
    TypeName typeName;

    /* ProcDecl are super */
    FuncDecl(String name, int lNum) {
    super(name, lNum);
    }

    
    @Override public String identify() {
    return "<func decl> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("function ");
        Main.log.prettyPrint(progProcFuncName);
        paramDeclList.prettyPrint();
        Main.log.prettyPrint(" : ");
        typeName.prettyPrint();
        Main.log.prettyPrintLn(";");
        block.prettyPrint();
    }

    static FuncDecl parse(Scanner s) {
        enterParser("func decl"); 
        s.skip(functionToken);
        FuncDecl fd = new FuncDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();
        /* param decl list in super */
        if(s.curToken.kind == leftParToken)
            fd.paramDeclList = ParamDeclList.parse(s);
        s.skip(colonToken);
        fd.typeName = TypeName.parse(s);
        s.skip(semicolonToken);
        
        /* in super */
        fd.block = Block.parse(s);
        s.skip(semicolonToken);

        leaveParser("func decl");
        return fd;
    }
}
