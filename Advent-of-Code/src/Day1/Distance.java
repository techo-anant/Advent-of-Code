package LAB4;

import java.io.*;

public class Distance {
	public static void main(String args[]) {
	String inputFilePath = "/Users/hfspl/Desktop/Advent/Input";
	
	Heap h1 = new Heap(1000);
	Heap h2 = new Heap(1000);
	
	String line;
	String[] lines;
	
	int[] dis = new int[1000];
	
	int i=1;
	
	int sum=0;
	
	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));){
		while((line = bufferedReader.readLine()) != null) {
			lines = line.split("   ");
			
			System.out.print(i+". "+lines[0] );
			System.out.println("   "+ lines[1] );
        
			h1.insertItem(Integer.parseInt(lines[0]));
			h2.insertItem(Integer.parseInt(lines[1]));
			i++;
		}
		
		for(int j=0; j<1000; j++) {
			int fromHeap1, fromHeap2;
			fromHeap1 = h1.removeMin();
			fromHeap2 = h2.removeMin();
			if(fromHeap1>fromHeap2) {
				dis[j] = fromHeap1 - fromHeap2;
			}else {
				dis[j] = fromHeap2 - fromHeap1;
			}
			System.out.println((j+1)+". "+fromHeap1+" , "+fromHeap2+ " : "+ dis[j]);
			sum += dis[j];
		}
		System.out.println();
		System.out.println("Total distance is: "+sum);
		

	} catch (FileNotFoundException e) {
        System.err.println("Error: Input file not found - " + e.getMessage());
    } catch (IOException e) {
        System.err.println("Error: An I/O error occurred - " + e.getMessage());
    }
	
	}
}
