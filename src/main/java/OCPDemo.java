import br.com.mariojp.solid.ocp.*;
import java.util.Map;
import java.util.HashMap;

public class OCPDemo {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do OCP ===");
        
        System.out.println("\n1. Políticas básicas:");
        DiscountCalculator basicCalc = new DiscountCalculator();
        System.out.println("REGULAR 100 -> " + basicCalc.apply(100, CustomerType.REGULAR));
        System.out.println("PREMIUM 100 -> " + basicCalc.apply(100, CustomerType.PREMIUM));
        System.out.println("PARTNER 100 -> " + basicCalc.apply(100, CustomerType.PARTNER));
        
        System.out.println("\n2. Adicionando nova política VIP (sem modificar DiscountCalculator):");
        Map<CustomerType, DiscountPolicy> customPolicies = new HashMap<>();
        customPolicies.put(CustomerType.REGULAR, new RegularPolicy());
        customPolicies.put(CustomerType.PREMIUM, new PremiumPolicy());
        customPolicies.put(CustomerType.PARTNER, new PartnerPolicy());
        
        DiscountCalculator customCalc = new DiscountCalculator(customPolicies);
        System.out.println("Calculadora customizada criada com sucesso!");
        
        System.out.println("\n3. Usando políticas customizadas:");
        Map<CustomerType, DiscountPolicy> specialPolicies = new HashMap<>();
        specialPolicies.put(CustomerType.REGULAR, (amount) -> amount * 0.98);
        specialPolicies.put(CustomerType.PREMIUM, (amount) -> amount * 0.85);
        specialPolicies.put(CustomerType.PARTNER, (amount) -> amount * 0.75);
        
        DiscountCalculator specialCalc = new DiscountCalculator(specialPolicies);
        System.out.println("REGULAR com política especial: " + specialCalc.apply(100, CustomerType.REGULAR));
        System.out.println("PREMIUM com política especial: " + specialCalc.apply(100, CustomerType.PREMIUM));
        System.out.println("PARTNER com política especial: " + specialCalc.apply(100, CustomerType.PARTNER));
        
        System.out.println("=== OCP aplicado com sucesso! ===");
        System.out.println("Aberto para extensão: novas políticas podem ser adicionadas");
        System.out.println("Fechado para modificação: DiscountCalculator não precisa ser alterada");
    }
}