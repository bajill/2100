package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* Block ::= <const decl part> <type decl part> <var decl part> 
    <func decl> || <proc decl> 'begin' <statm list> 'end' */
class Block extends PascalSyntax{
    ConstDeclPart constDeclPart;
    TypeDeclPart typeDeclPart;
    // VarDeclPart varDeclPart;
    // FuncDecl funcDecl;
    // ProcDecl procDecl;

    StatmList statmList;


    Block(int lNum){
        super(lNum);
    }
    
    @Override public String identify() {
    return "<empty statm> on line " + lineNum;
    }

    @Override void prettyPrint(){
        constDeclPart.prettyPrint();
        Main.log.prettyPrintLn("begin");
        Main.log.prettyIndent();
        statmList.prettyPrint();
        Main.log.prettyOutdent();
        Main.log.prettyPrintLn();
        Main.log.prettyPrint("end");
        
    }
    
    static Block parse(Scanner s) {

        enterParser("block"); 
        Block b = new Block(s.curLineNum());
        switch(s.curToken.kind){
            case constToken: 
                b.constDeclPart = ConstDeclPart.parse(s);
            case typeToken:
                b.typeDeclPart = TypeDeclPart.parse(s);
        // varDeclPart = varDeclPart.parse(s);
        // funcDecl = funcDecl.parse(s);
        // procDecl = procDecl.parse(s);
        }
        s.test(beginToken);
        s.readNextToken();
        
        b.statmList = StatmList.parse(s); 
        s.skip(endToken);
        leaveParser("block");
        return b;
    }

}
