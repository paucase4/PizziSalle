package Models.Template;

public abstract class OrderProcessor {
    //PATTERN: Template

    public final void processOrder() {
        createCustomer();
        createOrder();
        takeProducts();
        printOrder();
    }

    protected abstract void createCustomer();
    protected abstract void createOrder();
    protected abstract void takeProducts();
    protected abstract void printOrder();
}
