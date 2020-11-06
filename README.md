# PaypalClient
Simple client to check for resources of users based on emails

## How to use it?
First of all, we need to create our paypal client instance.<br>
In order to do so, we must know a few things: API username, password and signature.<br>
This can be retreived by opening the [api-access](https://www.paypal.com/businessprofile/mytools/apiaccess/firstparty/signature) of paypal, and retrieving the credentials.<br>

Now, we can create the client instance. We use the ClientBuilder object for that:<br>
```java
PaypalClient client = new ClientBuilder()
            .setClientType(ClientType.LIVE)
            .setUsername("<API USERNAME>")
            .setPassword("<API PASSWORD>")
            .setSignature("<API SIGNATURE>")
            .buildClient();
```

Now, we need to use a combination of the two methods that PaypalClient has:<br>
- searchTransactions: Will search all the transactions of a specfic email.
- getTransactionDetail: Will return all the details of a specific transction, by it's id.

*Please note: When using objects that extend Response, we must first check if they have failed using the hasFailed() method.*<br><br>

Then, we'll get all the transaction ids that were made by the email:
```java
TransactionSearchResponse transactionSearchResponse = client.searchTransactions(email);

if(transactionSearchResponse.hasFailed()){
    transactionSearchResponse.getFailureReason().printStackTrace();
    return;
}

List<String> transactionIds = transactionSearchResponse.getTransactionIds();
```

Now that we have all the transaction ids, we can loop through them and extract the data from them.<br>
I recommend checking for the following fields:<br>
- PAYMENTSTATUS: Making sure that the payment was successful first.
- CUSTOM: This field is attached to the payments, and can be used to get details about the resource and the user that purchased the resource on Spigot.
- SUBJECT/L_NAME0: This field is attached to the payments, and can be used to get details about the resource and the user that purchased the resource on MC-Market.

The only thing left is to implement the code:
```java
for(String transactionId : transactionIds){
    GetTransactionDetailResponse getTransactionDetailResponse = client.getTransactionDetail(transactionId);

    if(getTransactionDetailResponse.hasFailed()){
        getTransactionDetailResponse.getFailureReason().printStackTrace();
        continue;
    }

    Map<String, String> transactionDetails = getTransactionDetailResponse.getTransactionDetails();

    String paymentStatus = transactionDetails.getOrDefault("PAYMENTSTATUS", "");

    if(paymentStatus.equals("Completed")){
        try{
            String customField = transactionDetails.getOrDefault("CUSTOM", "");
            // Spigot's custom field has the following structure: 'resource_purchase|<user-id>|<>|<resource-id>'
            if (customField.contains("resource_purchase")) {
                // TODO
            }
            // If the field doesn't contain 'resource_purchase', then we can assume the transaction was made on MC-Market
            else{
                String productSubject = transactionDetails.getOrDefault("SUBJECT", "");
                /* The subject has the following structure: '<RESOURCE-TITLE> (Purchased by user #<user-id>)'
                    You can extract the data however you want. I personally just check if the name of the resource 
                    is in the subject, and with regex extract the user's id */
                // TODO
            }
        }catch(Exception ex){
            //TODO
        }
    }
}
```

That's the basic usage of the client. You can also add check for refunds etc, to make sure the user still has access to the resources.<br>
Other methods, such as web-scraping the websites or making simple requests are forbidden on both of the sites, so be aware.
