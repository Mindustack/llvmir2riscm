int head(int a[2]) {
    return a[0];
}

[[clang::optnone]] int main()
{
    int a [2];
    int c;
    a[0] = 1;
    a[1] = 2;
    c = head(a);
    return 0;
}
