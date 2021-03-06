package hw2;

import hw2.TypeCheckException;
import hw2.ExpressionType;
import visitor.GJNoArguDepthFirst;
import syntaxtree.*;

public class ExpressionVisitor extends GJNoArguDepthFirst<ExpressionType>{


    @Override
    public ExpressionType visit(Expression n) {
	return n.f0.accept(this);
    }
    

    // v v v EXPRESSION v v v //
    
    @Override
    public ExpressionType visit(AndExpression n) {
	if (n.f0.accept(this).isType("boolean") && n.f2.accept(this).isType("boolean")){
	    return new ExpressionType("boolean");
	}
	else {
	    throw new TypeCheckException("AndExpression");
	}
    }

    @Override
    public ExpressionType visit(CompareExpression n) {
	if (n.f0.accept(this).isType("int") && n.f2.accept(this).isType("int")) {
	    return new ExpressionType("boolean");
	}
	else {
	    throw new TypeCheckException("CompareExpression");
	}
    }	
    
    @Override
    public ExpressionType visit(PlusExpression n) {
	if (n.f0.accept(this).isType("int") && n.f2.accept(this).isType("int")) {
	    return new ExpressionType("int");
	}
	else {
	    throw new TypeCheckException("PlusExpression");
	}
    }

    @Override
    public ExpressionType visit(MinusExpression n) {
	if (n.f0.accept(this).isType("int") && n.f2.accept(this).isType("int")) {
	    return new ExpressionType("int");
	}
	else {
	    throw new TypeCheckException("MinusExpression");
	}
    }

    @Override
    public ExpressionType visit(TimesExpression n) {
	if (n.f0.accept(this).isType("int") && n.f2.accept(this).isType("int")) {
	    return new ExpressionType("int");
	}
	else {
	    throw new TypeCheckException("TimesExpression");
	}
    }

    @Override
    public ExpressionType visit(ArrayLookup n) {
	if (n.f0.accept(this).isType("int[]") && n.f2.accept(this).isType("int")) {
	    return new ExpressionType("int");
	}
	else {
	    throw new TypeCheckException("ArrayLookup");
	}
    }

    @Override
    public ExpressionType visit(ArrayLength n) {
	if (n.f0.accept(this).isType("int[]")) {
	    return new ExpressionType("int");
	}
	else {
	    throw new TypeCheckException("ArrayLength");
	}
    }

    @Override
    public ExpressionType visit(MessageSend n) {
	//TODO: give return type of method
	return new ExpressionType();
    }

    @Override
    public ExpressionType visit(ExpressionRest n) {
	return n.f1.accept(this);
    }
    
    @Override
    public ExpressionType visit(PrimaryExpression n) {
	return n.f0.choice.accept(this);
    }
    
    // ^ ^ ^ EXPRESSION ^ ^ ^ //



    
    // v v v PRIMARYEXPRESSION v v v //

    @Override
    public ExpressionType visit(IntegerLiteral n) {
	return new ExpressionType("int");
    }

    @Override
    public ExpressionType visit(TrueLiteral n) {
	return new ExpressionType("boolean");
    }

    @Override
    public ExpressionType visit(FalseLiteral n) {
	return new ExpressionType("boolean");
    }

    @Override
	    //just returns identifier string wrapped in ExpressionType
    public ExpressionType visit(Identifier n) {
	return new ExpressionType(n.f0.tokenImage);
    }

    @Override
    public ExpressionType visit(ThisExpression n) {
	//TODO
	//return scope.selfType
	return new ExpressionType();	
    }

    @Override
    public ExpressionType visit(AllocationExpression n) {
	if (n.f3.accept(this).isType("int")) {
	    return new ExpressionType("int[]");
	}
	else {
	    throw new TypeCheckException("AllocationExpression");
	}
    }

    @Override
    public ExpressionType visit(NotExpression n) {
	if (n.f1.accept(this).isType("boolean")) {
	    return n.f1.accept(this);
	}
	else {
	    throw new TypeCheckException("AllocationExpression");
	}	
    }
    
    @Override
    public ExpressionType visit(BracketExpression n) {
	return n.f1.accept(this);
    }
}
    
