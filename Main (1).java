class InvalidDurability extends Exception {
    InvalidDurability() {
        System.out.println("-**-Exception occured : Durability must be greater than 0 and less than or equal to 1-**-");
    }
}

class InvalidWarranty extends Exception {
    InvalidWarranty() {
        System.out.println("-**-Exception occured : Warranty must be greater than 0 -**-");
    }
}

abstract class Equipment {
    String make;
    String model;
    int purchaseYear;

    public Equipment(String make, String model, int purchaseYear) {
        this.make = make;
        this.model = model;
        this.purchaseYear = purchaseYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    abstract int replacementYear();

    public void showDetails() {
        System.out.println("Equipment [make=" + make + ", model=" + model + ", purchaseYear=" + purchaseYear + "]");
    }

}

class BatteryPoweredEquipment extends Equipment {
    private int warranty;

    public BatteryPoweredEquipment(String make, String model, int purchaseYear, int warranty) {
        super(make, model, purchaseYear);
        this.warranty = warranty;

    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) throws InvalidWarranty {
        this.warranty = warranty;
        if (warranty <= 0) {
            throw new InvalidWarranty();
        }
    }

    public int replacementYear() {
        int replacementyear = warranty + super.getPurchaseYear();
        return replacementyear;
    }

}

class FuelPoweredEquipment extends Equipment {
    int usage;
    int MaximumDays;

    public FuelPoweredEquipment(String make, String model, int purchaseYear, int usage, int maximumDays) {
        super(make, model, purchaseYear);
        this.usage = usage;
        MaximumDays = maximumDays;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int replacementYear() {
        int replacementyear = MaximumDays / usage + super.getPurchaseYear();
        return replacementyear;
    }

}

class StandardEquipment extends Equipment {
    double durability;
    int maximumretention = 7;

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) throws InvalidDurability {
        this.durability = durability;
        if (durability == 0 && durability < 1 || durability > 1) {
            // throws exception if durability is less than 0 and greater than or equal to 1
            throw new InvalidDurability();
        }
    }

    public int replacementYear() {
        int replacementyear = (int) (maximumretention * durability + super.getPurchaseYear());
        return replacementyear;
    }

    public StandardEquipment(String make, String model, int purchaseYear, double durability, int maximumretention) {
        super(make, model, purchaseYear);
        this.durability = durability;
        this.maximumretention = maximumretention;
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            StandardEquipment se = new StandardEquipment(null, null, 0, 0, 0);
            se.setMake("JCB");
            se.setModel("AMR01");
            se.setPurchaseYear(2022);
            se.setDurability(1);
            se.showDetails();
            System.out.println("\n Replacement Year: " + se.replacementYear());

        } catch (InvalidDurability e) {
            System.out.println(e.getMessage());
        }

        try {
            FuelPoweredEquipment fpe = new FuelPoweredEquipment(null, null, 0, 0, 0);
            fpe.setMake("Botch Mower");
            fpe.setModel("Rotak 40");
            fpe.setPurchaseYear(2020);
            fpe.setUsage(200);
            fpe.showDetails();
            System.out.println("\n" + "\nReplacement Year : " + fpe.replacementYear());
            fpe.setMake("Havana Chain Saw");
            fpe.setModel("Rotak 22");
            fpe.setPurchaseYear(2019);
            fpe.setUsage(300);
            fpe.showDetails();
            System.out.println("\n" + "\nReplacement Year : " + fpe.replacementYear());

            BatteryPoweredEquipment bpe = new BatteryPoweredEquipment(null, null, 0, 0);
            bpe.setMake("Nikita Edge Trimmer");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2021);
            bpe.setWarranty(3);
            bpe.showDetails();
            System.out.println("\n" + "\nReplacement Year :" + bpe.replacementYear());
            bpe.setMake("Nikita Brush Cutter");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2020);
            bpe.setWarranty(1);
            bpe.showDetails();
            System.out.println("\n" + "\nReplacement Year :" + bpe.replacementYear());
        } catch (InvalidWarranty e) {
            System.out.println(e.getMessage());
        }
    }
}