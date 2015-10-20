package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
/* Block ::= <const decl part> <type decl part> <var decl part> 
   <func decl> || <proc decl> 'begin' <statm list> 'end' */
class Block extends PascalSyntax{
    // TODO All these are PascalDecl, should be a arraylist?
    // ArrayList<PascalDecl> pascalDecl;
    ConstDeclPart constDeclPart;
    TypeDeclPart typeDeclPart;
    VarDeclPart varDeclPart;
    ArrayList<ProcDecl> procANDfuncDecl;

    StatmList statmList;


    Block(int lNum){
        super(lNum);
        procANDfuncDecl = new ArrayList<ProcDecl>(); 
    }

    @Override public String identify() {
        return "<empty statm> on line " + lineNum;
    }

    @Override void prettyPrint(){
        if(constDeclPart != null)
            constDeclPart.prettyPrint();
        if(typeDeclPart != null)
            typeDeclPart.prettyPrint();
        if(varDeclPart != null)
            varDeclPart.prettyPrint();
        if(procANDfuncDecl.size() != 0)
            for (int i = 0; i < procANDfuncDecl.size(); i++)
                 procANDfuncDecl.get(i).prettyPrint();
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
                    b.procANDfuncDecl.add(ProcDecl.parse(s));
                    break;
                case functionToken:
                    b.procANDfuncDecl.add(FuncDecl.parse(s));
                    break;
            }
            
            if(s.curToken.kind == functionToken ||
                    s.curToken.kind == procedureToken)
                continue;
            else
                break;
        }

        s.skip(beginToken);
        b.statmList = StatmList.parse(s); 
        s.skip(endToken);
        leaveParser("block");
        return b;
    }
}
