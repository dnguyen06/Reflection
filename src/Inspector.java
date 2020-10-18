import java.lang.reflect.*;
import java.lang.Class;
import java.lang.Object;

/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson
 */
public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	String className = c.getName();
    	String tabs = getTabs(depth);
    	System.out.println(tabs + "CLASS");
    	System.out.println(tabs + "Class: " + className);
    	// DEBUG: System.out.println(tabs + "Superclass: " + c.getSuperclass() + " Current Class: " + c.getName());
    	if (c.getSuperclass() == null) {
    		System.out.println(tabs + "Superclass: NONE");
    	} else {
        	System.out.println(tabs + "SUPERCLASS -> Recursively Inspect");
        	System.out.println(tabs + "Superclass : " + c.getSuperclass().getName());
        	inspectClass(c.getSuperclass(), obj, recursive, ++depth);
        	depth--;
    	}
    	System.out.println(tabs + "INTERFACES( " + className + " )");
    	Class[] interfaces = c.getInterfaces();
    	if(interfaces.length == 0) {
    		System.out.println(tabs + "Interfaces-> NONE");
    	} else {
    		System.out.println(tabs + "Interfaces->");
    		for (int i = 0; i < interfaces.length; i++) {
    			System.out.println(tabs + " INTERFACE -> Recursively Inspect");
    			System.out.println(tabs + " " + interfaces[i].getName());
    			inspectClass(interfaces[i], obj, recursive, ++depth);
    			depth--;
    		}
    	}
    	
    	System.out.println(tabs + "CONSTRUCTORS( " + className + " )");
    	Constructor[] constructors = c.getDeclaredConstructors();
    	if(constructors.length == 0) {
    		System.out.println(tabs + "Constructor-> NONE");
    	} else {
    		System.out.println(tabs + "Constructors->");
    		for (int i = 0; i < constructors.length; i++) {
    			System.out.println(tabs + " CONSTRUCTOR");
    			System.out.println(tabs + "  " + "Name: " + constructors[i].getName());
    			Class [] parameters = constructors[i].getParameterTypes();
    			System.out.println(tabs + "  " + "Parameter types:");
    			if (parameters.length != 0) {
    				for (int j = 0; j < parameters.length; j++) {
    					System.out.println(tabs + "  " + parameters[j]);
    				}
    			}
    			int modifier = constructors[i].getModifiers();
    			if (modifier == 0) {
    				System.out.println(tabs + "  " + "Modifiers: NONE");
    			} else {
    				System.out.println(tabs +  "  " + "Modifiers: " + Modifier.toString(modifier));
    			}
    		}
    		
    	
    	}
    	
    	System.out.println(tabs + "METHODS( " + className + " )");
    	Method[] methods = c.getDeclaredMethods();
    	if(methods.length == 0) {
    		System.out.println(tabs + "Methods-> NONE");
    	} else {
    		System.out.println(tabs + "Methods->");
    		for (int i = 0; i < methods.length; i++) {
    			System.out.println(tabs + " METHOD");
    			System.out.println(tabs + "  " + "Name: " + methods[i].getName());
    			Class[] exceptions = methods[i].getExceptionTypes();
    			if(exceptions.length == 0) {
    				System.out.println(tabs + "  " + "Exceptions-> NONE");
    			} else {
    				System.out.println(tabs + "  " + "Exceptions->");
    				for(int j = 0; j < exceptions.length; j++) {
    					System.out.println(tabs + "  " + exceptions[j]);
    				}
    				
    			}
    			Class[] parameters = methods[i].getParameterTypes();
    			if(parameters.length == 0) {
    				System.out.println(tabs + "  " + "Parameter types-> NONE");
    			} else {
    				System.out.println(tabs + "  " + "Parameter types->");
    				for(int j = 0; j < parameters.length; j++) {
    					System.out.println(tabs + "  " + parameters[j]);
    				}
    			}
    			System.out.println(tabs + "  " + "Return type: " + methods[i].getReturnType());
    			int modifier = methods[i].getModifiers();
    			System.out.println(tabs +  "  " + "Modifiers: " + Modifier.toString(modifier));
    		}
    	}
    	
    	
    }
    
    private String getTabs(int depth) {
    	String tabs = "";
    	for(int i = 0; i < depth; i++) {
    		tabs += "\t";
    	}
    	return tabs;
    }

}