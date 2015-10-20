package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
class EnumType extends Type{
    ArrayList <EnumLiteral> enumType;
    EnumType(int lNum) {
    super(lNum);
    enumType = new ArrayList<EnumLiteral>();
    }

    
    @Override public String identify() {
    return "<enum type> on line " + lineNum;
    }

    @Override public void prettyPrint() {
        Main.log.prettyPrint("(");
        for (int i = 0; i < enumType.size(); i++) {
            enumType.get(i).prettyPrint();
            if (i < enumType.size() - 1)
                Main.log.prettyPrint(", ");
        }
        Main.log.prettyPrint(") ");
    }

    static EnumType parse(Scanner s) {
        enterParser("enum type"); 
        EnumType et = new EnumType(s.curLineNum());
        s.skip(leftParToken);
        while(true){
            et.enumType.add(EnumLiteral.parse(s));
            if(s.curToken.kind == commaToken)
                s.skip(commaToken);
            else
                break;
        }
        s.skip(rightParToken);
        leaveParser("enum type");
        return et;
    }
}
