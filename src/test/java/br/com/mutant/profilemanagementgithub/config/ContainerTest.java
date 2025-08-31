package br.com.mutant.profilemanagementgithub.config;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Import({})
@Testcontainers
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerTest {

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] imports() default {};
}
