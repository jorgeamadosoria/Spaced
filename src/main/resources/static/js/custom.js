function loadCardset(id){
	return cardset;
};

function loadCardsets(){
	return [cardset,cardset];
};

function send(cardset,right,showPlayCard){
	//send to backend
//	alert(cardset.current.id +" " + right);
	cardset.current = cardset.current.next;
	showPlayCard(cardset);
}




