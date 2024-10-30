## [Jts-Toolkit](https://github.com/NicheToolkit/jts-toolkit)

[![GitHub License](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/NicheToolkit/jts-toolkit/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.nichetoolkit/jts-toolkit-starter)](https://central.sonatype.com/search?smo=true&q=jts-toolkit-starter&namespace=io.github.nichetoolkit)
[![Nexus Release](https://img.shields.io/nexus/r/io.github.nichetoolkit/jts-toolkit-starter?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/releases/io/github/nichetoolkit/jts-toolkit-starter/)
[![Nexus Snapshot](https://img.shields.io/nexus/s/io.github.nichetoolkit/jts-toolkit-starter?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/nichetoolkit/jts-toolkit-starter/)
![Tests](https://github.com/NicheToolkit/jts-toolkit/workflows/Tests/badge.svg)

## Toolkit Usages

- [Mybatis-Toolkit](https://github.com/NicheToolkit/mybatis-toolkit)

- [File-Toolkit](https://github.com/NicheToolkit/file-toolkit)

## Maven Central Repository

- [Maven Central Repository](https://search.maven.org/search?q=io.github.nichetoolkit)

- [Sonatype Central Repository](https://central.sonatype.dev/search?q=io.github.nichetoolkit)

## Dependent & Environment

> [Spring Boot](https://spring.io/projects/spring-boot) 2.7.18.RELEASE\
> [Maven](https://maven.apache.org/) 3.6.3+\
> [JDK](https://www.oracle.com/java/technologies/downloads/#java8) 1.8

## Wiki Reference

[Wiki Reference](https://github.com/NicheToolkit/jts-toolkit/wiki): https://github.com/NicheToolkit/jts-toolkit/wiki

## Instructions

### Maven Usages

#### jts-toolkit-core

* Maven (`pom.xml`)

```xml

<dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>jts-toolkit-core</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### jts-toolkit-utils

* Maven (`pom.xml`)

```xml

<dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>jts-toolkit-utils</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Configure Properties

#### jackson configuration

* prefix

>
> nichetoolkit.jts.jackson
>

* values

|   value   |   type    | defaultValue |                  description                   |
|:---------:|:---------:|:------------:|:----------------------------------------------:|
| `enabled` | `Boolean` |   `false`    | the switch of auto configure with jts jackson. |

* properties

```properties
nichetoolkit.jts.jackson.enabled=true
```

#### shape configuration

* prefix

>
> nichetoolkit.jts.shape
>

* values

|       value        |   type    | defaultValue |                 description                 |
|:------------------:|:---------:|:------------:|:-------------------------------------------:|
|     `enabled`      | `Boolean` |   `false`    | the switch of auto configure with jts shap. |
| `space.root-path`  | `String`  |              |   the root file path of jts shape space.    |
| `space.cache-path` | `String`  |              |   the cache file path of jts shape space.   |
| `space.shape-path` | `String`  |              |   the shape file path of jts shape space.   |
|  `space.zip-path`  | `String`  |              |    the zip file path of jts shape space.    |

* properties

```properties
nichetoolkit.jts.shape.enabled=true
nichetoolkit.jts.shape.space.root-path=G:\\data\\server
nichetoolkit.jts.shape.space.cache-path=\\cache
nichetoolkit.jts.shape.space.shape-path=\\shape
nichetoolkit.jts.shape.space.zip-path=\\zip
```

## Test Example

[jts-toolkit-example](https://github.com/NicheToolkit/jts-toolkit/tree/master/jts-toolkit-example)

## License

[Apache License](https://www.apache.org/licenses/LICENSE-2.0)

## Dependencies

[rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)

[geotools](https://github.com/geotools/geotools)

[jackson-datatype-jts](https://github.com/bedatadriven/jackson-datatype-jts)

