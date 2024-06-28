extern void print(double var);
double foo(double a, double b, double c) {

  double e, f;
  e = a + b * c - 1;
  f = 5 * b / (-3 * (c / a * b));
  return f;
}
[[clang::optnone]] int main() {
  double a, b, c;
  a = 3;
  b = 4;
  c = 5;
  double f = foo(a, b, c);

  print(f);

  return 0;
}
