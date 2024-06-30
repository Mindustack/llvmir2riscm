
[[clang::optnone]] int main() {
	int arr[5];
	int* p[5];
	int i,flag;


	i = 0;
	while (i < 5) {
		p[i] = &arr[i];
		i++;
	}
	

	return 0;
}
