private static void selectionSort(int[] arr) {
	int min;

	for(int idx = 0; idx < arr.length-1; idx++) {
		min = idx;
		for(int scan = idx + 1; scan < arr.length; scan++) {
			if(arr[scan] < arr[min]) {
				min = scan;
			}
		}
		swap(min, idx, arr);
	}

}
