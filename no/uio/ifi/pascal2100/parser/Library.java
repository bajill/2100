
package no.uio.ifi.pascal2100.parser;
import no.uio.ifi.pascal2100.main.*;
import no.uio.ifi.pascal2100.scanner.*;
import static no.uio.ifi.pascal2100.scanner.TokenKind.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Library extends Block{
    HashMap<String, PascalDecl> decls = new HashMap<String, PascalDecl>();

    public Library() {
        super(0);

    }
}
