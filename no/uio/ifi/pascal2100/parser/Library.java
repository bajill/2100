
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class Library extends Block{
    ArrayList<String> predefDecls;

    Library() {
        super(0);

        String [] enumtypes = {"write", "true", "false", "eol"};
        int i = 0;
        predefDecls = new ArrayList<String>();
        for(String t : enumtypes){
            predefDecls.add(t);
        }
    }

}
