package hackerrank.queues.ataleoftwostacks;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
    
    static class MyQueue<T> implements Queue<T> {
    	private Queue<T> q = new LinkedList<>();
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return q.size();
		}

		public void dequeue() {
			// TODO Auto-generated method stub
			q.remove();
		}

		public void enqueue(T o) {
			// TODO Auto-generated method stub
			q.add(o);
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return q.size() == 0;
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return q.contains(o);
		}

		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return q.iterator();
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return q.toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return q.toArray(a);
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return q.remove(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return q.containsAll(c);
		}

		@Override
		public boolean addAll(Collection<? extends T> c) {
			// TODO Auto-generated method stub
			return q.addAll(c);
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return q.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return q.retainAll(c);
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			q.clear();
		}

		@Override
		public boolean add(T e) {
			// TODO Auto-generated method stub
			return q.add(e);
		}

		@Override
		public boolean offer(T e) {
			// TODO Auto-generated method stub
			return q.offer(e);
		}

		@Override
		public T remove() {
			// TODO Auto-generated method stub
			return q.remove();
		}

		@Override
		public T poll() {
			// TODO Auto-generated method stub
			return q.poll();
		}

		@Override
		public T element() {
			// TODO Auto-generated method stub
			return q.element();
		}

		@Override
		public T peek() {
			// TODO Auto-generated method stub
			return q.peek();
		}
    }
}