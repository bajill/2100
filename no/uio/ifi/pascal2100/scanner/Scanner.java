package no.uio.ifi.pascal2100.scanner;
import no.uio.ifi.pascal2100.main.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

import java.io.*;
import java.util.*;

/** 
 * Creates Tokens of type TokenKind.
 * Reads from file and creates the matching Token from the language pascal2100
 *
 * @author kennetaf
 * @author parosen
 * @version 16/9/2015
 */
public class Scanner {
    public Token curToken = null, nextToken = null; 
    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";
    private int sourcePos = 0;

    /* Additional variables */
    private char curChar;
    private HashMap<String, String> signMap;
    

    public Scanner(String fileName) {
        sourceFileName = fileName;
        try {
            sourceFile = new LineNumberReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            Main.error("Cannot read " + fileName + "!");
        }
        makeSignMap();
        readNextToken();  readNextToken();
    }

    public String identify() {
        return "Scanner reading " + sourceFileName;
    }

      public int curLineNum() {
        return curToken.lineNum;
    }

    private void error(String message) {
        Main.error("Scanner error on line " + curLineNum() + ": " + message);
    }

/**
 * Checks if end of line or end of file are reached, and reads lines.
 * Return true if there is no more token to be made, else false
 */
    boolean checkForEnds() {
        /* end of line/first read */
        if((sourceLine.length() == 0) || sourceLine.length() == (sourcePos + 1)){
            readNextLine(); // Read next line to sourceLine
        }
        /* if eof token, make nextToken a eofToken*/
        if(nextToken != null && curToken != null)
            if(nextToken.kind == TokenKind.dotToken && curToken.kind == TokenKind.endToken) {
                curToken = nextToken;
                nextToken = new Token(TokenKind.eofToken, getFileLineNum());
                Main.log.noteToken(nextToken);
                return true;
            }else if(nextToken.kind == TokenKind.eofToken){
                curToken = nextToken;
                return true;
            }
        /* set curToken */
        if(nextToken != null)
            curToken = nextToken;
        /* end of file */
        if(sourceFile == null){
            Main.error("Error in last line: Expected a . but found a e-o-f");
            return true;
        }
        return false;
    }

    /**
     * Reades next token, and decides which Token to be created.
     * Calls checkForEnd() to read a line, then check what type of Token to be
     * created. This function may be called recursivly if there are comments or
     * empty lines in the code read from file
     */
    public void readNextToken() {
        /* True if end of file */
        if(checkForEnds())
            return;
        curChar = sourceLine.charAt(sourcePos);

        /* commentary, calls readNextToken() until Token found */
        if(curChar == '/' || curChar == '{'){
            readCommentary();
            readNextToken();
            return;
        }

        /* Checking for empty lines in code, readNextToken() */
        if(Character.isWhitespace(sourceLine.charAt(sourcePos))){
            if(sourceLine.length() == 1)
                readNextToken();
            else{
                sourcePos++;
                readNextToken();
            }
        }
        /* a-z char Token */
        else if(isLetterAZ(curChar))
            makeStringToken();

        /* symbol char, and E-O-F Token*/
        else if(signMap.containsKey(Character.toString(curChar))) 
            createCharToken();

        /* value Token */
        else if(curChar == '\'')
            createValToken();

        /* digit Token */
        else if(isDigit(curChar))
            createDigitToken();
        else{
            Main.error(getFileLineNum(), "Illegal character : '" + curChar + "'");
            System.exit(-1);
        }
    }

    /**
     * Search for end of commentary.
     * Reads all commentslines until end of comment are reached. If EOF is
     * reached before end of comment are reached, it writes a error and
     * terminates program
     */
    public void readCommentary() {
        boolean slashStar = false;
        int start = getFileLineNum();
        if(curChar == '/' && sourceLine.charAt(sourcePos+1) == '*'){
            slashStar = true;
        }
        while(true){
            /* end of line */
            if(sourceLine.length() == (sourcePos + 1)){
                readNextLine();
            }
            /* EOF */
            if(sourceFile == null){
                Main.error(start, "No end for comment starting on line " + start);
                System.exit(-1);
            }
            /* blank line */
            // if(sourceLine.length() == 1 && sourceLine.charAt(sourcePos) == ' ') {
            if(sourceLine.length() == 1 && Character.isWhitespace(sourceLine.charAt(sourcePos))) {
                break;
            }
            /* /* comments */
                else if(slashStar){
                /* comments end */
                if(sourceLine.charAt(sourceLine.length()-3)
                        == '*' && sourceLine.charAt(sourceLine.length()-2) == '/'){
                    sourcePos = sourceLine.length()-1;
                    return;
                }
                else 
                    readNextLine();
            }
            /* {} comments */
            else if (sourceLine.charAt(sourceLine.length()-2) == '}'){
                sourcePos = sourceLine.length() -1;
                readNextLine();
                return;
            }
        }
    }

