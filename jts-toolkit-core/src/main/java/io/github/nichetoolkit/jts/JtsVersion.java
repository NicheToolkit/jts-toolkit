package io.github.nichetoolkit.jts;

import tools.jackson.core.Version;
import tools.jackson.core.Versioned;
import tools.jackson.core.util.VersionUtil;

/**
 * <code>JtsVersion</code>
 * <p>The jts version class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.core.Versioned
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("unused")
public class JtsVersion implements Versioned {
    /**
     * <code>VERSION</code>
     * {@link tools.jackson.core.Version} <p>The constant <code>VERSION</code> field.</p>
     * @see tools.jackson.core.Version
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
