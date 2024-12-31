struct TEST {
  int a;
  int b;
  int c;
  int d;
};

struct TEST add(struct TEST t) {
  struct TEST t2;
  t2.a = t.a;
  return t2;
}

[[clang::optnone]] int main() {
  struct TEST a;
  struct TEST b;
  a.a = 1;
  return add(a).a;
}
