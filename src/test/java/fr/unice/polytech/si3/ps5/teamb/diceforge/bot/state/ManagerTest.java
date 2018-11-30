package fr.unice.polytech.si3.ps5.teamb.diceforge.bot.state;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Manager;
import fr.unice.polytech.si3.ps5.teamb.diceforge.bot.strategy.state.Template;
import fr.unice.polytech.si3.ps5.teamb.diceforge.game.Board;
import fr.unice.polytech.si3.ps5.teamb.diceforge.helper.PlayerTest;

/**
 * ManagerTest
 */
public class ManagerTest {

    Manager manager;
    Template template1;
    Template template2;
    int stateChecker = 0;

    @Before
    public void setup() throws Exception {
        manager = new Manager(PlayerTest.getInstance().getBoard());
        initTemplate();
    }

    @Test
    public void createTemplateTest() {
        manager.addState(template1).addState(template2).build();
        // template 1 cond ok
        manager.ExecSequence();
        // template 1 cond ko
        manager.ExecSequence();
        // template 2 cond ok
        manager.ExecSequence();
        // template 2 cond ko
        manager.ExecSequence();
    }

    private void initTemplate() {
        template1 = new Template() {
            @Override
            public void onInitialization(Board boardView) {
                assertEquals(0, stateChecker);
                stateChecker++;
            }

            @Override
            public boolean onCondition(Board boardView) {
                assertTrue(stateChecker < 4);
                stateChecker++;
                return stateChecker < 3;
            }

            @Override
            public void doAction(Board boardView) {
                assertEquals(2, stateChecker);
                stateChecker++;
            }

            @Override
            public void doElse(Board boardView) {
                assertEquals(4, stateChecker);
                stateChecker++;
                manager.nextTemplate();
            }

        };
        template2 = new Template() {
            @Override
            public void onInitialization(Board boardView) {
                assertEquals(5, stateChecker);
                stateChecker++;
            }

            @Override
            public boolean onCondition(Board boardView) {
                assertTrue(stateChecker < 9);
                stateChecker++;
                return stateChecker < 8;
            }

            @Override
            public void doAction(Board boardView) {
                assertEquals(7, stateChecker);
                stateChecker++;
            }

            @Override
            public void doElse(Board boardView) {
                assertEquals(9, stateChecker);
                stateChecker++;
            }
        };
    }
}