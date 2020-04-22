import java.util.*;

public class DynKnapsack {
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
		DynamicKnapSack(objectNumber, objectValues, objectWeights, WeightMax);
	}
	
	
	
	
	
	public static void DynamicKnapSack(int objectNumber, int[] objectValues, int[] objectWeights, int maxWeight) {
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
		for(int i = 0; i<objectNumber; i++) {
			System.out.println();
			for(int j=0; j<=maxWeight; j++) {
				System.out.print(dynamicMatrix[i][j]);
				System.out.print(" ");
			}
		}
		maxValue = dynamicMatrix[objectNumber-1][maxWeight];
		System.out.println();
		System.out.println(maxValue);
		while(maxValue!=0) {
			if(dynamicMatrix[objectNumber-1][maxWeight]>dynamicMatrix[objectNumber-2][maxWeight]) {
				objectsUsed.add(objectNumber);
				maxWeight-=objectWeights[objectNumber-1];
				maxValue = dynamicMatrix[objectNumber-1][maxWeight];
			} else {
				objectNumber-=1;
				maxValue = dynamicMatrix[objectNumber-1][maxWeight];
			}
		}
		System.out.println();
		for(int i = 0;i<objectsUsed.size();i++) {
			System.out.println(objectsUsed.get(i));
		}
	}
	
	
	
}