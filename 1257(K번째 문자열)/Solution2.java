import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
class Node{
    private String character;
    private int value;
    private Node[] children;
    private boolean leaf;
    private final int ALPHABET_SIZE = 26;
    public Node(String character) {
        this.character = character;
        this.children = new Node[ALPHABET_SIZE];
    }
     
    public Node getChild(int index) {
        return children[index];
    }
     
    public void setChild(int index, Node node, int value) {
        node.setValue(value);
        this.children[index] = node;
    }
     
    public int getValue() {
        return this.value;
    }
     
    public void setValue(int value) {
        this.value = value;
    }
     
    public String getCharacter() {
        return character;
    }
     
    public void setCharacter(String character) {
        this.character = character;
    }
     
    public Node[] getChildren() {
        return this.children;
    }
     
    public void setChildren(Node[] children) {
        this.children = children;
    }
     
    public boolean isLeaf() {
        return this.leaf;
    }
     
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
     
    @Override
    public String toString() {
        return this.character;
    }
}
 
class Trie{
    private Node root;
    private int indexOfSingleChild;
     
    public Trie() {
        this.root = new Node("");
    }
     
    public void insert(String key, int value) {
        Node tempNode = root;
        for(int i=0; i<key.length(); i++) {
            char c = key.charAt(i);
            int asciiIndex = c - 'a';
             
            if(tempNode.getChild(asciiIndex) == null){
                Node node = new Node(String.valueOf(c));
                tempNode.setChild(asciiIndex, node, value);
                tempNode = node;
            }
            else {
                tempNode = tempNode.getChild(asciiIndex);
            }
        }
        tempNode.setLeaf(true);
    }
     
    // 해당 키가 존재하는지 여부 반환
    public boolean search(String key) {
        Node trieNode = root;
         
        for(int i=0; i<key.length(); i++) {
            int asciiIndex = key.charAt(i) - 'a';
            if(trieNode.getChild(asciiIndex) == null)
                return false;
            else
                trieNode = trieNode.getChild(asciiIndex);
        }
        return true;
    }
 
    public ArrayList<String> allWordsWithPrefix(String prefix){
        Node trieNode = root;
        ArrayList<String> allWords = new ArrayList<>();
         
        for(int i=0; i<prefix.length(); i++) {
            int asciiIndex = prefix.charAt(i) - 'a';
            trieNode = trieNode.getChild(asciiIndex);
        }
        collect(trieNode, prefix, allWords);
        return allWords;
    }
     
    public void collect(Node node, String prefix, List<String> allWords) {
        if(node == null)
            return;
        if(node.isLeaf())
            allWords.add(prefix);
         
        for(Node childNode : node.getChildren()) {
            if(childNode == null)
                continue;
            String childCharacter = childNode.getCharacter();
            collect(childNode, prefix + childCharacter, allWords);
        }
    }
    public void sort(int test, int k) {
        ArrayList<String> list = allWordsWithPrefix("");
        if(list.size() < k)
            System.out.println("#" + test + " none");
        for(int i=0; i<list.size(); i++) {
            if(i == (k-1)) {
                System.out.println("#" + test + " " + list.get(i));
                 
            }
        }
    }
}
 
class Solution {
    static int k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
            k = Integer.parseInt(br.readLine());
            String str = br.readLine();
             
            Trie trie = new Trie();
            int cnt = 0;
             
            int len = str.length();
            for(int i=0; i<len; i++) {
                for(int j=i+1; j<=len; j++) {
                    String temp = str.substring(i, j);
                    trie.insert(temp, cnt++);
                }
            }
            trie.sort(test, k);
        }
    }
}