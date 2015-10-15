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

        enterParser("block"); 

        Block bl = new Block(s.curLineNum());
        
        s.test(beginToken);
        //System.out.println("etter test beginToken: " + s.curToken.identify());
        s.readNextToken();
        //System.out.println("etter readnextToken: " + s.curToken.identify());
        //System.out.println("etter readnextToken: " + s.nextToken.identify());
       // bl.statmList = StatmList.parse(s); 
        s.skip(endToken);
        //System.out.println("etter skip endToken: " + s.curToken.identify());
    // Fyll ut resten her.
        leaveParser("block");
        return bl;
    }

}
