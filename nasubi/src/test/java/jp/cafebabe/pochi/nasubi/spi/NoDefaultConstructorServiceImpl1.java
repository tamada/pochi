package jp.cafebabe.pochi.nasubi.spi;

public class NoDefaultConstructorServiceImpl1 extends NoDefaultConstructorService {

    public NoDefaultConstructorServiceImpl1(String string) {
        super(string);
    }

    public String type(){
        return "service2-1";
    }
}
