package hw2;

import visitor.DepthFirstVisitor;
import syntaxtree.*;

public class TestVisitor extends DepthFirstVisitor {

    @Override
    public void visit(Goal n) {
	super.visit(n.f0);
    }

    @Override
    public void visit(Identifier n) {
	System.out.println("TestVisitor::visit(Identifier n) "+n.f0.tokenImage);
    }

}
