package hw2;

public class TypeCheckException extends RuntimeException {

    public TypeCheckException(){
	super();
    }

    public TypeCheckException(String s) {
	super(s);
    }

    public TypeCheckException(String s, Throwable throwable) {
	super(s, throwable);
    }

    public TypeCheckException(Throwable throwable) {
	super(throwable);
    }
}
