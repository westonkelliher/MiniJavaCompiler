package hw2;

import hw2.Record;
import hw2.TypeVisitor;
import visitor.GJNoArguDepthFirst;
import syntaxtree.*;


class VarVisitor extends GJNoArguDepthFirst<VarRecord> {

    //helper visitors
    TypeVisitor type_visitor;

    //constructor
    public VarVisitor() {
	type_visitor = new TypeVisitor();
    }

    @Override
    public VarRecord visit(VarDeclaration n) {
	VarRecord vr = new VarRecord(n.f1.f0.tokenImage);
	vr.type = n.f0.f0.choice.accept(type_visitor);
	return vr;
    }

    @Override
    public VarRecord visit(FormalParameter n) {
	VarRecord vr = new VarRecord(n.f1.f0.tokenImage);
	vr.type = n.f0.f0.choice.accept(type_visitor);
	return vr;
    }

    /**
    //here we use an encapsulation trick that isnt very pretty
    @Override
    public VarRecord visit(ArrayType n) {
	return "int[]";
    }

    @Override
    public String visit(BooleanType n) {
	return "boolean";
    }

    @Override
    public String visit(IntegerType n) {
	return "int";
    }

    @Override
    public String visit(Identifier n) {
	return n.f0.tokenImage;
    }
    */
	
}



class VarRecord extends Record {

    //member variable
    public String type;

    //constructor
    public VarRecord(String id) {
	super(id);
    }
}
