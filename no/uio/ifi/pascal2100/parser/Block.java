package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
import java.util.HashMap;
//import java.io.*;

/* Block ::= <const decl part> <type decl part> <var decl part> 
   <func decl> || <proc decl> 'begin' <statm list> 'end' */

class Block extends PascalSyntax{
    ConstDeclPart constDeclPart;
    TypeDeclPart typeDeclPart;
    VarDeclPart varDeclPart;
    StatmList statmList;
    ArrayList<ProcDecl> procANDfuncDecl;
    HashMap<String, PascalDecl> decls = new HashMap<String, PascalDecl>();
    Block outerScope;
    ProcDecl paramDecl;
    String name;

    /* Evey new block get new level*/
    int blockLevel;
    //static int blockCount;
    int offSet, paramOffset;


    Block(int lNum){
        super(lNum);
        offSet = 0; 
        blockLevel = 0;
        procANDfuncDecl = new ArrayList<ProcDecl>(); 
    }


    public void genCode(CodeFile f){

        for(ProcDecl pd: procANDfuncDecl){
            pd.label = f.getLabel(pd.progProcFuncName);
            pd.genCode(f);
        }


        if(blockLevel == 1)
            f.genInstr("prog$" + name.toLowerCase()+ "_" + blockLevel, "", "", "");
        else {
            String label = findDecl(name, this).label ;
            /* if func*/
            if(outerScope.findDecl(name, this) instanceof FuncDecl) {
                f.genInstr("func$" + label.toLowerCase(),"", "", "");
                // TODO blocklevel burde være func sin blockLevel
                f.genInstr("", "enter", "$" +(32 + 4*(offSet)) + ",$" +
                        blockLevel, "Start of " + name); 
                statmList.genCode(f);
                f.genInstr("", "movl", "-32(%ebp),%eax", "Fetch return value");
                f.genInstr("", "leave", "", "End of " + name);
                f.genInstr("", "ret", "", "");
                return;                
            }
            f.genInstr("proc$" + label.toLowerCase(), "", "", "");
        }
        // TODO blocklevel burde være proc sin blockLevel
        f.genInstr("", "enter", "$" +(32 + 4*(offSet)) + ",$" + blockLevel,
                "Start of " + name); 
        statmList.genCode(f);
        f.genInstr("", "leave", "", "End of " + name);
        f.genInstr("", "ret", "", "");

    }



    void addDecl(String id, PascalDecl d) {
        if (decls.containsKey(id))
            d.error(id + " declares twice in the same block!");
        decls.put(id.toLowerCase(), d);
    }

    PascalDecl findDecl(String id, PascalSyntax where){
        PascalDecl d = decls.get(id.toLowerCase());
        if (d != null) {
            Main.log.noteBinding(id, where, d);
            return d;
        }
        if (outerScope != null)
            return outerScope.findDecl(id, where);
        where.error("Name " + id + " is unknown!");
        return null; // Required by the Java compiler
    }

    @Override void check(Block curScope, Library lib) {
        outerScope = curScope;
        blockLevel = outerScope.blockLevel + 1;
        // TODO dene blir feil
        paramOffset = curScope.paramOffset +1;

        if (constDeclPart != null) {
            constDeclPart.check(this, lib);
            for (ConstDecl cd: constDeclPart.constDecl) {
                addDecl(cd.name, cd); 
            }
        }
        if (typeDeclPart != null) {
            typeDeclPart.check(this, lib);
            for(TypeDecl td : typeDeclPart.typeDecl){
                addDecl(td.name, td);
            }
        }
        if (varDeclPart != null) {
            varDeclPart.check(this, lib);
            for (VarDecl vd: varDeclPart.varDecl) {
                addDecl(vd.name, vd);
            }
        }

        if (procANDfuncDecl != null) {
            for(ProcDecl pd : procANDfuncDecl){
                pd.check(this, lib);
            }
        }
        statmList.check(this, lib);
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

    @Override public String identify() {
        return "<empty statm> on line " + lineNum;
    }

}
