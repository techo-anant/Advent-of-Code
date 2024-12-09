package LAB4;

public class Heap {
	int[] H;
	int root = 1;
	int curLength;
	int length;
	
	public Heap(int length){
		H = new int[length+1];
		curLength = 0;
		this.length = length;
	}
	
	public void upheap(int idx) { // O(log n)
		if(idx == 1) return;
		if(H[idx] > H[idx/2]) {
			return;
		}else {
			int temp = H[idx];
			H[idx]  = H[idx/2];
			H[idx/2] = temp;
			upheap(idx/2);
		}
	}
	
	public void downheap(int idx) { // O(log n)
		if(idx > curLength) return;
		int less;
		if(idx*2<curLength) {
			less = (H[idx*2] > H[(idx*2)+1]) ? (idx*2)+1 : (idx*2);
		}else if(idx*2==curLength) {
			less = idx*2;
		}else {
			return;
		}
		
		if(H[idx] < H[less]) return;
		
		int temp  = H[idx];
		H[idx] = H[less];
		H[less] = temp;
		
		downheap(less);
	}
	
	public void insertItem(int item) { // O(log n)
		if(curLength > length ) {
			System.out.println("Heap is full.");
			return;
		}
		H[++curLength] = item;
		upheap(curLength);
	}
	
	public int removeMin() { // O(log n)
		int min = H[root];
		H[root] = H[curLength];
		H[curLength] = 0;
		curLength--;
		downheap(root);
		return min;
	}
	
	
}




