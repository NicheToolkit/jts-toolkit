package io.github.nichetoolkit.jts.error;

/**
 * <code>JtsBoxInvalidException</code>
 * <p>The jts box invalid exception class.</p>
 * @see  io.github.nichetoolkit.jts.error.JtsParseException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsBoxInvalidException extends JtsParseException {

    /**
     * <code>JtsBoxInvalidException</code>
     * <p>Instantiates a new jts box invalid exception.</p>
     */
    public JtsBoxInvalidException() {
        super(JtsErrorStatus.JTS_BOX_INVALID_ERROR);
    }

    /**
     * <code>JtsBoxInvalidException</code>
     * <p>Instantiates a new jts box invalid exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public JtsBoxInvalidException(String message) {
        super(JtsErrorStatus.JTS_BOX_INVALID_ERROR, message);
    }

    @Override
    public JtsBoxInvalidException get() {
        return new JtsBoxInvalidException();
    }

    @Override
    public String name() {
        return "box invalid exception";
    }
}
