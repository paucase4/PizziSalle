package Models.Template;

public abstract class OrderProcessor {
    // PATTERN: Template Method
    // This class defines the skeleton of the order processing algorithm
    // in the method processOrder(). The sequence of steps is fixed
    // (create customer, create order, take products, print order),
    // but the concrete implementation of each step is delegated to
    // subclasses. This allows different order types (phone, web, in-person)
    // to reuse the same workflow while customizing specific steps.
    //
    // For further information on the concrete implementation of the template pattern
    // subclass resolution read the PhoneOrderProcessor PATTERN comment.

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
