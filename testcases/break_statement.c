int a;
[[clang::optnone]] int main()
{
	a = 1;
	while (a) {
		break;
	}
	return 0;
}
