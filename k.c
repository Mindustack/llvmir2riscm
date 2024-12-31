extern int x;
static int y=x>1? 0:x;
static int z[]={6,6,6};
void foo();
[[clang::optnone]]
int main() {
  x = 1;
  y+=3;
  foo();
  y-=z[0]+z[1]+z[2];
  return x;
}
