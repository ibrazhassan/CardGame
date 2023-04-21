/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cardjitsu;


/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author Syed Ghazi, April, 2023
 */
public class Card {
    
    enum Element
    {
        Fire, Water, Snow;
        
        private static final Element[] elements = Element.values();
        public static Element getElement(int i)
        {
            return Element.elements[i];
        }
    }
    
    enum Value
    {
        One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
        
        private static final Value[] values = Value.values();
        public static Value getValue(int i)
        {
            return Value.values[i];
        }
                
    }
    
    private final Element element;
    private final Value value;
    
    public Card(final Element element, final Value value)
    {
        this.element = element;
        this.value = value;
    }
    
    public Element getElement()
    {
        return this.element;
    }
    
    public Value getValue()
    {
        return this.value;
    }
    
    public String toString()
    {
        return element + "_" + value;
    }
}
