package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

/**
 * <p>JtsVersion</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class JtsVersion implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion("1.0.0", "io.github.nichetoolkit.jts", "jts-toolkit");

    public JtsVersion() {
    }

    public Version version() {
        return VERSION;
    }
}
