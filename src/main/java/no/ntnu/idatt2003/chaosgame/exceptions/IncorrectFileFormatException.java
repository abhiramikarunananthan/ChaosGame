package no.ntnu.idatt2003.chaosgame.exceptions;

/**
 * {@code IncorrectFileFormatException} is the exception that can
 * be thrown when the file format is not eligible to be read
 *
 * <p>{@code IncorrectFileFormatException} is mainly used for
 * {@link no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler} for
 * when a file being handled is in a format that is not able
 * to be interpreted.
 *
 * @author 10052
 * @version 1.0
 */
public class IncorrectFileFormatException extends RuntimeException {

    /**
     * Constructs a new IncorrectFileFormatException with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public IncorrectFileFormatException() {
        super();
    }

    /**
     * Constructs a new IncorrectFileFormatException with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IncorrectFileFormatException(String message) {
        super(message);
    }

    /**
     * Constructs a new IncorrectFileFormatException with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public IncorrectFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new IncorrectFileFormatException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for IncorrectFileFormatException
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public IncorrectFileFormatException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new IncorrectFileFormatException with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     */
    protected IncorrectFileFormatException(String message, Throwable cause,
                                           boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
