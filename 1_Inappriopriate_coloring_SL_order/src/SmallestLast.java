package Projekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class SmallestLast {
	private int[] colors;
	private Graph G;
	private int freeColors[];
	private ArrayList<Integer> K;
	private int[] nodes;
	private int degrees[];

	public SmallestLast(Graph g) {
		K = new ArrayList<>();
		this.G = g;
		this.colors = new int[g.getN()];
		this.freeColors = new int[g.getN()];
		this.degrees = new int[g.getN()];
		this.nodes = new int[g.getN()];

		for (int i = 0; i < g.getN(); i++) {
			nodes[i] = i;
		}

		for (int i = 0; i < g.getN(); i++) {
			degrees[i] = G.getDegrees().get(i);
		}

	}

	// 37-70 Greedy algorithm for establishing the order SL.


	public void color() {
		boolean[] canReduce = new boolean[G.getN()];
		Arrays.fill(canReduce, true);
		int index = 0;
		int min = degrees[0];

		for (int i = 0; i < G.getN(); i++) {
			// This loop search for actual smallest degree node
			for (int u = 0; u < G.getN(); u++) {
				if (canReduce[u] == true) {
					if (degrees[u] <= min) {
						min = degrees[u];
						index = u;
					}
				}
			}
			degrees[index] = 0; // "deleting" node from graph
			canReduce[index] = false;
			K.add(nodes[index]); // adding deleted node to order
			// This loop decreases degree of deleted node's neighbors)
			for (int g : G.neighbors(index)) {
				if (canReduce[g] == true)
					degrees[g] = degrees[g] - 1;
			}
//This loop sets min as the node with smallest degree (greater than 0), 
			// so it can be "deleted" from graph
			for (int j = 0; j < G.getN(); j++) {
				if (canReduce[j] == true) {
					if (degrees[j] > min) {
						min = degrees[j];
					}
				}
			}
		}
		// K is now smallest first, so it needs to reversed for Smallest Last order

		Collections.reverse(K);
		
		int[] neighborsInColor = new int[G.getN()];

		Arrays.fill(neighborsInColor, 0); // no node have neighbor in color yet
		Arrays.fill(colors, -1); //every node is uncolored
		colors[K.get(0)] = 0; //first node gets first color


		Arrays.fill(freeColors, 0);
		// 83-118 1-Inappropriate coloring
		for (int u = 1; u < G.getN(); u++) {
			Iterator<Integer> it = G.getListaSas().get(K.get(u)).iterator();
			while (it.hasNext()) { // iterating through neighbors of K.get(u)
				int i = it.next();
				if (colors[i] != -1) { // checking if node i already got color
					if (neighborsInColor[i] == 1) // checking if i got neighbors in the same color as i
						freeColors[colors[i]] = 2; // if yes, blocking neighbors of i from
					// getting the same color
					else {
						freeColors[colors[i]] = 1; // else neighbor of i is allowed to have the same color as i
					}
				}
			}
			int flag = 0;
			int color; // int meaning color
			for (color = 0; color < G.getN(); color++) {
				if (freeColors[color] == 0) // color is available without any neighbor having it
					break;
				else if (freeColors[color] == 1) { //color is available with one neighbor having it
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				colors[K.get(u)] = color; // K.get(u) gets different color than neighbor
			} else {
				colors[K.get(u)] = color; // K.get(u) gets the same color as neighbor
				neighborsInColor[K.get(u)] = 1;
				for (int v : G.getListaSas().get(K.get(u))) {
					if (colors[v] == color) {
						neighborsInColor[v] = 1; // "letting know" neighbors that they have neighbor in that color
					}
				}
			}
		}
	}

	public ArrayList<Integer> getK() {
		return K;
	}

	public int getColor(int i) {
		return colors[i];
	}

}
