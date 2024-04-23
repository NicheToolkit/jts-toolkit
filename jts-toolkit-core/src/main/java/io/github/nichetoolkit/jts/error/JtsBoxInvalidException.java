package io.github.nichetoolkit.jts.error;

/**
 * <p>InvalidBoxRangeException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JtsBoxInvalidException extends JtsParseException {

    public JtsBoxInvalidException() {
        super(JtsErrorStatus.JTS_BOX_INVALID_ERROR);
    }

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
