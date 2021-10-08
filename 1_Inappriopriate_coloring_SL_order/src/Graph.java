package Projekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Graph {
	private int n;
	private int m;
	private ArrayList<ArrayList<Integer>> listasas;

	public Graph(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Number of nodes cannot be less than 0");
		this.n = n;
		this.m = 0;
		listasas = new ArrayList<ArrayList<Integer>>(n);
		for (int i = 0; i < n; i++) {
			listasas.add(i, new ArrayList<Integer>());

		}

	}

	public int getN() { //return number of Graph nodes
		return n;
	}

	public int getDegree(int i) {
		if (this.ifNode(i))
			return this.neighbors(i).size();
		else
			return -1;
	}

	public ArrayList<Integer> getDegrees() {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = 0; i < this.getN(); i++) {
			l.add(listasas.get(i).size());
		}
		return l;
	}

	public ArrayList<ArrayList<Integer>> getListaSas() {
		return listasas;
	}

	public int getSizeOfList(int i) {
		return listasas.get(i).size();
	}

	public int getM() {
		return m;
	}

	public Graph(Scanner in) {
		this(in.nextInt());
		while (in.hasNextInt()) {
			this.addEdge(in.nextInt(), in.nextInt());
		}
	}

	public boolean ifNode(int u) {
		if (u < 0 || u > n - 1)
			return false;
		return true;
	}

	public boolean ifEdge(int u, int w) {
		if (ifNode(u) && ifNode(w)) {
			if (listasas.get(u).contains(w))// its enough to check one neighbors list
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean addEdge(int u, int w) {
		if (ifNode(u) && ifNode(w)) {
			listasas.get(u).add(w);
			listasas.get(w).add(u);
			m++;
			return true;
		} else
			return false;
	}

	public void deleteEdge(int u, int w) {
		if (ifNode(u) && ifNode(w)) {
			for (int i = 0; i < this.getSizeOfList(u); i++) {
				if (listasas.get(u).get(i) == w) {
					listasas.get(u).remove(i);
					break;
				}
			}
			for (int i = 0; i < this.getSizeOfList(w); i++) {
				if (listasas.get(w).get(i) == u) {
					listasas.get(w).remove(i);
					break;
				}
			}
		}

	}

	public int getMaxDegree() {
		int max = Collections.max(this.getDegrees());
		return max;

	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Number of nodes =" + n + " number of edges=" + m + "\n");
		for (int i = 0; i < n; i++) {
			s.append(i + " ");
			s.append(listasas.get(i).toString() + "\n");
		}
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> neighbors(int u) {
		if (ifNode(u)) {
			return (ArrayList<Integer>) listasas.get(u).clone();

		} else
			throw new IllegalArgumentException(u + " is not node of this graph");
	}

}