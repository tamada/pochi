package jp.cafebabe.pochi.util.spi;

public class NoDefaultConstructorServiceImpl1 extends NoDefaultConstructorService {

    public NoDefaultConstructorServiceImpl1(String string) {
        super(string);
    }

    public String type(){
        return "service2-1";
    }
}
