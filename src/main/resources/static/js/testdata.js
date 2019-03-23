


var deck1 = {	id:1,	name: "No deck",	color: "ff0022", interval:-1 };
var deck2 = {	id:2,	name: "Current",	color: "ff0022", interval:1 };
var deck3 = {	id:3,	name: "Other day",	color: "ff0022", interval:2 };
var deck4 = {	id:4,	name: "Weekly",	    color: "ff0022", interval:7 };
var deck5 = {	id:5,	name: "Monthly",	color: "ff0022", interval:30 };
var deck6 = {	id:6,	name: "Retired",	color: "ff0022", interval:-1 };

var date = new Date('03/16/2019');
var cards = [
{ id:1, task: "task 1", answer:"answer 1-1\nanswer 1-1\nanswer 1-3\nanswer 1-4\nanswer 1-5", deck:deck1, set:cardset, last:Date.now()},
{ id:2, task: "task 2", answer:"answer 2-1", deck:deck2, set:cardset, last:Date.now()},
{ id:3, task: "task 3", answer:"answer 2-2", deck:deck2, set:cardset, last:Date.now()},
{ id:4, task: "task 4", answer:"answer 3-1", deck:deck3, set:cardset, last:Date.now()},
{ id:5, task: "task 5", answer:"answer 3-2", deck:deck3, set:cardset, last:Date.now()},
{ id:6, task: "task 6", answer:"answer 3-3", deck:deck3, set:cardset, last:Date.now()},
{ id:7, task: "task 7", answer:"answer 4-1", deck:deck4, set:cardset, last:Date.now()},
{ id:8, task: "task 8", answer:"answer 5-1", deck:deck5, set:cardset, last:Date.now()},
{ id:9, task: "task 9", answer:"answer 5-2", deck:deck5, set:cardset, last:Date.now()},
{ id:10, task: "task 0", answer:"answer 6-1", deck:deck6, set:cardset, last:Date.now()},
];

var cardset = {
	id: 1,
	name:"French verbs",
	description:"Description of the set",
	image:"https://www.avalonfreelibrary.org/sites/default/files/Calendar%20Images/AFPL%20website%20event%20icons-25.png",
	cards: cards,
	daily: 3,
	quota: 3
};

//alert("trest data");