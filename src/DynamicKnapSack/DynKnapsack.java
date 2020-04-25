import java.util.*;
import java.lang.IllegalArgumentException;

public class DynKnapsack {
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(System.in);
			int objectNumber = sc.nextInt();
			validObjectNumber(objectNumber);

			int[] objectValues = new int[objectNumber];
			for(int i = 0;i<objectNumber;i++) {
				objectValues[i] = sc.nextInt();
			}
			validObjectList(objectValues, objectNumber);

			int[] objectWeights = new int[objectNumber];
			for(int i = 0;i<objectNumber;i++) {
				objectWeights[i] = sc.nextInt();
			}
			validObjectList(objectWeights, objectNumber);

			int WeightMax = sc.nextInt();
			isPositive(WeightMax);

			DynamicKnapSack(objectNumber, objectValues, objectWeights, WeightMax);

			sc.close();

		}catch(IllegalArgumentException | InputMismatchException ex){
			//Se imprime el mensaje de la excepcion
			
			if(ex.getMessage() == "null")
				System.out.println("Solo se aceptan numeros enteros");
			else
				System.out.println(ex.getMessage()); 
		}

	}

	public static void DynamicKnapSack(int objectNumber, int[] objectValues, int[] objectWeights, int maxWeight) {
		long startTime = System.nanoTime();
		int maxValue = 0;
		ArrayList<Integer> objectsUsed = new ArrayList<Integer>();
		int[][] dynamicMatrix = new int[objectNumber][maxWeight+1];
		for(int i = 0; i<=maxWeight; i++) {
			if(i>=objectWeights[0]) {
				dynamicMatrix[0][i] = objectValues[0];
			} else {
				dynamicMatrix[0][i] = 0;
			}
		}
		if(objectNumber == 1) {
			maxValue = dynamicMatrix[0][maxWeight];
			objectsUsed.add(1);
		} else {
			for(int i = 1; i<objectNumber; i++) {
				for(int j=0; j<=maxWeight; j++) {
					if(j>=objectWeights[i]) {
						dynamicMatrix[i][j] = Math.max(objectValues[i]+dynamicMatrix[i-1][j-objectWeights[i]],dynamicMatrix[i-1][j]);
					} else {
						dynamicMatrix[i][j] = dynamicMatrix[i-1][j];
					}
				}
			}
		}
		System.out.println("Matriz resultante: (n)*(w+1):");
		for(int i = 0; i<objectNumber; i++) {
			System.out.println();
			for(int j=0; j<=maxWeight; j++) {
				System.out.print(dynamicMatrix[i][j]);
				System.out.print(" ");
			}
		}
		maxValue = dynamicMatrix[objectNumber-1][maxWeight];
		System.out.println();
		System.out.println();
		System.out.println("Maximo beneficio encontrado:");
		System.out.println(maxValue);
		while(objectNumber-1>=0) {
			if(objectNumber-2==-1) {
				if(dynamicMatrix[objectNumber-1][maxWeight]!=0) {
					objectsUsed.add(objectNumber);

				}
				break;
			} else if(dynamicMatrix[objectNumber-1][maxWeight]>dynamicMatrix[objectNumber-2][maxWeight]) {
				objectsUsed.add(objectNumber);
				maxWeight-=objectWeights[objectNumber-1];
				objectNumber-=1;
			} else {
				objectNumber-=1;
			}
		}
		System.out.println();
		System.out.println("Objetos usados:");
		for(int i = 0;i<objectsUsed.size();i++) {
			System.out.print("Objeto #" + objectsUsed.get(i)+ ",");
			System.out.print(" ");
		}
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds  : " + timeElapsed);
		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
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