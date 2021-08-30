package model;

import java.util.ArrayList;

/**
 * Created by gyseynov on 19.09.14.
 */
public class Coin {
    public static ArrayList<Coin> coins = new ArrayList<Coin>();

    Coin() {
        coins.add(this);
    }
}
