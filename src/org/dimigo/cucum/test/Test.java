package org.dimigo.cucum.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Integer> lists=new ArrayList<>();
		lists.add(3);
		lists.add(51);
		lists.add(12515);
		lists.remove(new Integer(3));
		for(Integer i :lists) {
			System.out.println(i);
		}
	}
}
