package us.lsi.flujossecuenciales;

import java.util.Iterator;


public class IteratorZip<A, B> implements Iterator<Pair<A,B>>,Iterable<Pair<A,B>> {
	
	public static <A, B> IteratorZip<A, B> of(Iterator<A> iteratorA, Iterator<B> iteratorB) {
		return new IteratorZip<A, B>(iteratorA, iteratorB);
	}
	
	private Iterator<A> iteratorA;
	private Iterator<B> iteratorB;
	
	private IteratorZip(Iterator<A> iteratorA, Iterator<B> iteratorB) {
		super();
		this.iteratorA = iteratorA;
		this.iteratorB = iteratorB;
	}
	@Override
	public Iterator<Pair<A, B>> iterator() {
		return this;
	}
	@Override
	public boolean hasNext() {
		return this.iteratorA.hasNext() && this.iteratorB.hasNext();
	}
	@Override
	public Pair<A, B> next() {
		return Pair.of(this.iteratorA.next(), this.iteratorB.next());
	}

}
