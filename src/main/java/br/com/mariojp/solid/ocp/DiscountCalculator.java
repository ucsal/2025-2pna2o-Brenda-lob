package br.com.mariojp.solid.ocp;

import java.util.Map;
import java.util.HashMap;

public class DiscountCalculator {
    private final Map<CustomerType, DiscountPolicy> policies;
    
    public DiscountCalculator() {
        this.policies = new HashMap<>();
        this.policies.put(CustomerType.REGULAR, new RegularPolicy());
        this.policies.put(CustomerType.PREMIUM, new PremiumPolicy());
        this.policies.put(CustomerType.PARTNER, new PartnerPolicy());
    }
    
    public DiscountCalculator(Map<CustomerType, DiscountPolicy> policies) {
        this.policies = new HashMap<>(policies);
    }
    
    public double apply(double amount, CustomerType type) {
        DiscountPolicy policy = policies.get(type);
        if (policy != null) {
            return policy.apply(amount);
        }
        return amount;
    }
}
