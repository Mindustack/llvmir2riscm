
[[clang::optnone]] int main() {
  int a, i, b, c;
  a = 10;
  b = 4;
  c = 9;
label:

  a--;
  if (a > 5)
    goto label;

  switch (c) {
  case 6:
    c--;
  case 5:
    c--;
  case 4:
    c--;
  default:
    c = 15;
  }
  return 0;
}
