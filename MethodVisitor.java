package hw2;

import java.util.Vector;
import java.util.Map;
import java.util.HashMap;
import visitor.GJNoArguDepthFirst;
import hw2.Record;
import hw2.VarVisitor;
import hw2.TypeVisitor;
import syntaxtree.*;


class MethodVisitor extends GJNoArguDepthFirst<MethodRecord> {

    //helper visitor
    private VarVisitor var_visitor;
    private TypeVisitor type_visitor;
    
    //constructor
    public MethodVisitor() {
	var_visitor = new VarVisitor();
    }
    
    @Override
    public MethodRecord visit(MethodDeclaration n) {
	String rt = type_visitor.visit(n.f1); //TODO: implement in VarVisitor
	MethodRecord mr = new MethodRecord(n.f2.f0.tokenImage, rt);
	for (/*VarDeclaration*/Node vd : n.f7.nodes) {
	    VarRecord vr = var_visitor.visit((VarDeclaration)(vd));
	    mr.addVarRecord(vr);
	}
	if (n.f4.node == null) {
	    return mr;
	}
	mr.addParameter(var_visitor.visit(((FormalParameterList)(n.f4.node)).f0));
	for (/*FormalParameterRest*/Node fpr: ((FormalParameterList)(n.f4.node)).f1.nodes) {
	    mr.addParameter(var_visitor.visit(((FormalParameterRest)(fpr)).f1/*FormalParameter*/));
	}
	return mr;
    }
    
}


class MethodRecord extends Record{

    //member variables
    public String return_type;
    public Vector<String> parameters; //just the types required by parameters
    private Map<String, VarRecord> variables;  //includes varables declared by parameter

    //constructor
    public MethodRecord(String id, String rt) {
	super(id);
	return_type = rt;
	parameters = new Vector<String>();
	variables = new HashMap<String, VarRecord>();
    }

    //public methods
    public String toString() {
	return id+"()";
    }
    public String toString(ClassRecord cr) {
	return cr.id+"::"+id+"()";
    }
    public void addParameter(VarRecord vr) {
	parameters.add(vr.type);
	variables.put(vr.id, vr);
    }
    public void addVarRecord(VarRecord vr) {
	variables.put(vr.id, vr);
    }

    public String getType(String id) {
	if (! variables.containsKey(id)) {
	    return "";
	}
	return variables.get(id);
    }
    /*
    public boolean sameMethodAs(MethodRecord mr) {
	if (id != mr.id ||
	    return_type != mr.return_type ||
	    parameter_types.size() != mr.parameter_types.size()
	    ) {
	    return false;
	}
	for (int i = 0; i < parameter_types.size(); i++) {
	    if (! parameter_types[i].equals(mr.parameter_types[i])) {
		return false
	    }
	}
	return false;
	}*/
    
}
