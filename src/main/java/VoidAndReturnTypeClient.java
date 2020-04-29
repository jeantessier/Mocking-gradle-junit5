public class VoidAndReturnTypeClient {
    private VoidAndReturnType voidAndReturnType;

    public VoidAndReturnTypeClient(VoidAndReturnType voidAndReturnType) {
        this.voidAndReturnType = voidAndReturnType;
    }

    public void callVoid() {
        voidAndReturnType.methodWithVoid();
    }

    public void callReturnType() {
        voidAndReturnType.methodWithReturnType();
    }

    public void callVoidAndReturnType() {
        callVoid();
        callReturnType();
    }
}
