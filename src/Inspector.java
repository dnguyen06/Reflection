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
    		for (int i = 0; i < interfaces.length; i++) {
    			System.out.println(tabs + " INTERFACE -> Recursively Inspect");
    			System.out.println(tabs + " " + interfaces[i].getName());
    			inspectClass(interfaces[i], obj, recursive, ++depth);
    			depth--;
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