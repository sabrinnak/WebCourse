//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");
const date = require(_dirname + "/date.js");

const app = express();

const items = ["Buy Food", "Cook Food", "Eat Food"];
const workItems = [];

app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

app.get("/", function(request, response){

    let day = date.getDate();

    response.render("list", {
        listTitle : day, newListItems: items
    });
});

app.post("/", function(request, response){
    
    const item = request.body.newItem;

    if(request.body.list === "Work"){
        workItems.push(item);
        response.redirect("/work");
    }else{
        items.push(item);
        response.redirect("/");
    }

});

app.get("/work", function(request, response){
    response.render("list", {listTitle: "Work List", newListItems: workItems});
});

app.get("/about", function(request, response){
    response.render("about");
});


app.listen(3000, function(){
    console.log("Server started on port 3000");
});


