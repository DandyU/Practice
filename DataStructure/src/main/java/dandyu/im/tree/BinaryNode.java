package dandyu.im.tree;

import lombok.Builder;

@Builder
public class BinaryNode {

    public int number;
    public BinaryNode leftNode;
    public BinaryNode rightNode;

}
