package LAB4;

import java.io.*;

public class checking {
	public static void main(String args[]) {
	String inputFilePath = "/Users/hfspl/Desktop/Advent/Input";
	
	int[] a1 = {3,4,2,1,3,3};
	int[] a2 = {4,3,5,3,9,3};
	
	String line;
	String[] lines;
	
	int[] sim = new int[6];
	
	
	int temp, counter;
	
	int sum=0;
	
	
	
//	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
//		while((line = bufferedReader.readLine()) != null) {
//			lines = line.split("   ");
//        
//			a1[i] = Integer.parseInt(lines[0]);
//			a2[i] = Integer.parseInt(lines[1]);
//			i++;
//			System.out.print(i+". "+lines[0]);
//			System.out.println("   "+ lines[1]);
//		}
//		
		for(int j =0; j<6; j++) {
			counter =0;
			temp = a1[j];
			System.out.print((j+1)+". "+temp);
			for(int k=0; k<6; k++) {
				if(a2[k] == temp) {
					counter++;
				}
			}
			sim[j] = temp*counter;
			System.out.println("  "+ counter+" : " +sim[j]);
			sum += sim[j];
		}
		
		System.out.println("Total similarity:  "+ sum);
		
		
		
		
//		for(int j=0; j<1000; j++) {
//			int fromHeap1, fromHeap2;
//			fromHeap1 = h1.removeMin();
//			fromHeap2 = h2.removeMin();
//			if(fromHeap1>fromHeap2) {
//				dis[j] = fromHeap1 - fromHeap2;
//			}else {
//				dis[j] = fromHeap2 - fromHeap1;
//			}
//			System.out.println((j+1)+". "+fromHeap1+" , "+fromHeap2+ " : "+ dis[j]);
//			sum += dis[j];
//		}
//		System.out.println();
//		System.out.println("Total distance is: "+sum);
//		

//	} catch (FileNotFoundException e) {
//        System.err.println("Error: Input file not found - " + e.getMessage());
//    } catch (IOException e) {
//        System.err.println("Error: An I/O error occurred - " + e.getMessage());
//    }
	
	}
}
