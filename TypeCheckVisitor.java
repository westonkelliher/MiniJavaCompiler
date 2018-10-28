package hw2;

import hw2.TypeCheckException;
import hw2.ExpressionType;
import hw2.ExpressionVisitor;
import visitor.DepthFirstVisitor;
import syntaxtree.*;


public class TypeCheckVisitor extends DepthFirstVisitor {

    //helper visitors
    TypeVisitor type_visitor;
    ExpressionVisitor expr_visitor;
    
    //declaration data
    private MasterRecord master_record;
    private ClassRecord current_class;
    private MethodRecord current_method;

    //constructor
    public TypeCheckVisitor(MasterRecord mr) {
	master_record = mr;
	type_visitor = new TypeVisitor();
	expr_visitor = new ExpressionVisitor();
    }



    // v v v DECLARATIONS v v v //

    @Override
    public void visit(MainClass n) {
	current_class = master_record.main_class;
	current_method = null;
	super.visit(n);
    }

    @Override
    public void visit(ClassDeclaration n) {
	String c_name = n.f1.f0.tokenImage;
	current_class = master_record.classes.get(c_name);
	current_method = null;
	super.visit(n);
    }

    
    @Override
    public void visit(ClassExtendsDeclaration n) {
	String c_name = n.f1.f0.tokenImage;
	current_class = master_record.classes.get(c_name);
	current_method = null;
	String c_super = n.f3.f0.tokenImage;
	if (! master_record.typeExists(c_super)) {
	    throw new TypeCheckException("Type '"+t+"'does not exist (extend of class "
					 +current_class.id+").");	    
	}
	super.visit(n);
    }


    @Override
    public void visit(MethodDeclaration n) {
	String m_name = n.f2.f0.tokenImage;
	current_method = current_class.getMethodRecord(m_name);
	String t = type_visitor.visit(n.f1);
	if (! master_record.typeExists(t)) {
	    throw new TypeCheckException("Type '"+t+"'does not exist (return for "
					 +current_method.toString()+").");
	}
	ExpressionType et = expr_visitor.visit(n.f10);
	if (! et.isType(t)) {
	    throw new TypeCheckException("Invalid return type '"+t+"' in function '"
					 +current_method.toString());
	}
	super.visit(n);
    }

    @Override
    public void visit(VarDeclaration n) {
	String t = type_visitor.visit(n.f0);
	if (! master_record.typeExists(t)) {
	    throw new TypeCheckException("Type '"+t+"'does not exist (declaration of "
					 +n.f1.f0.tokenImage+").");
	}
    }

    @Override
    public void visit(FormalParameter n) {
	String t = type_visitor.visit(n.f0);
	if (! master_record.typeExists(t)) {
	    throw new TypeCheckException("Type '"+t+"'does not exist (parameter "
					 +n.f1.f0.tokenImage+" for "+current_method.toString()+").");
	}
    }
    
    
    // ^ ^ ^ DECLARATIONS ^ ^ ^ //
    

    


    
    // v v v STATEMENT v v v //

    @Override
    public void visit(AssignmentStatement n) {

    }    

    @Override
    public void visit(ArrayAssignmentStatement n) {
	ExpressionType et = expr_visitor.visit(n.f2);
	if (! et.isType("int")) {
	    throw new TypeCheckException("Type other than int used to reference array");
	}
	et = expr_visitor.visit(n.f5);
	if (! et.isType("int[]")) {
	    throw new TypeCheckException("");
	}
    }    
	    
    // ^ ^ ^ STATEMENT ^ ^ ^ //

}
