package com.apress.prospring3.ch6.annotationpc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AdviceRequired {
}
