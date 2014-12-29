package icf.frontend;

import icf.frontend.types.LanguageType;
import icf.frontend.types.ParserType;
import language.pascal.frontend.PascalLexer;
import language.pascal.frontend.PascalTopDownParser;

public class FrontendFactory {

    public static Parser createParser(LanguageType languageType, ParserType parserType, Source source) throws Exception {
        if (languageType == LanguageType.PASCAL) {
            if (parserType == ParserType.TOP_DOWN) {
                Lexer lexer = new PascalLexer(source);
                return new PascalTopDownParser(lexer);
            }
            else
                throw new Exception("Parser factory: Invalid parser type '" + parserType + "'");
        }
        else
            throw new Exception("Parser factory: Invalid language '" + languageType + "'");
    }

}
