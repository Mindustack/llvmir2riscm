int a,b;
[[clang::optnone]] int main()
{
	a = 2;
	a = 3;
	b = a;
	return 0;
}
