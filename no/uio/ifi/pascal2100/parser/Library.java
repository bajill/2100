
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;

class Library extends Block{
    ArrayList<String> predefDecls;

    Library() {
        super(0);
        predefDecls = new ArrayList<String>();

        predefDecls.add("write");
        predefDecls.add("true");
        predefDecls.add("false");
        predefDecls.add("eol");
    }

    @Override void check(Block curScope, Library lib){
        String id = "write";
        // If its not write, true, false or eol, check for integer and char and
        // log error of not
        if(!predefDecls.contains(id)){
            System.out.println("Library: int or char");
        }
        
    }
}