    public void createDigitToken() {
        String digit = "";

        while(isDigit(sourceLine.charAt(sourcePos))){
            digit += sourceLine.charAt(sourcePos++);
        }
        nextToken = new Token(Integer.parseInt(digit), getFileLineNum());
        Main.log.noteToken(nextToken);
    }

    /**
     * Creates special character tokens that consists of only one character.
     * First it calls createSpecialChar() to check if current char consists of a sign
     * that can be combined into a doublechar Token. If that is not the case then this 
     * will create the Token that matches from the special sign HashMap.
     */
    public void createCharToken() {
        if (createSpecialChar())
            return;
        nextToken = new Token(valueOf(signMap.get(Character.toString(sourceLine.charAt(sourcePos)))),
                getFileLineNum());
        /* if end of file, make token */
        /*
        if(nextToken.kind == TokenKind.dotToken && curToken.kind == TokenKind.endToken){
            nextToken = new Token(TokenKind.eofToken, getFileLineNum());
        }
        */
        Main.log.noteToken(nextToken);
        sourcePos++; 
    }

    /**
     * Creates special character Tokens if they consist of two characters.
     * For example <>
     * It checks for matches inside the HashMap signMap.
     * @return true if special caracter was created, else false
     */
    private boolean createSpecialChar(){
        char[] specChar = {':', '<', '>', '.'};
        int i = 0;
        String doubleSign = "";

        while (i < 4) {
            if (curChar == specChar[i]) {
                doubleSign += curChar;
                doubleSign += sourceLine.charAt(sourcePos +1);
                if(signMap.containsKey(doubleSign)){
                    nextToken = new Token(valueOf(signMap.get(doubleSign)), getFileLineNum());
                    sourcePos += 2;
                    Main.log.noteToken(nextToken);
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    /**
     * Creates stringValTokens.
     * These are strings that you find as arguments inside functions like write()
     */
    public void createValToken(){
        StringBuilder val = new StringBuilder(""); 
        sourcePos++;
        try {
            do {
                val.append(sourceLine.charAt(sourcePos));
            } while(sourceLine.charAt(++sourcePos) != '\'');
        } catch (StringIndexOutOfBoundsException e) {
            Main.error(getFileLineNum(), "String is never terminated. \nProgram terminated");
        }
        nextToken = new Token("", val.toString(), sourceFile.getLineNumber());
        Main.log.noteToken(nextToken);
        sourcePos++;
    }

    /**
     * Creates a Token of any "String-type", either a terminal or non-terminal.
     */
    public void makeStringToken(){
        String tempToken = "";
        boolean b = true;
        while(b) {
            if(isLetterAZ(sourceLine.charAt(sourcePos)) || isDigit(sourceLine.charAt(sourcePos))){
                tempToken += sourceLine.charAt(sourcePos);
                sourcePos++;
            }
            else b = false;
        }
        nextToken = new Token(tempToken, sourceFile.getLineNumber());
        Main.log.noteToken(nextToken);
    }

    private void readNextLine() {
        if (sourceFile != null) {
            try {
                sourceLine = sourceFile.readLine();
                if (sourceLine == null) {
                    sourceFile.close();  sourceFile = null;
                    sourceLine = "";  
                } else {
                    sourceLine += " ";
                }
                sourcePos = 0;
            } catch (IOException e) {
                Main.error("Scanner error: unspecified I/O error!");
            }
        }
        if (sourceFile != null) 
            Main.log.noteSourceLine(getFileLineNum(), sourceLine);
    }

    private int getFileLineNum() {
        return (sourceFile!=null ? sourceFile.getLineNumber() : 0);
    }


    // Character test utilities:

    private boolean isLetterAZ(char c) {
        return 'A'<=c && c<='Z' || 'a'<=c && c<='z';
    }


    private boolean isDigit(char c) {
        return '0'<=c && c<='9';
    }

    // Parser tests:

    public void test(TokenKind t) {
        if (curToken.kind != t)
            testError(t.toString());
    }

    public void testError(String message) {
        Main.error(curLineNum(), 
                "Expected a " + message +
                " but found a " + curToken.kind + "!");
    }

    public void skip(TokenKind t) {
        test(t);
        readNextToken();
    }
    
    /**
     * Creates HashMap code-symbols as keys and their matching TokenKinds as values.
     */
    private void makeSignMap() {
        signMap = new HashMap <String, String>(); 
        String []mapKeys = {"+", ":=", ":", ",", ".", "=", ">", ">=", "[", "(", "<", "<=", "*",
            "<>", "..", "]", ")", ";", "-"};
        String []mapValues = {"addToken","assignToken", "colonToken","commaToken",  "dotToken",
            "equalToken", "greaterToken", "greaterEqualToken", "leftBracketToken",
            "leftParToken", "lessToken", "lessEqualToken","multiplyToken",
            "notEqualToken", "rangeToken", "rightBracketToken", "rightParToken",
            "semicolonToken", "subtractToken"};
        
    for(int i = 0; i < mapKeys.length; i++){
            signMap.put(mapKeys[i], mapValues[i]);
        }
    }
}
