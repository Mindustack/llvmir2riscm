int a;
[[clang::optnone]] int main()
{
	a = 2;
	while (a) {
		a--;
		continue;
		a--;
	}
	return a;
}
