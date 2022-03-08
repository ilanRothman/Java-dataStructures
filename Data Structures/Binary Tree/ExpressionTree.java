package il.ac.telhai.ds.trees;
import java.io.IOException;
import java.io.StreamTokenizer;
    public class ExpressionTree extends FullBinaryTree<String>{
        private static ExpressionTree exTree;

        public ExpressionTree(String element) {
            super(element);
        }

        public ExpressionTree(String element, BinaryTreeI<String> leftSon, BinaryTreeI<String> rightSon) {
            super(element, leftSon, rightSon);
        }


        /*
         * Read the stream tokenizer until EOF assuming and construct
         *  the expression tree corresponding to it.
         * The input contains a prefix expression.
         */
        public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
            if(tokenizer.ttype == StreamTokenizer.TT_EOF) {
                return exTree;
            }
                tokenizer.nextToken();
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    StringBuilder num = new StringBuilder();
                    num.append((int)tokenizer.nval);
                    return new ExpressionTree(num.toString());

                }
                else{
                    char c = (char)tokenizer.ttype;
                    return new ExpressionTree(String.valueOf(c),createTree(tokenizer),createTree(tokenizer));
            }

        }



        /*
         * Returns the infix expression corresponding to the current tree (*)
         */
        public String infix() {
            return inOrder(" "," ");
        }

        @Override
        public String inOrder(String before, String after) {
            StringBuilder sb = new StringBuilder();
            if (isLeaf()){
                return before + getValue() + after;
            }
            if(getLeft().isLeaf()){
                sb.append(getLeft().inOrder("", ""));
            }else{
                sb.append(getLeft().inOrder("",""));
            }
            sb.append(" "+ getValue()+ " ");
            if(getLeft().isLeaf()){
                sb.append(getRight().inOrder("",""));
            }else{
                sb.append(getRight().inOrder("",""));
            }
            return "("+sb.toString()+")";
        }

        /*
         * Returns the prefix expression corresponding to the current tree (*)
         */
        public String prefix() {
            return preOrder() + " ";
        }
//
//        /*
//         * Evaluates the expression corresponding to the current tree
//         * and returns its value
//         */
//        public double evaluate() {
//
//        }
    }
