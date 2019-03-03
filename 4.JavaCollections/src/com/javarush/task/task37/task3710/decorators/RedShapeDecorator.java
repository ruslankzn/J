package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator{
    private Shape shape1;

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
        this.shape1 = decoratedShape;
    }

    private void setBorderColor(Shape shape){
        System.out.println(String.format("Setting border color for %s to red.", shape.getClass().getSimpleName()));
    }

    @Override
    public void draw() {
        setBorderColor(shape1);
        super.draw();
    }
}
