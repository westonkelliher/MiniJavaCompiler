package hw2;

import hw2.ExpressionType;
import java.util.Map;

public class Scope {

    //member variables
    private  Map<String, ExpressionType> known_identifiers;
    private Scope super_scope;
    private String this_class;
    
    //constructor
    public Scope() {
	super_scope = null;
    }
    public Scope(Scope s) {
	super_scope = s;
    }

    //functions
    public boolean declaredInThisScope(String id) {
	return known_identifiers.containsKey(id);
    }
    public ExpressionType getType(String id) throws TypeCheckException{
	if (! declaredInThisScope(id)) {
	    if (super_scope == null) {
		throw new TypeCheckException("Identifier '"+id+"' does not exist in this scope");
	    }
	    return super_scope.getType(id);
	}
	return known_identifiers.get(id);
    }
}
