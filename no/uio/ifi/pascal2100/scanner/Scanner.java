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
        if(sourceLine.length() == 0)
            readNextLine();
        // if end of line/first read
        if((sourceLine.length() == (sourcePos + 1))){
            readNextLine(); // Read next line to sourceLine
        }
        // if e-o-f
        if(sourceFile == null){
            nextToken = new Token(TokenKind.eofToken, getFileLineNum());
            System.out.println("Scanner: " + nextToken.identify());
            return;
        }
        //System.out.println(sourcePos);
        curChar = sourceLine.charAt(sourcePos);
        // if commentary
        if(curChar == '/' || curChar == '{')
            readCommentary();
            
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
        else
            System.exit(-1);
    }
   

    public void readCommentary() {
        StringBuilder comment = new StringBuilder();
        boolean curly = false;
        boolean slash = false;
        if(curChar == '/' && sourceLine.charAt(sourcePos+1) == '*')
            curly = true;
        else
            curly = true;
        do{

            if(sourceLine.length() == (sourcePos + 1)){
                System.out.println("   " + getFileLineNum() + ": " + comment);
                readNextLine();
            }
            if(sourceFile == null){
                nextToken = new Token(TokenKind.eofToken, getFileLineNum());
                System.out.println("   " + getFileLineNum() + ": " + comment);
                System.out.println("Scanner: " + nextToken.identify());
                System.exit(0);
            }
            if(curly){
                if(sourceLine.charAt(sourcePos) == '*' && sourceLine.charAt(sourcePos+1) == '/'){
                    comment.append(sourceLine.charAt(sourcePos));
                    comment.append(sourceLine.charAt(sourcePos+1));
                    System.out.println("   " + getFileLineNum() + ": " + comment); 
                    return;
                }
            }
            else if(sourcePos.charAt(sourcePos) == '}'){
                comment.append(sourceLine.charAt(sourcePos));
                System.out.println("   " + getFileLineNum() + ": " + comment);  
                return;
            }
            else if(sourceLine.length() = 1 && sourceLine.charAt(sourcePos) == ' ')
                System.out.println("");
            else
                comment.append(sourceLine.charAt(sourcePos++));
        }while(1);
     
    }

    public void createDigitToken() {
        String digit = "";
        while(isDigit(sourceLine.charAt(sourcePos))){
            digit += sourceLine.charAt(sourcePos++);

        }
        nextToken = new Token(Integer.parseInt(digit), getFileLineNum());
        System.out.println("Scanner: " + nextToken.identify());
    }

    public void createCharToken() {
        if (createSpecialChar())
            return;
        nextToken = new Token(valueOf(signMap.get(Character.toString(sourceLine.charAt(sourcePos)))), getFileLineNum());
        System.out.println("Scanner: " + nextToken.identify());
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
        //System.out.println(specChar.length);
        while (i < 4) {
            if (curChar == specChar[i]) {
                doubleSign += curChar;
                doubleSign += sourceLine.charAt(sourcePos +1);
                //System.out.println(doubleSign);
                // if l 
                if(signMap.containsKey(doubleSign)){
                    nextToken = new Token(valueOf(signMap.get(doubleSign)), getFileLineNum());
                    sourcePos += 2;
                    System.out.println("Scanner: " + nextToken.identify());
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
        System.out.println("Scanner: " + nextToken.identify());
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
        System.out.println("Scanner: " + nextToken.identify());
        //Del 1 her
        //Main.log.noteToken(nextToken);
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
