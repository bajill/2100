package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* Block ::= <const decl part> <type decl part> <var decl part> 
    <func decl> || <proc decl> 'begin' <statm list> 'end' */
class Block extends PascalSyntax{
    /*ConstDeclPart constDeclPart;
    TypeDeclPart typeDeclPart;
    VarDeclPart varDeclPart;
    FuncDecl funcDecl;
    ProcDecl procDecl;
*/
    StatmList statmList;


    Block(int lNum){
        super(lNum);
    }
    
    @Override public String identify() {
    return "<empty statm> on line " + lineNum;
    }
    
    static Block parse(Scanner s) {

        enterParser("block"); 
        Block bl = new Block(s.curLineNum());
        s.test(beginToken);
        s.readNextToken();
        // constDeclPart = constDeclPart.parse(s);
        // typeDeclPart = typeDeclPart.parse(s);
        // varDeclPart = varDeclPart.parse(s);
        // funcDecl = funcDecl.parse(s);
        // procDecl = procDecl.parse(s);
        
        bl.statmList = StatmList.parse(s); 
        s.skip(endToken);
        leaveParser("block");
        return bl;
    }

}
