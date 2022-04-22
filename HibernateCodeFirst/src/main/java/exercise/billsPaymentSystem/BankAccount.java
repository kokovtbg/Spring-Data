package exercise.billsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "bank_account")
public class BankAccount extends BillingDetail{
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "swift_code")
    private String swiftCode;
}
