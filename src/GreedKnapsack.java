import java.util.*;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;

public class GreedKnapsack {
	public static void main(String[] args) {
		try{ 
			Scanner sc = new Scanner(System.in);
			int objectNumber = sc.nextInt();
			validObjectNumber(objectNumber);

			int[] objectValues = new int[objectNumber];
			validObjectValues(objectValues, objectNumber);
			for(int i = 0;i<objectNumber;i++) {
				objectValues[i] = sc.nextInt();
			}

			int[] objectWeights = new int[objectNumber];
			for(int i = 0;i<objectNumber;i++) {
				objectWeights[i] = sc.nextInt();
			}

			int WeightMax = sc.nextInt();
	
			System.out.println(objectNumber);
			sc.close();

		}catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
		}

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

	public static void knapsack(int[] profit, int[] weight, int maxWeight){
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

	//Metodos de comprobación de input
	public static void validObjectNumber(int a){
		if(a <= 0)
			throw new IllegalArgumentException("La cantidad de objetos debe ser mayor a 0");
	}

	public static void validObjectValues(int [] b, int a){
		if(b.length != a)
			throw new IllegalArgumentException("La cantidad de objetos no es la misma que la cantidad de valores dados");
	}


}
