import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Jhava {

    private int hitPoints = 52489112;
    private String greeting = "BLARGH";

    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void offerFood() {
        Hero.handOverFood("피자");
    }

    public void extendHandInFriendship() throws Exception {
        throw new Exception();
    }

    public void apologize() {
        try {
            Hero.acceptApology();
        } catch (IOException e) {
            System.out.println("Caught!");
        }
    }

    public static void main(String[] args) {
        System.out.println(Hero.makeProclamation());

        Spellbook spellbook = new Spellbook();
        System.out.println(spellbook.spells);

        System.out.println("Max Spell count: " + Spellbook.MAX_SPELL_COUNT);
        Spellbook.Companion.getSpellbookGreeting();
        Spellbook.getSpellbookGreeting();

        Function1<String, Unit> transactorJ = Hero.getTranslator();
        transactorJ.invoke("TRUCE");
    }

}
