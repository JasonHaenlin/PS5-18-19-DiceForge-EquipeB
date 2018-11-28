package fr.unice.polytech.si3.ps5.teamb.diceforge.game.util.exception;

/**
 * BadConfigFileRuntimeException
 */
public class BadConfigFileException extends Exception {

    private static final long serialVersionUID = -3372643505402513278L;

    public BadConfigFileException(String msg) {
        super("ERROR :" + msg);
    }
}