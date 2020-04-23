import java.util.*;

public class GreedKnapsack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int objectNumber = sc.nextInt();
		int[] objectValues = new int[objectNumber];
		for(int i = 0;i<objectNumber;i++) {
			objectValues[i] = sc.nextInt();
		}
		int[] objectWeights = new int[objectNumber];
		for(int i = 0;i<objectNumber;i++) {
			objectWeights[i] = sc.nextInt();
		}
		int WeightMax = sc.nextInt();
		
		knapsack(objectValues, objectWeights, WeightMax);
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

	//////////////////////

	public static void knapsack(int[] values, int[] weight, int maxWeight){
		Float[] relation = new Float[values.length];
		Hashtable<Float, Integer> datos = new Hashtable<Float, Integer>();
		
		for(int i = 0; i<values.length; i++) {
			relation[i] = (float)values[i]/weight[i];
			datos.put(relation[i], i);
		}
		quicksort(relation);
		
		//ejemplo que usé: 7 10 5 15 7 6 18 3 2 3 5 7 1 4 20 15
		boolean[] used = new boolean[relation.length];
		for(int i = 0; i<relation.length; i++) {
			int tempWeight = weight[datos.get(relation[relation.length-1-i])];
			maxWeight -= tempWeight;
			if(maxWeight <= 0) {
				break;	
			}else {
				//System.out.println("i: " + i + " iWe: " + datos.get(relation[relation.length-1-i]) + " temp: " + tempWeight + " max: " + maxWeight + " ");
				used[datos.get(relation[relation.length-1-i])] = true;
			}	
		}

		int maxValue = 0;
		maxWeight = 0;
		for(int i = 0; i<values.length; i++) {
			if(used[i]) {
				maxValue += values[i];
				maxWeight += weight[i];
			}
		}
		
		System.out.println();
		for(int i = 0; i<weight.length; i++) {
			System.out.print(used[i] + " ");
		}
		System.out.println();
		System.out.println("Maximum Value: " + maxValue + " Maximum Weight: " + maxWeight);
		
	}
}
