int add(int kk[]){

return kk[4];
}
[[clang::optnone]] int main(){
	int a[10];
	int x;
	a[2] = 5;
	x = a[2];
	return add(a);
}
