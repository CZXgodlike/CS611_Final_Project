package controller;

import assets.AccountInformation;
import assets.ActivityHistory;
import assets.AssociateAccountInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssociateAccountController {

    private String dataPath;

    public AssociateAccountController(){
        dataPath = "data/customerData.csv";
    }

    public List<AccountInformation> getData(String userName){
        List<AccountInformation> data = new ArrayList<>();

        try {
            for(String account: getAssociateAccounts(userName)){
                data.add(new AccountInformation(account));
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getAssociateAccounts(String userName) throws IOException {
        List<AssociateAccountInformation> data = readData();
        String accounts = null;
        List<String> associateAccounts;

        for(AssociateAccountInformation information: data){
            if(information.getUserName().equals(userName)){
                accounts = information.getAssociateAccounts();
                break;
            }
        }

        if(accounts == null){
            return null;
        } else {
            associateAccounts = Arrays.asList(accounts.split(";"));
            return associateAccounts;
        }
    }

    private List<AssociateAccountInformation> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(dataPath));
        List<AssociateAccountInformation> associateAccountInformationList = new ArrayList<>();
        String row;

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            associateAccountInformationList.add(new AssociateAccountInformation(Arrays.asList(data)));
        }
        csvReader.close();

        return associateAccountInformationList;
    }
}
