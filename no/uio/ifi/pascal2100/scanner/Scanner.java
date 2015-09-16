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
 * 
 */
    public void checkForEnds() {

        /* set curToken */
        if(nextToken != null)
            curToken = nextToken;

        /* end of line/first read */
        if((sourceLine.length() == 0) || sourceLine.length() == (sourcePos + 1)){
            readNextLine(); // Read next line to sourceLine
            // System.out.println("   " + getFileLineNum() + ": " + sourceLine);
        }

        /* end of file */
        if(sourceFile == null){
            Main.error("Reached end of file without reading 'end.'\nProgram terminated.");
            System.exit(-1);
        }
    }

    /**
     * Reades next token, and decides which Token to be created.
     * Calls checkForEnd() to read a line, then check what type of Token to be
     * created. This function may be called recursivly if there are comments or
     * empty lines in the code read from file
     */
    public void readNextToken() {
        checkForEnds();
        curChar = sourceLine.charAt(sourcePos);

        /* commentary, calls readNextToken() until Token found */
        if(curChar == '/' || curChar == '{'){
            readCommentary();
            readNextToken();
            return;
        }

        /* Checking for empty lines in code, readNextToken() */
        if(sourceLine.charAt(sourcePos) == ' '){
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
                System.exit(0);
            }
            /* blank line */
            if(sourceLine.length() == 1 && sourceLine.charAt(sourcePos) == ' ') {
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

    public void createCharToken() {
        if (createSpecialChar())
            return;
        nextToken = new Token(valueOf(signMap.get(Character.toString(sourceLine.charAt(sourcePos)))),
                getFileLineNum());
        Main.log.noteToken(nextToken);

        /* if end of file, make token */
        if(nextToken.kind == TokenKind.dotToken && curToken.kind == TokenKind.endToken){
            nextToken = new Token(TokenKind.eofToken, getFileLineNum());
            Main.log.noteToken(nextToken);
            System.exit(0);
        }
        sourcePos++; 
    }

    /**
     * Creates special caracter tokens
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

    public void createValToken(){
        StringBuilder val = new StringBuilder("\'"); 
        try {
            do {
                val.append(sourceLine.charAt(++sourcePos));
            } while(sourceLine.charAt(sourcePos) != '\'');
        } catch (StringIndexOutOfBoundsException e) {
            Main.error(getFileLineNum(), "String is never terminated. \nProgram terminated");
        }
        nextToken = new Token("", val.toString(), sourceFile.getLineNumber());
        Main.log.noteToken(nextToken);
        sourcePos++;
    }

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
            //System.out.println(signMap.get(mapKeys[i]));
        }
    }
}
