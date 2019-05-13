Buisness Opportunities

A Buisness Opportunities webhook nodejs sample for a SAP Conversation AI bot.

Introduciton

A Buisness Opportunities bot shows how you reply with text message. The nlp result by SAP Conversational AI is sent in the request body, in which you can know about the intent and entities etc as a result of your bot training. Usually, the intent indicates a service to be fulfiled by the bot.

Deploy the webhook project

Local Deployment
Prequisites: You have NodeJS 6.12 or above run-time installed in your local machine.

Install the dependent node modules

npm install
Start the app

node index.js
Publish the webhook url to public internet. 
If you don’t have a public server, or if you want to test your bot during development, ngrok is a very handy tool: It creates a public URL for you and forwards requests to your computer. Once you installed it, run

ngrok http 3000
And copy the Forwarding URL in HTTPS (https://XXX.ngrok.io) to your bot Settings (“Bot webhook base URL” field). All requests made to these URL will be forwarded to the port 3000 of your computer.

As a result, you have obtain the webhook url for your bot.

Apply the webhook url to the skill of your bot
Open your bot on SAP Conversation AI

Go to tab Build and create a skill for the target intent
Setup the trigger as when the target intent is present.
For action, use Call Webhook to reply, and fill in the webhook url as step of deployment.
Now test your bot, it will reply with five opportunities messages based on MaxLocalTotal and PredictedClosingDate in Sales Opportunities.


Note In Node JS file Change the Service layer URL and Database Details
