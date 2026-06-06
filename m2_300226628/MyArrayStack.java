/**
 * COPYRIGHT MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Mehrdad Sabetzadeh
 * 
 * Name: Yannick Vaillancourt
 * Student id: 300226628 
 */

public class MyArrayStack<E> extends ArrayStack<E> {

	public Stack<Pair<E>> pairUp() {

        Stack<E> original = this;
        Stack<E> originalReverse = new ArrayStack<E>();
        Stack<Pair<E>> paired = new ArrayStack<Pair<E>>();
        Stack<Pair<E>> reverse = new ArrayStack<Pair<E>>();
        Pair<E> pair;
        E target;

        while (!original.isEmpty()){
            target = original.pop();
            originalReverse.push(target);
            pair = new Pair((Object)target, (Object)target);
            reverse.push(pair);
        }
        while (!reverse.isEmpty()){
            paired.push(reverse.pop());
        }
        while (!originalReverse.isEmpty()){
            original.push(originalReverse.pop());
        }
        return paired;

	}
}