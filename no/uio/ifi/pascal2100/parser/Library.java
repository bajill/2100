
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Library extends Block{

    public void genCode(CodeFile f){
        blockLevel = 1; 
        f.genInstr("", ".extern write_char", "", "");
        f.genInstr("", ".extern write_int", "", "");
        f.genInstr("", ".extern write_string", "", "");
    }
    public Library() {
        super(0);
        
        /* Vi har ikke faatt gjort ferdig Library slik at deklarasjonene i decls
         * er riktig i forhold til definisjonen av predefinerte deklarasjoner,
         * men programmet kjorer uten aa fi feilmeldinger*/

        /* Boolean */
        EnumLiteral t = new EnumLiteral("true", -1);
        EnumLiteral f = new EnumLiteral("false", -1);
        EnumType type = new EnumType(-1);
        TypeDecl bol = new TypeDecl("boolean", -1);
        type.enumType.add(t);
        type.enumType.add(f);
        bol.type = type;

        /* write */
        ProcDecl pd = new ProcDecl("write", -1);
        ParamDeclList pdlWrite = new ParamDeclList(-1);
        pd.paramDeclList = pdlWrite;
        
        /* char */
        EnumLiteral c = new EnumLiteral("char", -1);
        NumberLiteral charTo = new NumberLiteral(-1);
        NumberLiteral charFrom = new NumberLiteral(-1);
        RangeType character = new RangeType(-1);
        character.constant = charFrom;
        character.additionalConstant = charTo;
        charFrom.numValue = 0;
        charTo.numValue = 127;
        TypeDecl charType = new TypeDecl("char", -1);
        charType.type = character;
        decls.put("char", charType);


        /* eol */
        ConstDecl eol = new ConstDecl("eol", -1);
        NumberLiteral eolAscii = new NumberLiteral(-1);
        eolAscii.numValue = 10;
        eol.constant = eolAscii;
        decls.put("eol", eol);

        //EnumLiteral bol = new EnumLiteral("boolean", -1);
        
        /* Integer */
        NumberLiteral from = new NumberLiteral(-1);
        NumberLiteral to = new NumberLiteral(-1);
        RangeType integer = new RangeType(-1);
        integer.constant = from;
        integer.additionalConstant = to;
        from.numValue = -2147483648;
        to.numValue = 2147483647;
        TypeDecl intType = new TypeDecl("integer", -1);
        intType.type = integer;

        decls.put("integer", intType);
        // Vi har ikke lagt til findDecl for Library deklarrasjonene. Dette vil
        // blir gjort mot neste oblig
        decls.put("true", t);
        decls.put("false", f);
        decls.put("boolean", bol);
        decls.put("write", pd);

    }
    
}
