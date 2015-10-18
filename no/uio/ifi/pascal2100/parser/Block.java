package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* Block ::= <const decl part> <type decl part> <var decl part> 
   <func decl> || <proc decl> 'begin' <statm list> 'end' */
class Block extends PascalSyntax{
    // TODO All these are PascalDecl, should be a arraylist?
    // ArrayList<PascalDecl> pascalDecl;
    ConstDeclPart constDeclPart;
    TypeDeclPart typeDeclPart;
    VarDeclPart varDeclPart;
    FuncDecl funcDecl;
    ProcDecl procDecl;

    StatmList statmList;


    Block(int lNum){
        super(lNum);
    }

    @Override public String identify() {
        return "<empty statm> on line " + lineNum;
    }

    @Override void prettyPrint(){
        if(constDeclPart != null)
        constDeclPart.prettyPrint();
        if(typeDeclPart != null)
        typeDeclPart.prettyPrint();
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

        /* const, type and varDeclPart */
        if(s.curToken.kind == constToken)
            b.constDeclPart = ConstDeclPart.parse(s);
        if(s.curToken.kind == typeToken)
            b.typeDeclPart = TypeDeclPart.parse(s);
        if(s.curToken.kind == varToken)
            b.varDeclPart = VarDeclPart.parse(s);

        /* func or procDecl */
        while(true){
            switch(s.curToken.kind){
                case procedureToken:
                    b.procDecl = ProcDecl.parse(s);
                    break;
                case functionToken:
                    b.funcDecl = FuncDecl.parse(s);
                    break;
            }
            /* */
            System.out.println(s.curToken.kind);
            if(s.curToken.kind != functionToken ||
                    s.curToken.kind != procedureToken)
                break;
        }

        s.skip(beginToken);
        //s.test(beginToken);
        //s.readNextToken();

        b.statmList = StatmList.parse(s); 
        s.skip(endToken);
        leaveParser("block");
        return b;
    }
}
