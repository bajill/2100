package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class TypeDeclPart extends PascalSyntax {
    ArrayList<TypeDecl> typeDecl;

    TypeDeclPart(int lNum) {
        super(lNum);
        typeDecl = new ArrayList<TypeDecl>();
    }

    @Override public String identify() {
        return "<type decl part> on line " + lineNum;
    }

    @Override void genCode(CodeFile f) {

    }
    
    @Override void check(Block curScope, Library lib) {
        for (TypeDecl td: typeDecl)
            td.check(curScope, lib);
    }

    @Override public void prettyPrint(){
        Main.log.prettyPrintLn("type");
        Main.log.prettyIndent();
        for (int i = 0; i < typeDecl.size(); i ++) {
            typeDecl.get(i).prettyPrint();
        } 
        Main.log.prettyOutdent();
    }

    static TypeDeclPart parse(Scanner s) {
        enterParser("type decl part"); 
        s.skip(typeToken);
        TypeDeclPart tdp = new TypeDeclPart(s.curLineNum());
        while(true){
            tdp.typeDecl.add(TypeDecl.parse(s));
            /* Tests if curToken is valid */
            if (s.curToken.kind == nameToken)
                continue;
            else
                break;
        }

        leaveParser("type decl part");
        return tdp;
    }
}
