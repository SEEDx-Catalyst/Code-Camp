/*****************************************************
 * This is a hello world sample of webhook implementation
 * for a SAP Conversation Bot with a webhook trigger as 
 * action. This simple sample how you reply with text 
 * message and a list template message. The nlp result by 
 * SAP Conversational AI is sent in the request body, in
 * which you can know about the intent and entities etc as 
 * a result of your bot training. Before reply to user, 
 * a service fulfilment should be accomplished according to 
 * the intent.
 * 
 * For example, the end user is buying something through 
 * a conversation with your bot, once the intent and given 
 * contextual information has been understood by the bot,
 * it fulfils the service by invoking a RESTful call to 
 * SAP Business One or SAP Business ByDesign to place an order 
 * etc, and respond to the end user with the order number 
 * just placed.
 * 
 * The source code is under MIT license. Please kindly check the LICENSE.
 * Here is to highlight that THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED. Therefore no support available.
 * 
 * Author: Yatsea Li
 * Created on Feb 20 2019
 * All right reserved by SAP SE
 ******************************************************/
const express = require('express')
const bodyParser = require('body-parser')

const app = express()
const port = process.env.PORT || 5000
app.use(bodyParser.json())

//This sample only show one end point "/" for the webhook.
//Of course, you could have multiple endpoint, each serve one intent
//For instance, you have built an online shopping assistant bot 
//with several inents, then you can endpoint as
//1.product-inquiry intent: app.post('/search-product', (req, res) => {})
//which handle the product inquiry by searching the products from product
//catalog with given key words.
//2.add-to-cart intent: app.post('/add-to-cart', (req, res) => {})
//which handle the add-to-cart intent by adding the given item to the shopping cart
//3.place-order intent: app.post('/place-order', (req, res) => {})
//which handle the checkout the shopping cart and place the order to ERP 


app.post('/login',getLoginInformations);
app.post('/presales',getPresalesInformations);

function getLoginInformations(req, res) {  
  console.log(req.body)

  //Todo #1: Please implement your service fulfilment for the intent here
  
  //Todo #2: Format the result of service fulfilment above into reply back to user
  //This sample sends back the reply message in text and list template.
  //1.Text reply sample
  //2.A list template reply
  //for more reply template, please refer to
  //https://cai.tools.sap/docs/concepts/structured-messages
  res.send({
    replies: [{
      type: 'text',
      content: 'Welcome to b1 system !'
    }],
    conversation: {
      memory: {
        key: 'value'
      }
    }
  })
}

function getPresalesInformations(req, res) {  
  console.log(req.body)


  res.send({
    replies: [
      {
        type: 'text',
        content: '#100(60%)\n$90000(Weighted Amt)\nC10000-Kamal Kumara\n$100000(Total Amt)\nFinal Negotiation',
      }, {
        type: 'text',
        content: '#101(50%)\n$10000(Weighted Amt)\nC20000-Vimal Perera\n$8000(Total Amt)\nPending',
      },
      {
        type: 'text',
        content: '#102(100%)\n$5000(Weighted Amt)\nC60000-Saman Kosta\n$5000(Total Amt)\nCompleted',
      },
      {
        type: 'text',
        content: '#110(0%)\n$90000(Weighted Amt)\nC70000-Kusal Perera\n$0(Total Amt)\nNot Started',
      }
    ],
    conversation: {
      memory: {
        key: 'value'
      }
    }
  })
}



app.post('/errors', (req, res) => {
  console.log(req.body)
  res.send()
})

app.listen(port, () => {
  console.log(`Server is running on port ${port}`)
})