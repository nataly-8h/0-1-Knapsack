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
	public static<E extends Comparable<E>> void quicksort(Items[] itemList) {
		swap(itemList, 0, (int) Math.random()*itemList.length);
		quicksort(itemList, 0,itemList.length-1);
	}

	private static<E extends Comparable<E>> void quicksort(Items[] datos, int left, int right) {
		if(left<right) {
			int p = particionar(datos,left,right);
			quicksort(datos,left, p-1);
			quicksort(datos, p+1, right);
		}
	}

	private static<E extends Comparable<E>> int particionar(Items[] datos, int left, int right) {
		int i = left + 1;
		for(int j = left+1; j<=right; j++) {
			if(datos[j].getRatio()>(datos[left].getRatio())) {
				swap(datos, i, j);
				i++;
			}
		}
		swap(datos, left, i-1);

		return i-1;
	}

	private static<E extends Comparable<E>> void swap(Items[] itemList, int i, int j) {
		Items aux = itemList[j];
		itemList[j]=itemList[i];
		itemList[i]=aux;

	}

	//////////////////////

	public static void knapsack(int[] values, int[] weight, int maxWeight){
		Items[] itemList = new Items[values.length];
		int finalValue = 0;
		String res = "";
		String peso = "";
		
		for(int i = 0; i < values.length; i++) {
			itemList[i] = new Items(values[i], weight[i], i);
		}
		
		quicksort(itemList);
		
		for(int i = 0; i < values.length; i++) {
			if(maxWeight-itemList[i].getWeight()>=0) {
				maxWeight -= itemList[i].getWeight();
				finalValue += itemList[i].getValue();
				res += itemList[i].getPosition()+1 + " ";
				peso += itemList[i].getWeight() + " ";
			}
		}
		
		if(finalValue==0) {
			System.out.println("No se pudo agregar ningún objeto a la mochila");
		}else {
			System.out.println("El valor máximo es: " + finalValue);
			System.out.println("Utilizando los objetos #: " + res);
			System.out.println("Con los pesos: " + peso);
		}

	}
}

class Items{
	private int value, weight, position;
	private float ratio;
	
	public Items(int value, int weight, int position) {
		super();
		this.value = value;
		this.weight = weight;
		this.position = position;
		this.ratio = (float) value/weight;
	}

	
	public int getPosition() {
		return position;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	public float getRatio() {
		return ratio;
	}

	public static void main(String[] args) {
		
	}
}
