import java.util.EnumMap;

public class Bill {

    Bill() {
        amountPerQualification = new EnumMap<>(Qualification.class);
    }

    public void IncreaseAmount(Qualification qualification, Integer amount) {
        amountPerQualification.putIfAbsent(qualification, 0);
        amountPerQualification.put(qualification, amountPerQualification.get(qualification) + amount);
    }

    public void SetManagementPay(int managementPay) {
        this.managementPay = managementPay;
    }

    public int GetTotal() {
        int total = 0;
        for (Integer amount : amountPerQualification.values()) {
            total += amount;
        }
        total += managementPay;
        return total;
    }

    protected EnumMap<Qualification, Integer> amountPerQualification;
    protected Integer managementPay;
}
