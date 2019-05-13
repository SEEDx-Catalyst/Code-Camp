# demo-bot
A demo webhook nodejs sample for a SAP Conversation AI bot.

## Introduciton
This is a sample for a SAP Conversational AI bot with a webhook trigger as action. It is just a "hardcode" text for the response instead of the query data from real DB. But because of lacking of time, we'll try to improve it later.

To use this sample, please follow the steps below:

## Pull the latest source code
```
git pull
```

## Deploy the webhook project
### Option 1: Local Deployment
Prequisites: You have NodeJS 6.12 or above run-time installed in your local machine.

1. Install the dependent node modules
   ```
   npm install
   ```
2. Start the app
    ```
    node webhook.js
    ```
3. Publish the webhook url to public internet.
    Contact us if you need to run the test, then we can restart the ngrok and edit the link in SAP Conversation AI

## Further todos for a complete webhook implementation of your own bot
1. Complete the service fulfilment in webhook endpoint for your own bot.
2. Format the result of service fulfilment above into reply back to user
<br/>This sample only show one end point "/" for the webhook.
<br/>Of course, you could have multiple endpoint, each serve one intent
<br/>For instance, you have built an online shopping assistant bot with several inents, then you can endpoint as
  * product-inquiry intent: app.post('/search-product', (req, res) => {}) ,which handle the product inquiry by searching the products from product catalog with given key words.
  * add-to-cart intent: app.post('/add-to-cart', (req, res) => {}), which handle the add-to-cart intent by adding the given item to the shopping cart
  * place-order intent: app.post('/place-order', (req, res) => {}) which handle the checkout the shopping cart and place the order to ERP.

