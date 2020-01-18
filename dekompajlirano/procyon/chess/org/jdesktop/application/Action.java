// 
// Decompiled by Procyon v0.5.36
// 

package org.jdesktop.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.Annotation;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Action {
    String name() default "";
    
    String enabledProperty() default "";
    
    String selectedProperty() default "";
    
    Task.BlockingScope block() default Task.BlockingScope.NONE;
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.PARAMETER })
    public @interface Parameter {
        String value() default "";
    }
}
