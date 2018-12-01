package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state;

import org.junit.Before;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateEarlyGameForgeGoldPriority;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateLateGameExploitHigestCard;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.template.TemplateMiddleGameForgeMoonSun;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.HelperPlayer;

/**
 * TemplateTest
 */
public class TemplateTest {

    private Manager m;
    private Context c;

    @Before
    public void setup() {
        m = new Manager(HelperPlayer.first);
        c = m.getContext();
    }

    @Test
    public void templateTest1() {
        m.addState(new TemplateEarlyGameForgeGoldPriority()).addState(new TemplateMiddleGameForgeMoonSun())
                .addState(new TemplateLateGameExploitHigestCard()).build();

        m.ExecSequence();
        assertEquals(new TemplateEarlyGameForgeGoldPriority().toString(), c.getState().toString());

        m.nextTemplate();

        m.ExecSequence();
        assertEquals(new TemplateMiddleGameForgeMoonSun().toString(), c.getState().toString());

        m.nextTemplate();

        m.ExecSequence();
        assertEquals(new TemplateLateGameExploitHigestCard().toString(), c.getState().toString());

        m.nextTemplate();

        m.ExecSequence();
        assertEquals(new TemplateLateGameExploitHigestCard().toString(), c.getState().toString());
    }
}