package hw2;

import visitor.GJNoArguDepthFirst;
import syntaxtree.*;


class TypeVisitor extends GJNoArguDepthFirst<String> {

    @Override
    public String visit(ArrayType n) {
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
    
}
