/* Instead of Login_URL use service Layer Login URL of Yours */
/* Instead of SalesOpportunities_URL use service Layer SalesOpportunities URL of Yours */



const request = require('request')
const req = require('request')
const express = require('express')

const app = express()
var ID = ""

app.get('/', (req1, result) => {
	request.post('Login_URL',                      /* Change With your URL*/
{
  json: 
  {
    "CompanyDB": "DDWNEW",   /* DbName */
    "Password": "********",  /* DbPassword */
    "UserName": "manager"    /* DbUserName */
  }
}, 
(error, res, body) => 
{
  if (error) 
  {
    result.status(500).json(error)
    return
  }
  ID = body.SessionId
  req.get('SalesOpportunities_URL?$select=SequentialNo,WeightedSumLC,ClosingPercentage,CardCode,CustomerName,PredictedClosingDate,MaxLocalTotal &$filter=MaxLocalTotal ge 15000 and PredictedClosingDate le 20191010 &$top=5',               /* Change With your URL*/

	{
		headers: {
    'Cookie': "B1SESSION=" + ID.toString()
  }
},
(error, res, body) => 
{
  if (error) 
  {
	result.status(500).json(error)
    return
  }
  
  var results=[];
  
  for(var t in JSON.parse(body).value)
  {
	  var arr=
    {
      type: 'text',
		  content : "#" + JSON.parse(body).value[t].SequentialNo.toString() +" (" + JSON.parse(body).value[t].ClosingPercentage.toString() +" %) / "+ JSON.parse(body).value[t].WeightedSumLC.toString() + " INR (WeightedAmount)" + "\n" + JSON.parse(body).value[t].CardCode.toString() + " - " + JSON.parse(body).value[t].CustomerName.toString() +"\n"+ "Date : " + JSON.parse(body).value[t].PredictedClosingDate.toString() + " - Profit : " + JSON.parse(body).value[t].MaxLocalTotal.toString(),
    };
		results.push(arr);  
  }
result.status(200).json( 
{
  replies: results
}  
)
}
)
}
)	
});
app.listen(3000, () => console.log('Server ready'))
