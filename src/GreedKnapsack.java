import java.util.*;

public class GreedKnapsack {
	private int max = 15;

	public static void main(String[] args) {

	}

	/////////////QUICKSORT
	public static<E extends Comparable<E>> void quicksort(E[] datos) {
		swap(datos, 0, (int) Math.random()*datos.length);
		quicksort(datos, 0,datos.length-1);
	}

	private static<E extends Comparable<E>> void quicksort(E[] datos, int left, int right) {
		if(left<right) {
			int p = particionar(datos,left,right);
			quicksort(datos,left, p-1);
			quicksort(datos, p+1, right);
		}
	}

	private static<E extends Comparable<E>> int particionar(E[] datos, int left, int right) {
		int i = left + 1;
		for(int j = left+1; j<=right; j++) {
			if(datos[j].compareTo(datos[left])<0) {
				swap(datos, i, j);
				i++;
			}
		}
		swap(datos, left, i-1);

		return i-1;
	}

	private static<E extends Comparable<E>> void swap(E[] valores, int i, int j) {
		E aux = valores[j];
		valores[j]=valores[i];
		valores[i]=aux;

	}

	public static<E extends Comparable<E>> void imprimeArreglo(E[] valores) {
		for(E valor:valores){
			System.out.print(valor+"  ");
		}
		//System.out.println();
	}
	//////////////////////

	public static void knapsack(int[] profit, int[] weight){
		Float[] relation = new Float[profit.length];
		for(int i = 0; i<profit.length; i++) {
			relation[i] = (float)profit[i]/weight[i];
			System.out.print(relation[i] + " ");
		}
		System.out.println();
		quicksort(relation);
		imprimeArreglo(relation);


		//return relation;
	}




	public static void GreedyKnapSack(int objectNumber, int[] objectValues, int[] objectWeights, int maxWeight) {

	}



}
