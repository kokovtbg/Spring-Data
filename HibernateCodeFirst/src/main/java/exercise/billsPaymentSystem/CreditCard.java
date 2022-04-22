package exercise.billsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue(value = "credit_card")
public class CreditCard extends BillingDetail {
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "expiration_date")
    private Timestamp expirationDate;
}
