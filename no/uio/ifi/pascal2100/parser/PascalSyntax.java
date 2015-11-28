package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.scanner.TokenKind;
import no.uio.ifi.pascal2100.scanner.Token;
import no.uio.ifi.pascal2100.scanner.Scanner;
import no.uio.ifi.pascal2100.main.*;

public abstract class PascalSyntax {
    public int lineNum;

    PascalSyntax(int n) {
        lineNum = n;
    }

    boolean isInLibrary() {
        return lineNum < 0;
    }  

    /*
     * This method compares the current token to a list of tokens given as
     * a parameter. It´s purpose is to save us from comparing current token
     * against all the possible tokens in a long if-statement.
     *
     * It is called from ConstDeclPart, TypeDeclPart, and VarDeclPart.
     *
     */
    public static boolean testIfToken(Scanner s, TokenKind[] tokens) {
        boolean b = false;
        for (int i = 0; i < tokens.length; i++) {
            if (s.curToken.kind == tokens[i])
                b = true;
        }
        return b;
    }


    abstract void check(Block curScope, Library lib);

    abstract void genCode(CodeFile f);

    abstract public String identify();

    abstract void prettyPrint();

    void error(String message) {
        Main.error("Error at line " + lineNum + ": " + message);
    }

    static void enterParser(String nonTerm) {
        Main.log.enterParser(nonTerm);
    }

    static void leaveParser(String nonTerm) {
        Main.log.leaveParser(nonTerm);
    }
}
