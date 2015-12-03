package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/* param decl ::= <name> ':' <type name> */

class ParamDecl extends PascalDecl{
    TypeName typeName;
    Block scope;    

    /* name is going to super's  progProcFuncName */
    ParamDecl(String name, int lNum) {
        super(name, lNum);
    }

    @Override void genCode(CodeFile f) {
        /* have to wait for procdecl.blcok.parse to set declLevel,
         * therefore a block reference */
        declLevel = scope.blockLevel; 
    }

    @Override void check(Block curscope, Library lib){
        curscope.addDecl(name, this);
        declOffset = ++curscope.paramOffset;

        /* see genCode */
        scope = curscope;
        typeName.check(curscope, lib);
        
    }
    @Override public String identify() {
        return "<param decl> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        super.prettyPrint();
        Main.log.prettyPrint(" : ");
        typeName.prettyPrint();
    }

    static ParamDecl parse(Scanner s) {
        enterParser("param decl"); 
        ParamDecl pd = new ParamDecl(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(colonToken);
        pd.typeName = TypeName.parse(s);
        leaveParser("param decl");
        return pd;
    }
}
