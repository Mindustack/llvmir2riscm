int x;
static int y=0;
[[clang::optnone]]
void foo();
void foo() {
y=x*x;
  x = x+y;
}