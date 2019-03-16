


var deck1 = {	id:1,	name: "No deck",	color: "ff0022" };
var deck2 = {	id:2,	name: "Current",	color: "ff0022" };
var deck3 = {	id:3,	name: "Other day",	color: "ff0022" };
var deck4 = {	id:4,	name: "Weekly",	    color: "ff0022" };
var deck5 = {	id:5,	name: "Monthly",	color: "ff0022" };
var deck6 = {	id:6,	name: "Retired",	color: "ff0022" };

var cards = [
{ id:1, task: "task 1", answer:"answer 1-1", deck:deck1, set:cardset},
{ id:1, task: "task 2", answer:"answer 2-1", deck:deck2, set:cardset},
{ id:1, task: "task 3", answer:"answer 2-2", deck:deck2, set:cardset},
{ id:1, task: "task 4", answer:"answer 3-1", deck:deck3, set:cardset},
{ id:1, task: "task 5", answer:"answer 3-2", deck:deck3, set:cardset},
{ id:1, task: "task 6", answer:"answer 3-3", deck:deck3, set:cardset},
{ id:1, task: "task 7", answer:"answer 4-1", deck:deck4, set:cardset},
{ id:1, task: "task 8", answer:"answer 5-1", deck:deck5, set:cardset},
{ id:1, task: "task 9", answer:"answer 5-2", deck:deck5, set:cardset},
{ id:1, task: "task 0", answer:"answer 6-1", deck:deck6, set:cardset},
];

var cardset = {
	id: 1,
	name:"French verbs",
	description:"Description of the set",
	image:"https://www.avalonfreelibrary.org/sites/default/files/Calendar%20Images/AFPL%20website%20event%20icons-25.png",
	cards: cards
};

//alert("trest data");