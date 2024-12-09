package Day5;

import java.io.*;
import java.util.*;

public class Q1_Check {
	public static void main(String[] args) {
    	ArrayList<Integer> orders = new ArrayList<>();
    	String input;
    	String[] order;
    	String inputFilePath1 = "/Users/hfspl/Desktop/Advent/Day5Order";
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath1));){
			while((input = bufferedReader.readLine()) != null) {
				order = input.split("\\|");
				if(!orders.contains(Integer.parseInt(order[0])) && !orders.contains(Integer.parseInt(order[1]))) {
					orders.add(Integer.parseInt(order[0]));
					orders.add(Integer.parseInt(order[1]));
				}else if(!orders.contains(Integer.parseInt(order[0])) && orders.contains(Integer.parseInt(order[1]))) {
					orders.add(Integer.parseInt(order[0]));
					Collections.swap(orders, indexOf(orders, Integer.parseInt(order[0])), indexOf(orders, Integer.parseInt(order[1])));
				}else if(orders.contains(Integer.parseInt(order[0])) && !orders.contains(Integer.parseInt(order[1]))) {
					orders.add(Integer.parseInt(order[1]));
				}else {
					if(indexOf(orders, Integer.parseInt(order[0])) > indexOf(orders, Integer.parseInt(order[1]))) {
						Collections.swap(orders, indexOf(orders, Integer.parseInt(order[0])), indexOf(orders, Integer.parseInt(order[1])));
					}
				}
			}
			System.out.println(orders);
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
    	
    	String update;
    	String[] updates;
    	int sum =0;
    	String inputFilePath2 = "/Users/hfspl/Desktop/Advent/Day5Updates.xml";
    	
    	try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath2));){
			while((update = bufferedReader.readLine()) != null) {
				updates = update.split(",");
				if(isOrdered(orders, updates)) {
					sum += Integer.parseInt(updates[(updates.length)/2]);
				}
			}
    	}catch (FileNotFoundException e) {
	        System.err.println("Error: Input file not found - " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error: An I/O error occurred - " + e.getMessage());
	    }
    	System.out.println("The sum is: "+ sum);
    	
    }
	
	public static int indexOf(List<Integer> list, int a) {
		int i =0;
		for(int item : list) {
			if(item == a) {
				break;
			}
			i++;
		}
		return i;
	}
	
	public static boolean isOrdered(List<Integer> list, String[] update) {
		ArrayList<Integer> index = new ArrayList<>();
		for(String item : update) {
			index.add(indexOf(list, Integer.parseInt(item)));
		}
		for(int i =0; i<index.size()-1; i++) {
			if(index.get(i)>index.get(i+1)) {
				return false;
			}
		}
		return true;
		
//		int i = 0,j = 0;
//		while(i < list.size() && j<update.length) {
//			if(list.get(i) == Integer.parseInt(update[j])) {
//				i++;
//				j++;
//			}else {
//				i++;
//			}
//		}
//		if(j<update.length) {
//			return false;
//		}else {
//			return true;
//		}
	}
	
}
