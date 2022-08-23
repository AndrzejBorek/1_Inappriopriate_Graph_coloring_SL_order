package Projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		String file = "1_Inappriopriate_coloring_SL_order/Test_Graphs/Cycle_6.txt";
		Scanner in = new Scanner(new File(file));
		Graph g = new Graph(in);
		SmallestLast sl = new SmallestLast(g);
		sl.color();
		System.out.println(sl.getK());
		for (int i = 0; i < g.getN(); i++) {
			System.out.println("vertex " + i + " colour: " + sl.getColor(i));
		}
		System.out.println("Smallest quite difficult graph for normal SL order: ");
		String plik2 = "1_Inappriopriate_coloring_SL_order/Test_Graphs/Cycle_6.txt";
		Scanner in2 = new Scanner(new File(plik2));
		Graph g2 = new Graph(in2);
		SmallestLast sl2 = new SmallestLast(g2);
		sl2.color();
		System.out.println(sl2.getK());
		for (int i = 0; i < g2.getN(); i++) {
			System.out.println("vertex " + i + " colour: " + sl2.getColor(i));
		}
		System.out.println("Prismatoid (smallest hard graph for normal SL): ");
		String plik3 = "1_Inappriopriate_coloring_SL_order/Test_Graphs/Cycle_6.txt";
		Scanner in3 = new Scanner(new File(plik3));
		Graph g3 = new Graph(in3);
		SmallestLast sl3 = new SmallestLast(g3);
		sl3.color();
		System.out.println(sl3.getK());
		for (int i = 0; i < g3.getN(); i++) {
			System.out.println("vertex " + i + " colour: " + sl3.getColor(i));
		}
		String plik4 = "1_Inappriopriate_coloring_SL_order/Test_Graphs/Cycle_6.txt";
		Scanner in4 = new Scanner(new File(plik4));
		Graph g4 = new Graph(in4);
		SmallestLast sl4 = new SmallestLast(g4);
		sl4.color();
		System.out.println(sl4.getK());
		for (int i = 0; i < g4.getN(); i++) {
			System.out.println("vertex " + i + " colour: " + sl4.getColor(i));
		}
	}
}
