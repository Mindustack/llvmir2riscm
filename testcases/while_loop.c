int a, b, c;
[[clang::optnone]] int main()
{
	a = 0;
	b = 1;
	c = 2;
	while (a > b) c = 1;
	while (c > a) {
		a++;
	}
	return 0;
}
