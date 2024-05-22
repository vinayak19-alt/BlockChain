package blockchain;

import java.util.Date;

public class Block {
    public String hash; // this will hold the digital signature
    public String prevHash;
    private String data; // the data will be a single message
    private long timeStamp; //
    private int nonce;

    public Block(String data, String prevHash){
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); // make sure we do this after we set the other values
    }

    public String calculateHash(){
        String calculatesHash = StringUtil.applySHA256(
                prevHash + Long.toString(timeStamp) + Integer.toString(nonce) + data
        );
        return calculatesHash;
    }
    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0'); // create a string with difficulty * '0'
        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined: " +  hash);
    }
}
