package ch17;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

public class TestLambdas {

	public static void main(String[] args) {

        Consumer<Object> c1 = x -> System.out.println(x);
        Consumer<Object> c2 = System.out::println;
        
        IntPredicate even = value -> value % 2 == 0;
        IntPredicate gr = value -> value > 5;
        
        if(even.or(gr).test(2)){
        	System.out.println("true");
        }

        c1.accept("Print via lambda");
        c2.accept("Print via method reference");
	}	
}
