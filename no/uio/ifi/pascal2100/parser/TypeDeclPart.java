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

    @Override public void prettyPrint(){

    }

    static TypeDeclPart parse(Scanner s) {
        enterParser("type decl part"); 
        s.skip(typeToken);
        TypeDeclPart tdp = new TypeDeclPart(s.curLineNum());
        while(true){
            tdp.typeDecl.add(TypeDecl.parse(s));
            if(true)
                break;
        }
        leaveParser("type decl part");
        return tdp;
    }
}
