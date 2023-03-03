## [Jts-Toolkit](https://github.com/NicheToolkit/jts-toolkit) jts开发工具组

[![GitHub license](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/NicheToolkit/jts-toolkit/blob/master/LICENSE)[![Maven Release](https://img.shields.io/maven-central/v/io.github.nichetoolkit/jts-toolkit-utils.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.github.nichetoolkit%22%20AND%20a%3A%jts-toolkit-utils%22)
[![Maven Snapshot](https://img.shields.io/maven-central/v/io.github.nichetoolkit/jts-toolkit-utils.svg)](https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/nichetoolkit/jts-toolkit-utils/)
![Tests](https://github.com/NicheToolkit/jts-toolkit/workflows/Tests/badge.svg)

&emsp;&emsp; 依赖[rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)组件下的Gis相关开发工具整理.

&emsp;&emsp; 针对[jackson-datatype-jts](https://github.com/bedatadriven/jackson-datatype-jts)的基础上进行对[geotools](https://github.com/geotools/geotools)工具升级，
并包含`shape`数据的`zip`压缩文件的上传，下载示例。

## Release介绍

-  [Jts-Toolkit@1.0.3](https://github.com/NicheToolkit/jts-toolkit/tree/master/release/1.0.3.md)

### v1.0.3 Release


# [jts-toolkit-utils](https://github.com/NicheToolkit/jts-toolkit/tree/master/jts-toolkit-utils)

1、升级`gt-geojson`至`27.0`版本。

2、升级`rest-toolkit-utils`至`1.0.5`版本。

## Maven Central

-  [Maven Central Repository](https://search.maven.org/search?q=io.github.nichetoolkit)

-  [Sonatype Central Repository](https://central.sonatype.dev/search?q=io.github.nichetoolkit)

## 依赖环境
 > [Spring Boot](https://spring.io/projects/spring-boot) 2.6.6.RELEASE\
 > [Maven](https://maven.apache.org/) 3.6.0+\
 > [JDK](https://www.oracle.com/java/technologies/downloads/#java8) 1.8

## jts-toolkit-utils
 * Maven (`pom.xml`)
```xml
  <dependency>
    <groupId>io.github.nichetoolkit</groupId>
    <artifactId>jts-toolkit-utils</artifactId>
    <version>1.0.3</version>
  </dependency>
```

## 使用方式

参考[jts-toolkit-test-web](https://github.com/NicheToolkit/jts-toolkit/tree/master/jts-toolkit-test-web)模块.

## License 

 [Apache License](https://www.apache.org/licenses/LICENSE-2.0)

 # 依赖参考

 [rest-toolkit](https://github.com/NicheToolkit/rest-toolkit)
 
 [geotools](https://github.com/geotools/geotools)
 
 [jackson-datatype-jts](https://github.com/bedatadriven/jackson-datatype-jts)
 
