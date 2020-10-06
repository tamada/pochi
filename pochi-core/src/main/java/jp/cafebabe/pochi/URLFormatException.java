package jp.cafebabe.pochi;

public class URLFormatException extends RuntimeException {
    private static final long serialVersionUID = 3586056020163124175L;

    public URLFormatException(Exception e){
        super(e);
    }
}
