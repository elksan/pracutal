package etc;

import ninja.params.WithArgumentExtractor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@WithArgumentExtractor(LoggedInRoleExtractor.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface LoggedInRole {}
