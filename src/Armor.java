public class Armor {
    private String name;
    private int movementDice;
    private int armor;

    public Armor(String name, int movementDice, int armor) {
        this.name = name;
        this.movementDice = movementDice;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovementDice() {
        return movementDice;
    }

    public void setMovementDice(int movementDice) {
        this.movementDice = movementDice;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}