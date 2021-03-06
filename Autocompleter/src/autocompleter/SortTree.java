/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autocompleter;

/**
 * A tree with a list on each node for storing words with same weight
 * @author Sebastián Patiño Barrientos
 * @author Luis Miguel Arroyave Quiñones
 */
public class SortTree {

    Node root;

    private class WordList {

        String word;
        WordList next;

        public WordList(String word){
            this.word = word;
            next = null;
        }
    }

    private class Node {

        int weight;
        WordList start;
        WordList last;
        Node left;
        Node right;

        public Node(String word, int weight){
            this.weight = weight;
            start = last = new WordList(word);
        }
    }

    /**
     * Constructor for the SortTree class
     */
    public SortTree(){
        root = null;
    }

    /**
     * This method inserts a word in the tree
     * @param word the word that we want to add
     * @param weight the weight of the word
     */
    public void add(String word, int weight){
        root = add(word, weight, root);
    }

    private Node add(String word, int weight, Node node){
        if (node == null) {
            return new Node(word, weight);
        }

        if (weight == node.weight){
            node.last = node.last.next = new WordList(word);
        } else if (weight < node.weight) {
            node.left = add(word, weight, node.left);
        } else {
            node.right = add(word, weight, node.right);
        }

        return node;
    }

    @Override
    public String toString(){
        return toString(root);
    }

    private String toString(Node node){
        if (node == null) {
            return "";
        }

        StringBuilder list = new StringBuilder();
        list.append(toString(node.right));

        WordList words = node.start;
        while (words != null) {
            list.append(words.word + "\n");
            words = words.next;
        }

        list.append(toString(node.left));

        return list.toString();
    }
}
