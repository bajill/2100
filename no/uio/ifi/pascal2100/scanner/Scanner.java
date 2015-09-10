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


    public void readNextToken() {
        // Del 1 her 
        System.out.println("readnexttoken");
        if(nextToken != null)
            curToken = nextToken;
        if(sourceLine.length() == 0)
            readNextLine();
        if((sourceLine.length() == (sourcePos + 1))){
            readNextLine(); // Read next line to sourceLine
        }
        if(sourceLine.length() == 0){
            nextToken = new Token(TokenKind.eofToken, getFileLineNum());
            System.out.println("eofToken : " + nextToken.identify());
            return;
        }
        if(isLetterAZ(sourceLine.charAt(sourcePos)))
            readString();
        
        else if(signMap.containsKey(sourceLine.charAt(sourcePos))) {
            nextToken = new Token(valueOf(signMap.get(sourceLine.charAt(sourcePos))), getFileLineNum());
            System.out.println("signmap: " + nextToken.identify());
            sourcePos++; 
        }
        /*else if(isDot(sourceLine.charAt(sourcePos)))
            makeDotToken();
        */
        /*
        for(; sourcePos < sourceLine.length(); sourcePos++){
            if(isLetterAZ(sourceLine.charAt(sourcePos))) 
                readString();
        }            
                */
    }


    public void makeDotToken(){
        for(TokenKind temp : TokenKind.values()){
            temp.identify();
        }
            //kindMap.put(valueOf(.identify(), 

        nextToken = new Token(valueOf("dotToken"), getFileLineNum()); 
        sourcePos++;
        System.out.println("asjkdnextToken : " + nextToken.identify());

    }

    public void readString(){
        String tempToken = "";
        boolean b = true;

        System.out.println("ReadString");
        while(b) {
            if(isLetterAZ(sourceLine.charAt(sourcePos)) || isDigit(sourceLine.charAt(sourcePos))){
                tempToken += sourceLine.charAt(sourcePos);
                sourcePos++;
            }
            else b = false;
        }
        
        nextToken = new Token(tempToken, sourceFile.getLineNumber());
        System.out.println("nextToken: " + nextToken.identify());
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

    private boolean isBrackets(char c) {
        return false;
    }

    private boolean isDot(char c) {
        return c == '.';
    }

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
        signMap.put("addToken", "+");
        signMap.put("assignToken", ":=");
        signMap.put("colonToken", ":");
        signMap.put("commaToken", ";");
        /* signMap.put("divideToken", "/"); */
        signMap.put("dotToken", ".");
        signMap.put("equalToken", "=");
        signMap.put("greaterToken", ">");
        signMap.put("greaterEqualToken", ">=");
        signMap.put("leftBracketToken", "[");
        signMap.put("leftParToken", "(");
        signMap.put("lessToken", "<");
        signMap.put("lessEqualToken", "<=");
        signMap.put("multiplyToken", "*");
        signMap.put("notEqualToken", "<>");
        signMap.put("rangeToken", "..");
        signMap.put("rightBracketToken", "]");
        signMap.put("rightParToken", ")");
        signMap.put("semicolonToken", ";");
        signMap.put("subtractToken", "-");

    }
}
