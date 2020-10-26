# CPSC 501 - A2

## First Refactor
***Code to be altered***

     ...
     } else if (!recursive) {
		 if(fields[i].get(obj) == null) {
			 System.out.println(tabs + "  " + "Value (ref): null");
	 } 	
	 ...	
	 } else if (recursive) {
		 if(fields[i].get(obj) == null) {
			 System.out.println(tabs + "  " + "Value (ref): null");
		}
	 ...

I had a duplicated check to see if the object was null. I actually already checked for the null object in the else-if before as shown below.

	 ...
	 } else if (fields[i].get(obj) == null) {
		 System.out.println(tabs + "  " + "Value (ref): null");
	   }


The refactor was to completely remove all the code shown in the first snippet as there was no need to check for the null object.

## Second Refactor
***Code to be altered***

     if (fields[i].getType().isPrimitive()) {
		 System.out.println(tabs + "  " + "Value: " + fields[i].get(obj));
	 } else if (fields[i].get(obj) == null) {
		 System.out.println(tabs + "  " + "Value (ref): null");
	 } else if (fields[i].get(obj).getClass().isArray()) {
		 System.out.println(tabs + "  " + "Component Type: "+ 
		 fields[i].get(obj).getClass().getComponentType());
	 ...
		 for (int j = 0; j < Array.getLength(fields[i].get(obj)); j++) {
			 System.out.println(tabs + "   " + "Value: " + Array.get(fields[i].get(obj), j));
	 }
	 } else if (!recursive) {
		 System.out.println(tabs + "  " + "Value (ref): " + fields[i].get(obj).getClass().getName()
	 + "@" + fields[i].get(obj).hashCode());
	 } else {
		 System.out.println(tabs + "  " + "Value (ref): " + fields[i].get(obj).getClass().getName()
		 + "@" + fields[i].get(obj).hashCode());
		 System.out.println(tabs + "   -> Recursively inspect");
		 inspectClass(fields[i].get(obj).getClass(), fields[i].get(obj), recursive, ++depth);
		 depth--;
	 }

I often had to get the object from the field and instead of creating a variable for it, I had to get it each time by using 

	    fields[i].get(obj)

The refactor was to create a variable one time and then use it. I stored it in a variable called object.

***Code changed to***

    Object object = fields[i].get(obj);
	if (fields[i].getType().isPrimitive()) {
		 System.out.println(tabs + "  " + "Value: " + object);
	} else if (object == null) {
		 System.out.println(tabs + "  " + "Value (ref): null");
	} else if (object.getClass().isArray()) {
		 System.out.println(tabs + "  " + "Component Type: "
		 + object.getClass().getComponentType());
		 System.out.println(tabs + "  " + "Length: " + Array.getLength(object));
		 System.out.println(tabs + "  " + "Entries->");
	 for (int j = 0; j < Array.getLength(object); j++) {
		 System.out.println(tabs + "   " + "Value: " + Array.get(object, j));
	 }
	 } else if (!recursive) {
		 System.out.println(tabs + "  " + "Value (ref): " + object.getClass().getName()
		 + "@" + object.hashCode());
	 } else {
		 System.out.println(tabs + "  " + "Value (ref): " + object.getClass().getName()
		 + "@" + object.hashCode());
		 System.out.println(tabs + "   -> Recursively inspect");
		 inspectClass(object.getClass(), object, recursive, ++depth);
		 depth--;
	}

