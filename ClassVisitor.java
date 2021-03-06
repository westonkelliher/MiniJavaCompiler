package hw2;

import java.util.Map;
import java.util.HashMap;
import visitor.GJNoArguDepthFirst;
import hw2.MethodVisitor;
import hw2.VarVisitor;
import hw2.Record;
import syntaxtree.*;


class ClassVisitor extends GJNoArguDepthFirst<ClassRecord> {

    //helper visitors
    private MethodVisitor method_visitor;
    private VarVisitor var_visitor;

    //constructor
    public ClassVisitor() {
	method_visitor = new MethodVisitor();
	var_visitor = new VarVisitor();
    }

    
    //visit methods
    @Override
    public ClassRecord visit(ClassDeclaration n) {    //? also for MainClass?
	ClassRecord cr = new ClassRecord(n.f1.f0.tokenImage);
	for (/*VarDeclaration*/Node vd : n.f3.nodes) {
	    VarRecord vr = var_visitor.visit((VarDeclaration)(vd));
	    cr.addVarRecord(vr);
	}
	for (/*MethodDeclaration*/Node md : n.f4.nodes) {
	    MethodRecord mr = method_visitor.visit((MethodDeclaration)(md));
	    cr.addMethodRecord(mr);
	}
	return cr;
    }

    @Override
    public ClassRecord visit(ClassExtendsDeclaration n) {
	ClassRecord cr = new ClassRecord(n.f1.f0.tokenImage, n.f3.f0.tokenImage);
	for (/*VarDeclaration*/Node vd : n.f5.nodes) {
	    VarRecord vr = var_visitor.visit((VarDeclaration)(vd));
	    cr.addVarRecord(vr);
	}
	for (/*MethodDeclaration*/Node md : n.f6.nodes) {
	    MethodRecord mr = method_visitor.visit((MethodDeclaration)(md));
	    cr.addMethodRecord(mr);
	}
	return cr;
    }

    @Override
    public ClassRecord visit(MainClass n) {
	ClassRecord cr = new ClassRecord(n.f1.f0.tokenImage);
	for (/*VarDeclaration*/Node vd : n.f14.nodes) {
	    VarRecord vr = var_visitor.visit((VarDeclaration)(vd));
	    cr.addVarRecord(vr);
	}
	return cr;
    }
    
}



class ClassRecord extends Record {

    //member variables
    public String super_class;
    private Map<String, VarRecord> variables;
    private Map<String, MethodRecord> methods;

    //constructors
    public ClassRecord(String id) {
	super(id);
	super_class = null;
	variables = new HashMap<String, VarRecord>();
	methods = new HashMap<String, MethodRecord>();
    }
    public ClassRecord(String id, String superc) {
	super(id);
	super_class = superc;
	methods = new HashMap<String, MethodRecord>();
    }

    //public methods
    public void addVarRecord(VarRecord vr) {
	variables.put(vr.id, vr);
    }
    public void addMethodRecord(MethodRecord mr) {
	methods.put(mr.id, mr);
    }

    public VarRecord getVarRecord(String id) {
	return variables.get(id);
    }
    public MethodRecord getMethodRecord(String id) {
	return methods.get(id);
    }

    public String getType(String id) {
	if (! variables.containsKey(id)) {
	    return "";
	}
	return variables.get(id).type;
    }

    /**public VarRecord getVarWithType(String id, String t) {
	
      }*/
    
}
