int add(int a, int b) {
    return a + b;
}

[[clang::optnone]] int main()
{
    int a,b,c;
    a = 1;
    b = 2;
    c = add(a, b);
    return 0;
}
