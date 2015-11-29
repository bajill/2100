package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

class RangeType extends Type{
    Constant constant;
    Constant additionalConstant;
    RangeType(int lNum) {
        super(lNum);
    }

    @Override public String identify() {
        return "<range type> on line " + lineNum;
    }

    @Override void genCode(CodeFile f) {
    }
    

    @Override public void prettyPrint() {
        constant.prettyPrint();
        Main.log.prettyPrint("..");
        additionalConstant.prettyPrint();
    }
    @Override void check(Block curscope, Library lib){
        constant.check(curscope, lib);
        additionalConstant.check(curscope, lib);
    }

    static RangeType parse(Scanner s) {
        enterParser("range type"); 
        RangeType rt = new RangeType(s.curLineNum());
        rt.constant = Constant.parse(s);
        s.skip(rangeToken);
        rt.additionalConstant = Constant.parse(s);
        leaveParser("range type");
        return rt;
    }
}
