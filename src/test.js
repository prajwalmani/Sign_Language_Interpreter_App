function spellcheck(word){
    var data="";
    var data=data.concat("text=",word,"&language=en-US");
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            // console.log(this.responseText);
            var response=this.responseText;
            console.log(response);

        }
    });
    
    xhr.open("POST", "https://grammarbot.p.rapidapi.com/check");
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("x-rapidapi-key", "e2be155a48msha5f36a50e5a0239p192aefjsn5df12b82562e");
    xhr.setRequestHeader("x-rapidapi-host", "grammarbot.p.rapidapi.com");
    
    xhr.send(data);
}

spellcheck("thissss")
