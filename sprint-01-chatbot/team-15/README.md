# chat-bot team 15
A sapbot webhook java spring sample for a SAP Conversation AI bot.

## Introduciton
A sapbot webhook sample for a SAP Conversational AI bot with a webhook trigger as action. This simple sample how you reply with text message and a list template message. The nlp result by 
SAP Conversational AI is sent in the request body, in which you can know about the intent and entities etc as a result of your bot training. Usually, the intent indicates a service to be fulfiled by the bot.

For example, the end user is buying something through a conversation with your bot by saying "I want some pizza", once its intent and given contextual information has been understood by the bot, the bot will fulfils the service by invoking a RESTful call to SAP Business One or SAP Business ByDesign to place an order 
etc, and respond to the end user with the order number 
just placed.

To use this sample, please follow the steps below:

## Prequisities
* You have followed this [tutorial](https://recast.ai/blog/build-your-first-bot-with-recast-ai/) to build your first bot with SAP Conversational AI.

## Download the project
```
git clone https://github.com/YatseaLi/hello-world-bot.git
```

## Deploy the webhook project
### Local Deployment
Prequisites: You have JDK 8 or above run-time installed in your local machine.

1. Download and install the dependent java development kit (jdk)
   ```sh
   https://www.oracle.com/technetwork/java/javase/downloads/index.html
   ```
2. Start the app
    ```
    java -jar seedx-team15.jar
    ```
3. Publish the webhook url to public internet.
    <br/>If you don’t have a public server, or if you want to test your bot during development, ngrok is a very handy tool: It creates a public URL for you and forwards requests to your computer. Once you installed it, run 
    ```
    ngrok http 5000
    ``` 
    And copy the Forwarding URL in HTTPS (https://XXX.ngrok.io) to your bot Settings (“Bot webhook base URL” field). All requests made to these URL will be forwarded to the port 5000 of your computer.

    As a result, you have obtain the webhook url for your bot.

## License
The source code is under MIT license. Please kindly check the LICENSE. Here is to highlight that THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED. Therefore no support available.
