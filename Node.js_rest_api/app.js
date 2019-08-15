var express = require("express");
var bodyParser = require("body-parser");

var app = express();
app.use(bodyParser.json());

/*  "/api/contacts"
 *    GET: finds all contacts
 *  
 *  example: curl -i -X GET http://localhost:8080/api/contacts
 */
app.get("/api/contacts", function(req, res) {
  res.status(200).json([{ id: 1, name: 'name1' }, { id: 2, name: 'name2' }]);
});

/*  "/api/contacts"
 *    POST: creates a new contact
 *
 *  example: curl -i -X POST -H "Content-Type: application/json" --data '{"id":"3","name":"name3"}' http://localhost:8080/api/contacts
 */
app.post("/api/contacts", function(req, res) {
  var newContact = req.body;
  res.status(201).json(newContact);
});

/*  "/api/contacts/:id"
 *    GET: find contact by id
 *
 *  example: curl -i -X GET http://localhost:8080/api/contacts/2
 */
app.get("/api/contacts/:id", function(req, res) {
  var id = req.params.id;
  res.status(200).json({ id: id, name: 'name'+id });
});

/*  "/api/contacts/:id"
 *    PUT: update contact by id
 *
 *  example: curl -i -X PUT -H "Content-Type: application/json" --data '{"id":"3","name":"new_name"}' http://localhost:8080/api/contacts/3
 */
app.put("/api/contacts/:id", function(req, res) {
  var updateContact = req.body;    
  res.status(200).json(updateContact);    
});

/*  "/api/contacts/:id"
 *    DELETE: deletes contact by id
 *
 *  example: curl -i -X DELETE http://localhost:8080/api/contacts/2
 */
app.delete("/api/contacts/:id", function(req, res) {
  var id = req.params.id;    
  res.status(200).json({ id: id });
});

app.listen(8080, function() {
    console.log("App started on port 8080");
});