
public class ArraysExample {
	
	public static int[] merge(int[] a, int[] b) {
		int[] r = new int[a.length + b.length];
		int a_pos = 0;
		int b_pos = 0;
		int r_pos = 0;
		while (a_pos < a.length && b_pos < b.length) {
			if (a[a_pos] <= b[b_pos]) {
				r[r_pos++] = a[a_pos++];
	
			} else {
				r[r_pos++] = b[b_pos++];
			}
		}
		while (a_pos < a.length) {
			r[r_pos++] = a[a_pos++];
		}
		while (b_pos < b.length) {
			r[r_pos++] = b[b_pos++];
		}
		return r;
	}
	
	public static int[] mergeSort(int[] a, int start, int end) {
		if (end-start <= 1) return a;
		int mid = (start + end)/2;
		int[] left = mergeSort(a, start, mid);
		int[] right = mergeSort(a, mid+1, end);
		return merge(left, right);
	}

	public static void main(String[] args) {
		int[] a = {1,8,2,-1,5,6,7};
	}

}
