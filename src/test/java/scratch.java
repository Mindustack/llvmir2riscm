import kvtodev.mindustack.llvmir2riscm.share.Lang;

class Scratch {
    public static void main(String[] args) {
        for (int i = 0; i < Lang.Reg.size(); i++) {
            System.out.print(i +": "+Lang.Reg.get(i)+' ');
        }
    }
}