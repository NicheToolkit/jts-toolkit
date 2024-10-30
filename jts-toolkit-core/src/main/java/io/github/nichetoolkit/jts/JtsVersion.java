package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

/**
 * <code>JtsVersion</code>
 * <p>The jts version class.</p>
 * @see  com.fasterxml.jackson.core.Versioned
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class JtsVersion implements Versioned {
    /**
     * <code>VERSION</code>
     * {@link com.fasterxml.jackson.core.Version} <p>The constant <code>VERSION</code> field.</p>
     * @see  com.fasterxml.jackson.core.Version
     */
    public static final Version VERSION = VersionUtil.parseVersion("1.1.0", "io.github.nichetoolkit.jts", "jts-toolkit");

    /**
     * <code>JtsVersion</code>
     * <p>Instantiates a new jts version.</p>
     */
    public JtsVersion() {
    }

    @Override
    public Version version() {
        return VERSION;
    }
}
