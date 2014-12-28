package icf.frontend.token;

import icf.frontend.Source;

public class EofToken extends Token<Void> {

    public EofToken(Source source) throws Exception {
        super(source);
    }

    @Override
    protected void extract() throws Exception {
    }

}
