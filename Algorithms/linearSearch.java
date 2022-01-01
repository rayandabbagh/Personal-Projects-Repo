private static boolean linearSearch(int target, int[] pool) {
	boolean found = false;
	int idx = 0;

	while(!found && idx < pool.length) {
		if(pool[idx] == target) {
			found = true;
		} else {
			idx++;
		}
	}
	return found;
}
