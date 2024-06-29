int a;
[[clang::optnone]] int main()
{
	a = 2;
	if (a > 0) {
		a--;
	}
	else {
		a++;
	}
	return 0;
}
