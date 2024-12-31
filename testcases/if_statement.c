int a;
[[clang::optnone]] int main()
{
	a = 2;
	if (a > 1) {
		a--;
	}
	return 0;
}
