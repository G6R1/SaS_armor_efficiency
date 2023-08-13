import java.util.ArrayList;
import java.util.List;

public class Calculation {
    public static void main(String[] args) {

        //ВХОДНЫЕ ПАРАМЕТРЫ
        int attackBonus = 3;
        int dex = 1;
        int armorPenetration = 2;

        List<Armor> armorList = new ArrayList<>();
        armorList.add(new Armor("Без брони", 10, 0));
        armorList.add(new Armor("Шкурный доспех", 8, 2));
        armorList.add(new Armor("Сыромятный доспех", 4, 3));
        armorList.add(new Armor("Набивняк", 6, 3));
        armorList.add(new Armor("Стеганка", 8, 3));
        armorList.add(new Armor("Ламеллярный ", 6, 4));
        armorList.add(new Armor("Бригантина", 6, 5));
        armorList.add(new Armor("Кольчужный", 6, 6));
        armorList.add(new Armor("Ландскнехтский доспех", 4, 7));
        armorList.add(new Armor("Кираса", 8, 7));
        armorList.add(new Armor("Латы", 4, 10));


        for (Armor armor : armorList) {
            double dodgeChance = calculateDodgeChance(attackBonus, armor.getMovementDice(), dex);
            double dodgeChanceWithShield = calculateDodgeChanceWithShield(attackBonus, armor.getMovementDice(), dex);
            double blockChance = calculateBlockChance(armor.getArmor(), armorPenetration);
            double blockChanceWithShield = calculateBlockChanceWithShield(armor.getArmor(), armorPenetration);

            System.out.println(armor.getName());
            System.out.printf("\tШанс уворота: %.2f%%\n", dodgeChance * 100);
            System.out.printf("\tШанс уворота (со щитом тир3): %.2f%%\n", dodgeChanceWithShield * 100);
            System.out.printf("\tШанс блока: %.2f%%\n", blockChance * 100);
            System.out.printf("\tШанс блока со щитом: %.2f%%\n", blockChanceWithShield * 100);
            System.out.printf("\tИтоговая эффективность защиты: %.2f%%\n",
                    (1 - (1 - dodgeChance) * (1 - blockChance)) * 100);
            System.out.printf("\tИтоговая эффективность защиты (со щитом): %.2f%%\n",
                    (1 - (1 - dodgeChance) * (1 - blockChanceWithShield)) * 100);
            System.out.printf("\tИтоговая эффективность защиты (со щитом тир3): %.2f%%\n\n",
                    (1 - (1 - dodgeChanceWithShield) * (1 - blockChanceWithShield)) * 100);
        }
    }

    public static double calculateDodgeChance(int attackBonus, int movementDice, int dex) {
        double dodgeChance = 0;   // успех атаки если она больше уклонения //ОТМЕНА РАВЕРСТО ЭТО УСПЕХ АТАКИ

        if (movementDice + dex >= attackBonus + 1) {
            for (int movementDiceValue = 1; movementDiceValue <= movementDice; movementDiceValue++) {
                int dodgeSuccessCount = 0;
                for (int attValue = 1; attValue < 11; attValue++) {
                    if (attValue + attackBonus < movementDiceValue + dex)
                        dodgeSuccessCount++;
                }

                dodgeChance += (1.0 / movementDice) * (dodgeSuccessCount / 10.0);
            }
        }
        return dodgeChance;
    }

    public static double calculateDodgeChanceWithShield(int attackBonus, int movementDice, int dex) {
        double dodgeChanceWithShield = 0;

        if (movementDice + dex + 2 >= attackBonus + 1) {
            for (int movementDiceValue = 1; movementDiceValue <= movementDice; movementDiceValue++) {
                int dodgeWithShieldSuccessCount = 0;
                for (int attValue = 1; attValue < 11; attValue++) {
                    if (attValue + attackBonus < movementDiceValue + dex + 2)
                        dodgeWithShieldSuccessCount++;
                }

                dodgeChanceWithShield += (1.0 / movementDice) * (dodgeWithShieldSuccessCount / 10.0);
            }
        }
        return dodgeChanceWithShield;
    }

    public static double calculateBlockChance(int armor, int armorPenetration) {
        double blockFailedCount = 0;

        for (int penetrationValue = 1; penetrationValue < 11; penetrationValue++) {
            if (penetrationValue + armorPenetration - armor > 0)
                blockFailedCount++;
        }

        return 1 - blockFailedCount / 10;
    }

    public static double calculateBlockChanceWithShield(int armor, int armorPenetration) {
        double blockFailedCount = 0;

        for (int penetrationValue = 1; penetrationValue < 11; penetrationValue++) {
            if (penetrationValue + armorPenetration - armor - 2 > 0)
                blockFailedCount++;
        }

        return 1 - blockFailedCount / 10;
    }

    public static class Armor {
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
}

