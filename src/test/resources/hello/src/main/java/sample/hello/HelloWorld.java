package sample.hello;

/**
 * Hello world!
 *
 */
public class HelloWorld{
    public String hello(String name){
        StringBuilder sb = new StringBuilder("Hello, ");
        sb.append(name);
        sb.append("!");
        return new String(sb);
    }

    public String hello(){
        return hello("World");
    }
}
