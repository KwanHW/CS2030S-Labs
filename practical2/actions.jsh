// May change all to List as parameter
Function<Thing,Thing> takeSword = t -> {
    if (t instanceof Sword) {
        Sword sword = (Sword) t;
        if (!sword.isEquipped()) {
            System.out.println("You have taken the sword.");
            return new Sword(sword.getState(),!sword.isEquipped());
        } else {
            System.out.println("You already have the sword.");
            return sword;
        }
    } else {
        return t;
    }
};

Function<Thing,Thing> killTroll = t -> {
    if (t instanceof Troll) {
        Troll troll = (Troll) t;
        // Check if sword is equipped
        // If have kill the troll
        return troll;
    } else {
        return t;
    }
};
