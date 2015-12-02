package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class FuncCall extends Factor {
    CharLiteral name;
    ArrayList<Expression> expression;
    int blockLevel;
    Block block;

    FuncCall(int lNum) {
        super(lNum);
        expression = new ArrayList<Expression>();
    }

    @Override void genCode(CodeFile f){
        /* add last parameter first */
        for (int i = expression.size() - 1; i >= 0; i--) {
            expression.get(i).genCode(f);
            f.genInstr("", "pushl", "%eax", "Push param #" + (i+1) + ".");
        }
        f.genInstr("", "call", "func$" + block.findDecl(name.name,
                    this).label.toLowerCase(), ""); 
        if(expression.size() > 0)
            f.genInstr("", "addl", "$" + (4* expression.size()) + ",%esp", 
                    "Pop parameters.");
    }

    @Override void check(Block curscope, Library lib){
        name.check(curscope, lib);
        block = curscope;

        for(Expression e : expression){
            e.check(curscope, lib);
        }
        /* set blocklevel for genCode */
        blockLevel = curscope.findDecl(name.name, this).declLevel;
    }
    @Override public String identify() {
        return "<func call> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        name.prettyPrint();
        if (!expression.isEmpty()) {
            Main.log.prettyPrint("(");
            for (int i = 0; i < expression.size(); i++) {
                expression.get(i).prettyPrint();
                if (i < expression.size() - 1)
                    Main.log.prettyPrint(", ");
            }
            Main.log.prettyPrint(") ");
        }
    }

    static FuncCall parse(Scanner s) {
        enterParser("func call"); 
        FuncCall fc = new FuncCall(s.curLineNum());
        fc.name = CharLiteral.parse(s);
        if(s.curToken.kind == leftParToken){
            s.skip(leftParToken);
            while(true){
                fc.expression.add(Expression.parse(s));
                if(s.curToken.kind == commaToken)
                    s.skip(commaToken);
                else
                    break;
            }
            s.skip(rightParToken);
        }
        leaveParser("func call");
        return fc;
    }
}
