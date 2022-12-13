package jam.Scene;

import java.util.ArrayList;

public class Account implements Comparable<Account> {

    private long score = 0;
    private String userName;

    public ArrayList<Account> getAccounts() {return accounts;}

    public static ArrayList<Account> accounts = new ArrayList<>();


    @Override
    public int compareTo(Account o) {
        return Long.compare(score, o.getScore());
    }

    public void addToScore(long score) {
        this.score += score;
    }

    public void setScore(long score) {this.score = score;}
    public long getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;
    }

    public static Account makeNewAccount(String userName, Long score){
        Account account = new Account(userName,score);
        accounts.add(account);
        return account;
    }

    public Account(String userName,Long score){
        this.score = score;
        this.userName=userName;
    }

}
