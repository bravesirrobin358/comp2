/**
 * COPYRIGHT MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Mehrdad Sabetzadeh 
 */

public interface Stack<E> {
	boolean isEmpty();

	E push(E elem);

	E pop();

	E peek();
}