package blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BlockChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static void main(String[] args) {
        // add out blocks to the blockchain list
        blockchain.add(new Block("Hi I am the first block", "0"));
        System.out.println("Mining block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Hi I am the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hi I am the third block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockChain is valid:" + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe BlockChain: ");
        System.out.println(blockchainJson);

    }
    public static boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;
        String hashtarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to find hashes
        for(int i=1; i<blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes are not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!prevBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Previous Hashes are not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring(0, difficulty).equals(hashtarget)){
                System.out.println("The Block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
