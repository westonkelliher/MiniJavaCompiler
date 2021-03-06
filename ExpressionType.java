package hw2;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class ExpressionType {

    //functional data
    private String type;

    //static
    private static ArrayList<String> known_types;
    private static Map<String, ExpressionType> known_extends;
    ///private static Map<String, ExpressionType> known_identifiers;

    //static initializer
    static {
	known_types = new ArrayList<String>();
	known_extends = new HashMap<String, ExpressionType>();
	known_types.add("boolean");
	known_types.add("int[]");
	known_types.add("int");
    }

    //constructor
    public ExpressionType(String s) throws TypeCheckException {
	//if (! known_types.contains(s)) {
	//    throw new TypeChcekException("Type '"+s+"'not recognized");
	//}
	type = s;
    }
    public ExpressionType() {
	type = "";
    }
    
    
    //querry functions
    public String getType() {
	return type;
    }
    public boolean isType(String s) {
	return type.equals(s);
    }
    public boolean isOfType(String s) {
	if (type.equals(s)) {
	    return true;
	}
	else if (known_extends.containsKey(type)) {
	    return known_extends.get(type).isType(s);
	}
	return false;
    }
    public boolean sameAs(ExpressionType t) {
	return t.isType(type);
    }

    //set functions
    public void newKnownType(String s) {
	known_types.add(s);
    }
    public void newKnownType(String s, ExpressionType e) {
	known_types.add(s);
	known_extends.put(s, e);
    }

}
