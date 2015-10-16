package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
class CompoundStatm extends Statement{
    StatmList statmList;

    CompoundStatm(int lNum){
        super(lNum);
    }
    
    @Override public String identify() {
    return "<empty statm> on line " + lineNum;
    }
    
    static CompoundStatm parse(Scanner s) {

        enterParser("compoundStatm"); 
        CompoundStatm cs = new CompoundStatm(s.curLineNum());
        s.skip(beginToken);
        cs.statmList = StatmList.parse(s); 
        s.skip(endToken);
        leaveParser("compoundStatm");
        return cs;
    }
}
