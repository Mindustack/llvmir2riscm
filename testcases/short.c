[[clang::optnone]] int main() {
	short a,b;
	a = 1;
	b = 2;
	b = a;
	a = 1;
	return 0;
}
