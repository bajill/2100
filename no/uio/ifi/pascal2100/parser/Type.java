package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

abstract class Type extends PascalSyntax {
    Type(int lNum) {
        super(lNum);
    }


    @Override public String identify() {
        return "<type> on line " + lineNum;
    }

    @Override void prettyPrint() {
    }

    static Type parse(Scanner s) {
        enterParser("type"); 

        Type t = null;
        switch (s.curToken.kind){
            case leftParToken:
                t = EnumType.parse(s);
                break;

            case arrayToken:
                t = ArrayType.parse(s);
                break;

            case nameToken:
                switch(s.nextToken.kind){
                    case equalToken:
                        t = TypeName.parse(s);
                        break;
                }
                /* All the other fails, must be */
            default:
                t = RangeType.parse(s);
        }
        leaveParser("type");
        return t;
    }
    }
