import java.util.*;

public class GreedKnapsack {
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(System.in);
			int objectNumber = sc.nextInt();
			DynKnapsack.validObjectNumber(objectNumber);

			int[] objectValues = new int[objectNumber];
			for(int i = 0;i<objectNumber;i++) {
				objectValues[i] = sc.nextInt();
			}
			DynKnapsack.validObjectList(objectValues, objectNumber);

			int[] objectWeights = new int[objectNumber];
			for(int i = 0;i<objectNumber;i++) {
				objectWeights[i] = sc.nextInt();
			}
			DynKnapsack.validObjectList(objectWeights, objectNumber);


			int WeightMax = sc.nextInt();
			DynKnapsack.isPositive(WeightMax);

			knapsack(objectValues, objectWeights, WeightMax);

			sc.close();
		}catch(IllegalArgumentException | InputMismatchException ex){
			//Se imprime el mensaje de la excepcion

			if(ex.getMessage() == "null")
				System.out.println("Los Strings no son aceptados");
			else
				System.out.println(ex.getMessage());
		}



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
			System.out.print(itemList[i].getRatio() + " ");
		}

		System.out.println();

		for(int i = 0; i < values.length; i++) {
			int tempWeight = maxWeight-itemList[i].getWeight();
			if(tempWeight>=0) {
				maxWeight = tempWeight;
				finalValue += itemList[i].getValue();
				res += itemList[i].getPosition()+1 + " ";
				peso += itemList[i].getWeight() + " ";
			}else {
				break;
			}
		}

		if(finalValue==0) {
			System.out.println("No se pudo agregar ning�n objeto a la mochila");
		}else {
			System.out.println("El valor m�ximo es: " + finalValue);
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

	//Metodos de validacion

	//Validar que los numeros de objetos sean aceptados
	public static void validObjectNumber(int a){
		if(a <= 0)
			throw new IllegalArgumentException("El numero de objetos debe ser mayor a 0");
	}

	//Validar que sea positivo
	public static void isPositive(int a){
		if(a < 0)
			throw new IllegalArgumentException("Los datos deben ser positivos");
	}

	//Validar que la lista de objetos y pesos sea aceptada
	public static void validObjectList(int[] b, int a){
		if(b.length != a){
			throw new IllegalArgumentException("La cantidad de objetos y pesos debe coincidir con el número de objetos");
		}

		for(int i = 0; i < b.length; i++){
			if(b[i] < 0){
				throw new IllegalArgumentException("Los datos deben ser positivos");
			}
		}
	}
}
