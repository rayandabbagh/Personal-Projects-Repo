private static void insertionSort(int[] arr) {
	int idx2;
	for(int idx1 = 1; idx1 < arr.length; idx1++) {
		idx2 = idx1;
		while(idx2 > 0 && arr[idx2] < arr[idx2-1]) {
			swap(idx2-1, idx2, arr);
			idx2--;
		}		
	}
}

private static void swap(int idx1, int idx2, int[] arr) {
	int temp = arr[idx1];
	arr[idx1] = arr[idx2];
	arr[idx2] = temp;
}
