package jam.Account;

import java.util.ArrayList;

/**
 * This Class is used to handle the array list of accounts object.
 */
public class Account implements Comparable<Account> {

    /**
     * This is score of the current account object, default is set to 0;
     */
    private long score = 0;

    /**
     * This username is used to store current object username.
     */
    private String userName;

    /**
     * This getter of arraylist of exits accounts.
     *
     * @return
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * This is array list of the account which contain all exist account.
     */
    public static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * This method compare current Account object score with the given Account object score.
     *
     * @param o the object to be compared.
     * @return >0 if current Account score is bigger, 0 if same score, <0 if smaller.
     */
    @Override
    public int compareTo(Account o) {
        return Long.compare(score, o.getScore());
    }

    /**
     * This is setter for the score. Is used when replacing the score.
     *
     * @param score set this score to current account object.
     */
    public void setScore(long score) {
        this.score = score;
    }

    /**
     * This is getter for the score. It is used when setting the score and replacing the score.
     *
     * @return Get current account score.
     */
    public long getScore() {
        return score;
    }

    /**
     * Getter for username for current account object.
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This function is used to check whether the given username already exist in the Account arraylist.
     *
     * @param userName
     * @return Return the account which have same username as the given username, return null if account with given username not found.
     */
    public static Account accountHaveBeenExist(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    /**
     * This function is use to create a object of Account and also add the account object in to the Account array list.
     *
     * @param userName Username which will give to new account.
     * @param score    Score which will f=give to new account.
     * @return Just created account.
     */
    public static Account makeNewAccount(String userName, Long score) {
        Account account = new Account(userName, score);
        accounts.add(account);
        return account;
    }

    /**
     * This is Account constructor.
     *
     * @param userName
     * @param score
     */
    public Account(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }

}
