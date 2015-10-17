package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

/*<program> ::= ’program’ <name> ’;’ <block> ’.’*/
public class Program extends PascalDecl {
    Block block;
    Program(String id, int lNum) {
        super(id, lNum);
    }
    @Override public String identify() {
        return "<program> " + name + " on line " + lineNum;

    }

    @Override public void prettyPrint(){
        Main.log.prettyPrint("Program ");
        super.prettyPrint();
        //Main.log.prettyPrint(";\n");
        block.prettyPrint(); 
        //Main.log.prettyPrint(".");
        //block.prettyPrint(); 
    }


    public static Program parse(Scanner s) {
        enterParser("program");
        s.skip(programToken);
        s.test(nameToken);
        Program p = new Program(s.curToken.id, s.curLineNum());
        s.readNextToken();
        s.skip(semicolonToken);
        //System.out.println("1. " + s.curToken.identify());
        p.block = Block.parse(s); // p.block.context = p;
        //System.out.println("2. " + s.curToken.identify());
        s.skip(dotToken);
        //System.out.println("3. " + s.curToken.identify());
        
        // Hvorfor var ikke denne med koden?
        s.skip(eofToken);
        leaveParser("program");
        return p;
    }

}
