<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.woehlke.computer.kurzweil</groupId>
        <artifactId>insourcing2</artifactId>
        <version>2.2.2</version>
        <relativePath>..</relativePath>
    </parent>

    <artifactId>insourcing2-cyclic-cellular-automaton</artifactId>
    <packaging>jar</packaging>

    <name>insourcing2 :: Cyclic Cellular Automaton</name>
    <description> asdf </description>

    <dependencies>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-scm-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.scm</groupId>
            <artifactId>maven-scm-provider-gitexe</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.scm</groupId>
            <artifactId>maven-scm-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-jcl</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-yaml</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-properties</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-ion</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>org.yaml</groupId>
            <artifactId>snakeyaml</artifactId>
        </dependency>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>

        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-site-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-project-info-reports-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jxr-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-checkstyle-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-changes-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-invoker-plugin</artifactId>
            <type>maven-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.skins</groupId>
            <artifactId>maven-default-skin</artifactId>
        </dependency>
    </dependencies>

    <build>
        <finalName>${project.artifactId}</finalName>
        <defaultGoal>clean dependency:list install exec:java</defaultGoal>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-enforcer-plugin</artifactId>
                <executions>
                    <execution>
                        <id>enforce-versions</id>
                        <goals>
                            <goal>enforce</goal>
                        </goals>
                        <configuration>
                            <rules>
                                <bannedPlugins>
                                    <level>ERROR</level>
                                    <excludes>
                                        <exclude>org.apache.maven.plugins:maven-verifier-plugin</exclude>
                                    </excludes>
                                    <message>Please consider using the maven-invoker-plugin (http://maven.apache.org/plugins/maven-invoker-plugin/)!</message>
                                </bannedPlugins>
                                <requireMavenVersion>
                                    <version>${version.maven}</version>
                                </requireMavenVersion>
                                <requireJavaVersion>
                                    <version>${java.version}</version>
                                </requireJavaVersion>
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-invoker-plugin</artifactId>
                <executions>
                    <execution>
                        <id>integration-test</id>
                        <goals>
                            <goal>install</goal>
                            <goal>run</goal>
                        </goals>
                        <configuration>
                            <settingsFile>src/it/settings.xml</settingsFile>
                            <cloneProjectsTo>${project.build.directory}/it</cloneProjectsTo>
                            <localRepositoryPath>${project.build.directory}/it-repo</localRepositoryPath>
                            <postBuildHookScript>verify</postBuildHookScript> <!-- no extension required -->
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>unpack-dependencies-classpath</id>
                        <phase>site</phase>
                        <goals>
                            <goal>analyze-only</goal>
                            <goal>analyze-dep-mgt</goal>
                            <goal>analyze-report</goal>
                            <goal>sources</goal>
                            <goal>resolve</goal>
                            <goal>resolve-plugins</goal>
                            <goal>collect</goal>
                            <goal>copy-dependencies</goal>
                            <!--
                            <goal>unpack-dependencies</goal>
                            -->
                            <goal>build-classpath</goal>
                        </goals>
                        <configuration>
                            <overWriteReleases>true</overWriteReleases>
                            <overWriteSnapshots>true</overWriteSnapshots>
                            <outputFile>${project.build.outputDirectory}/classpath.txt</outputFile>
                            <failOnWarning>false</failOnWarning>
                            <failOnMissingClassifierArtifact>false</failOnMissingClassifierArtifact>
                            <outputDirectory>${project.build.directory}/dependency-classes/</outputDirectory>
                            <ignoredDependencies>
                                <ignoredDependency>org.apache.maven.plugins:maven-surefire-plugin</ignoredDependency>
                                <ignoredDependency>org.apache.maven.scm:maven-scm</ignoredDependency>
                                <ignoredDependency>org.apache.maven.scm:maven-scm-providers-git</ignoredDependency>
                                <ignoredDependency>org.apache.maven.scm:maven-scm-providers-standard</ignoredDependency>
                                <ignoredDependency>org.apache.maven.scm:maven-scm-providers</ignoredDependency>
                                <ignoredDependency>org.apache.maven.scm:maven-scm-managers</ignoredDependency>
                            </ignoredDependencies>
                            <ignoredUsedUndeclaredDependencies>
                                <ignoredUsedUndeclaredDependency>org.apache.maven.plugins:maven-surefire-plugin</ignoredUsedUndeclaredDependency>
                            </ignoredUsedUndeclaredDependencies>
                            <ignoredUnusedDeclaredDependencies>
                                <ignoredUnusedDeclaredDependency>org.apache.maven.plugins:maven-surefire-plugin</ignoredUnusedDeclaredDependency>
                            </ignoredUnusedDeclaredDependencies>
                        </configuration>
                    </execution>
                    <execution>
                        <id>check-dependencies-1</id>
                        <phase>site</phase>
                        <goals>
                            <goal>list</goal>
                            <goal>list-repositories</goal>
                            <goal>properties</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>check-dependencies-2</id>
                        <phase>site</phase>
                        <goals>
                            <goal>analyze</goal>
                            <goal>analyze-dep-mgt</goal>
                            <goal>analyze-duplicate</goal>
                            <goal>analyze-report</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>check-dependencies-3</id>
                        <phase>site</phase>
                        <goals>
                            <goal>tree</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>site</phase>
                        <configuration>
                            <excludeGroupIds>org.webjars,org.jclouds,org.jboss.as,xml-resolver,commons-configuration,org.codehaus.plexus,commons-digester,junit,log4j,io.openliberty.features</excludeGroupIds>
                            <excludeArtifactIds>weld-probe-core,commons-configuration,jdk.tools,maven-antrun-plugin</excludeArtifactIds>
                            <failOnMissingClassifierArtifact>false</failOnMissingClassifierArtifact>
                            <failOnWarning>false</failOnWarning>
                            <reportSets>
                                <reportSet>
                                    <reports>
                                        <report>analyze-report</report>
                                    </reports>
                                </reportSet>
                            </reportSets>
                        </configuration>
                    </execution>
                    <execution>
                        <id>get-dependency-sources</id>
                        <phase>site</phase>
                        <goals>
                            <goal>sources</goal>
                            <goal>resolve</goal>
                            <goal>resolve-plugins</goal>
                            <goal>collect</goal>
                            <goal>copy-dependencies</goal>
                            <goal>unpack-dependencies</goal>
                        </goals>
                        <configuration>
                            <classifier>sources</classifier>
                            <overWriteReleases>true</overWriteReleases>
                            <overWriteSnapshots>true</overWriteSnapshots>
                            <excludeGroupIds>org.webjars,antlr,org.beanshell,org.apache.velocity</excludeGroupIds>
                            <excludeArtifactIds>weld-probe-core,antlr,bsh,velocity-tools</excludeArtifactIds>
                            <failOnMissingClassifierArtifact>false</failOnMissingClassifierArtifact>
                            <outputDirectory>${project.build.directory}/dependency-sources/</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <configuration>
                    <maxmemory>2048m</maxmemory>
                    <source>${java.version}</source>
                    <encoding>${project.build.sourceEncoding}</encoding>
                </configuration>
                <executions>
                    <execution>
                        <id>javadoc-javadoc-package</id>
                        <phase>site</phase>
                        <goals>
                            <goal>javadoc</goal>
                        </goals>
                        <configuration>
                            <linksource>true</linksource>
                            <maxmemory>2048m</maxmemory>
                            <source>${java.version}</source>
                            <encoding>${project.build.sourceEncoding}</encoding>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>org.woehlke.computer.kurzweil.cyclic.cellular.automaton.CyclicCellularAutomatonApplication</mainClass>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>org.woehlke.computer.kurzweil.cyclic.cellular.automaton.CyclicCellularAutomatonApplication</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-scm-plugin</artifactId>
                <configuration>
                    <connectionType>developerConnection</connectionType>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-release-plugin</artifactId>
                <configuration>
                    <preparationGoals>clean</preparationGoals>
                    <autoVersionSubmodules>true</autoVersionSubmodules>
                    <goals>install</goals>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>delombok</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jxr-plugin</artifactId>
                <configuration>
                    <linkJavadoc>true</linkJavadoc>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-clean-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-install-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-help-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-site-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skipTests>${skipTests}</skipTests>
                    <systemPropertyVariables>
                        <spring.profiles.active>default</spring.profiles.active>
                        <skipIntegrationTests>${skipTests}</skipIntegrationTests>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <configuration>
                    <skipTests>${skipTests}</skipTests>
                    <systemPropertyVariables>
                        <spring.profiles.active>default</spring.profiles.active>
                        <skipIntegrationTests>${skipTests}</skipIntegrationTests>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
