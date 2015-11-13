package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* ProcDecl ==: 'procedure' <name> [param decl list] ';' <block> ';' */

class ProcDecl extends PascalDecl {
    ParamDeclList paramDeclList;
    Block block;

    ProcDecl(String name, int lNum) {
        super("", name, lNum);
    }

    @Override public String identify() {
        return "<proc decl> on line " + lineNum;
    }
    @Override void check(Block curscope, Library lib){
        block.outerScope = curscope;
        if(paramDeclList != null) {
            paramDeclList.check(block, lib);
        }
        curscope.addDecl(progProcFuncName, this);
        block.check(curscope, lib);
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("procedure  ");
        super.prettyPrint();
        if (paramDeclList != null)
            paramDeclList.prettyPrint();
        Main.log.prettyPrintLn(";");
        block.prettyPrint();
        Main.log.prettyPrintLn(";");
    }

    static ProcDecl parse(Scanner s) {
        enterParser("proc decl"); 
        s.skip(procedureToken);
        ProcDecl pd = new ProcDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();

        if(s.curToken.kind == leftParToken){
            pd.paramDeclList = ParamDeclList.parse(s);
        }

        s.skip(semicolonToken);
        pd.block = Block.parse(s);
        s.skip(semicolonToken);
        leaveParser("proc decl");
        return pd;
    }
}
