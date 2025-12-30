import Models.Template.OrderProcessor;
import Models.Template.PhoneOrderProcessor;

public class Main {
    public static void main(String[] args) {
        OrderProcessor processor = new PhoneOrderProcessor();
        processor.processOrder();
    }
}
