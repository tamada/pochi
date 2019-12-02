import java.util.List;
import java.util.stream.IntStream;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Fibonacci{
    public void run(){
        List<Integer> array = fibonacci(15);
        for(Integer item: array){
            System.out.println(item);
        }
    }

    public IntStream stream(){
        return IntStream.iterate(1, new FibonacciUnaryOperator());
    }

    public List<Integer> fibonacci(int max){
        return stream().limit(max)
            .mapToObj(item -> new Integer(item))
            .collect(Collectors.toList());
    }

    public static void main(String[] args){
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.run();
    }

    private static class FibonacciUnaryOperator implements IntUnaryOperator{
        private int prev = 0;

        public int applyAsInt(int operand){
            int next = prev + operand;
            prev = operand;
            return next;
        }
    };
}
