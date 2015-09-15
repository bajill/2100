package no.uio.ifi.pascal2100.scanner;

import no.uio.ifi.pascal2100.main.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;

import java.io.*;
import java.util.*;

public class Scanner {
    public Token curToken = null, nextToken = null; 
    private HashMap<String, String> signMap;
    private LineNumberReader sourceFile = null;
    private String sourceFileName, sourceLine = "";
    private int sourcePos = 0;
    private char curChar;

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

    // TODO end + . gir eof-token, som er en beskjed til kompilatoren
    public void readNextToken() {
        // Del 1 her 
        //System.out.println("readnexttoken");
        if(nextToken != null)
            curToken = nextToken;
        if(sourceLine.length() == 0){
            readNextLine();
            System.out.println("   " + getFileLineNum() + ": " + sourceLine);
        }
        // if end of line/first read
        if((sourceLine.length() == 0) || sourceLine.length() == (sourcePos + 1)){
            readNextLine(); // Read next line to sourceLine
            System.out.println("   " + getFileLineNum() + ": " + sourceLine);
        }
        // if e-o-f
        if(sourceFile == null){
            nextToken = new Token(TokenKind.eofToken, getFileLineNum());
            Main.log.noteToken(nextToken);
            return;
        }
        //System.out.println(sourcePos);
        curChar = sourceLine.charAt(sourcePos);
        // if commentary
        if(curChar == '/' || curChar == '{'){
            readCommentary();
            return;
        }
            
        // if space char token
        if(sourceLine.charAt(sourcePos) == ' ')
            if(sourceLine.length() == 1)
                return;
            else
                sourcePos++;
        // if a-z char token
        else if(isLetterAZ(sourceLine.charAt(sourcePos)))
            makeStringToken();
        // if symbol char, dobble symbol token has same first symbol as a single
        else if(signMap.containsKey(Character.toString(sourceLine.charAt(sourcePos)))) 
            createCharToken();
        // if value token
        else if(curChar == '\'')
            createValToken();
        // if digit token
        else if(isDigit(curChar))
            createDigitToken();
        else{
            System.out.println("PANIC: Programming error in line num " + getFileLineNum()); 
            System.exit(-1);
        }
    }
   

    public void readCommentary() {
        StringBuilder comment = new StringBuilder();
        boolean slashStar = false;
            if(curChar == '/' && sourceLine.charAt(sourcePos+1) == '*'){
            slashStar = true;
        }
        do{

            if(sourceLine.length() == (sourcePos + 1)){
                readNextLine();
                System.out.println("   " + getFileLineNum() + ": " + sourceLine);

            }
            if(sourceFile == null){
                nextToken = new Token(TokenKind.eofToken, getFileLineNum());
                Main.log.noteToken(nextToken);
                System.exit(0);
            }
            
            if(sourceLine.length() == 1 && sourceLine.charAt(sourcePos) == ' ') {
                System.out.println("   " + getFileLineNum() + ": ");
                break;
            }

            else if(slashStar){
                if(sourceLine.charAt(sourceLine.length()-3) == '*' && sourceLine.charAt(sourceLine.length()-2) == '/'){
                    sourcePos = sourceLine.length()-1;
                    return;
                }
                else 
                    readNextLine();
                    System.out.println("   " + getFileLineNum() + ": " + sourceLine);
            }
            else if (sourceLine.charAt(sourceLine.length()-2) == '}'){
                sourcePos = sourceLine.length() -1;
                return;
            }
            
        }while(true);
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
        nextToken = new Token(valueOf(signMap.get(Character.toString(sourceLine.charAt(sourcePos)))), getFileLineNum());
        Main.log.noteToken(nextToken);
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
                // if l 
                if(signMap.containsKey(doubleSign)){
                    nextToken = new Token(valueOf(signMap.get(doubleSign)), getFileLineNum());
                    sourcePos += 2;
                    Main.log.noteToken(nextToken);
                    return true;
                }
                // TODO ELSE 

            }
            i++;
        }
        return false;
    }
    
    public void createValToken(){
        StringBuilder val = new StringBuilder("\'"); 
        do {
            val.append(sourceLine.charAt(++sourcePos));
        } while(sourceLine.charAt(sourcePos) != '\'');
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
        //Del 1 her
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
        String []mapKeys = {"+", ":=", ":", ";", ".", "=", ">", ">=", "[", "(", "<", "<=", "*",
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
