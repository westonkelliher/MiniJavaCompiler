package hw2;

import java.util.Map;
import java.util.HashMap;
import hw2.ClassVisitor;
import hw2.ExpressionVisitor;
import syntaxtree.*;
import visitor.DepthFirstVisitor;


class MasterVisitor extends DepthFirstVisitor {

    //helper visitors
    private ClassVisitor class_visitor;

    //member variable
    private MasterRecord master_record;
    
    //constructor
    public MasterVisitor(Goal n) {
	master_record = new MasterRecord();
	class_visitor = new ClassVisitor();
    }
    
    //public methods
    public MasterRecord generateMasterRecord(Goal n) {
	n.accept(this); //populates class data
	return master_record;
    }


    //visit methods
    @Override
    public void visit(ClassDeclaration n) {
	ClassRecord cr = class_visitor.visit(n);
	master_record.addClassRecord(cr);
    }
    
    @Override
    public void visit(ClassExtendsDeclaration n) {
	ClassRecord cr = class_visitor.visit(n);
	master_record.addClassRecord(cr);
    }

    @Override
    public void visit(MainClass n) {
	ClassRecord cr = class_visitor.visit(n);
	master_record.setMainClass(cr);
    }
    
}


class MasterRecord {

    //member data
    public ClassRecord main_class;
    public Map<String, ClassRecord> classes;

    ///constructors
    public MasterRecord() {
	classes = new HashMap<String, ClassRecord>();
    }
    
    //public methods
    public void setMainClass(ClassRecord cr) {
	main_class = cr;
    }
    public void addClassRecord(ClassRecord cr) {
	classes.put(cr.id, cr);
    }

    public ClassRecord getClassRecord(String id) {
	return classes.get(id);
    }

    public boolean typeExists(String id) {
	if (id.equals("boolean") || id.equals("int") || id.equals("int[]")) {
	    return true;
	}
	return classes.containsKey(id);
    }

    public boolean varExistsOfType(String id, String t, MethodRecord mr, ClassRecord cr) {
	if (mr == null) {
	    if (cr.getType(id).equals("")) {
		throw new TypeCheckException("Variable '"+id+"'not declared in class '"
					     +cr.id+"'.");
	    }
	    return cr.getType(id).equals(t);
	}
	if (mr.getType(id).equals("")) {
	    if (cr.getType(id).equals("")) {
		throw new TypeCheckException("Variable '"+id+"'not declared in class '"
					     +cr.id+"'.");
	    }
	    return cr.getType(id).equals(t);
	}
	if (! mr.getType(id).equals(t)) {
	    return cr.getType(id).equals(t);
	}
    }

    /**public VarRecord getVarWithType(String id, String t, MethodRecord mr, ClassRecord cr) {
	if (mr == null) {
	    if (cr.getType(id).equals("")) {
		throw new TypeCheckException("Variable '"+id+"'not declared in class '"
					     +cr.id+"'.");
	    }
	    return cr.getType(id).equals(t);
	}
	if (mr.getType(id).equals("")) {
	    if (cr.getType(id).equals("")) {
		throw new TypeCheckException("Variable '"+id+"'not declared in class '"
					     +cr.id+"'.");
	    }
	    return cr.getType(id).equals(t);
	}
	return mr.getType(id).equals(t);
	}*/
    
}
