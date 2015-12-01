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

    public void genCode(CodeFile f){
        f.genInstr("", ".globl", "_main", "");
        f.genInstr("", ".globl", "main", "");
        f.genInstr("_main", "", "", "");
        f.genInstr("main", "call", "prog$" + f.getLabel(name), "Start program");
        f.genInstr("", "movl", "$0,%eax", "Set status 0 and");
        f.genInstr("", "ret", "", "terminate the program");
        
        /* Program start */ 
        //for(ProcDecl pd: block.procANDfuncDecl)
        //    pd.genCode(f);
        //f.genInstr("prog$" + name + "_" + block.blockLevel, "", "", "");
        block.genCode(f);

        
    }

    @Override public String identify() {
        return "<program> " + name + " on line " + lineNum;
    }

    
    @Override public void check(Block curscope, Library lib) {
        block.name = name.toLowerCase();
        block.check(curscope, lib);
    }

    @Override public void prettyPrint(){
        Main.log.prettyPrint("Program ");
        super.prettyPrint();
        Main.log.prettyPrintLn(";");
        block.prettyPrint(); 
        Main.log.prettyPrint(".");
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
