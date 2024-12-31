package kvtodev.mindustack.llvmir2riscm.share;

public class Pointer <E> {
   public E pointed;

    public Pointer() {
    }

    public Pointer(E pointed) {
        this.pointed = pointed;
    }

//    public void set(E pointed) {
//        this.pointed = pointed;
//    }
//
//    public E get(){
//        return pointed;
//    }
}
