int kaljsflkjlkajsdfkjlaksjdflkjklajsdflkj() {
    int a,b;
    a = 1;
    b = 2;
    return a + b;
}

[[clang::optnone]] int main()
{
    int a,b,c;
    a = 1;
    b = 2;
    c = kaljsflkjlkajsdfkjlaksjdflkjklajsdflkj();
    return 0;
}
