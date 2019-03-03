package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)//1) Должна быть доступна во время выполнения программы.
@Target(ElementType.TYPE)//2) Применяться может только к новым типам данных (Class, interface (including annotation type), or enum declaration).
public @interface Ticket {

    public enum Priority {LOW, MEDIUM, HIGH}//3) Должна содержать enum Priority c элементами LOW, MEDIUM, HIGH.

    Priority priority() default Priority.MEDIUM;//4) Приоритет будет задавать свойство priority — по умолчанию Priority.MEDIUM.

    String[] tags() default {};//5) Свойство tags будет массивом строк и будет хранить теги связанные с тикетом

    String createdBy() default "Amigo";//6) Свойство createdBy будет строкой — по умолчанию Amigo.

}
