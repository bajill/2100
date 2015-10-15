package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
/* Block ::= <const decl part> <type decl part> <var decl part> 
    <func decl> || <proc decl> 'begin' <starm list> 'end' */
class Block extends PascalSyntax{
    StatmList statmList;
    


    Block(int lNum){
        super(lNum);
    }
    
    @Override public String identify() {
    return "<empty statm> on line " + lineNum;
    }
    
    static Block parse(Scanner s) {
        System.out.println("1111");

        enterParser("block"); 
        System.out.println("2222");

        Block bl = new Block(s.curLineNum());
        System.out.println("333 ");
        
        s.test(beginToken);
        System.out.println("4444");
        System.out.println(s.curToken.identify());
        System.out.println(s.nextToken.identify());
        s.readNextToken();
       // bl.statmList = StatmList.parse(s); 
        s.skip(endToken);
        System.out.println("5555");
    // Fyll ut resten her.
        leaveParser("block");
        return bl;
    }

}
